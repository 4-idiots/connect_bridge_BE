package com.connectbridge.connect_bridge_BE.studypage.Service;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.LeaderMapping;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.MemberMapping;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudySubmitEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.*;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyLikeRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudySubmitRepository;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudyService {
    private final S3Service s3Service;
    private final StudyRepository studyRepository;
    private final StudyLikeRepository studyLikeRepository;
    private final UserRepository userRepository;
    private final StudySubmitRepository submitRepository;
    private final EntityManager em;

    public StudyService(S3Service s3Service, StudyRepository studyRepository, StudyLikeRepository studyLikeRepository, UserRepository userRepository, StudySubmitRepository submitRepository, EntityManager em) {
        this.s3Service = s3Service;
        this.studyRepository = studyRepository;
        this.studyLikeRepository = studyLikeRepository;
        this.userRepository = userRepository;
        this.submitRepository = submitRepository;
        this.em = em;
    }

    // 페이징
    public List<StudyDto> studyGetPage(Pageable pageable, int reqPage) {
        pageable = PageRequest.of(reqPage, 5, Sort.by(Sort.Direction.DESC, "id"));
        Page<StudyEntity> page = studyRepository.findAll(pageable);
        List<StudyDto> pageDto = page.map(StudyDto::new).getContent();
        System.out.println("study main get 동작");
        return pageDto;
    }

    // 게시글 top4
    public List<StudyDto> studyGetTop() {
        List<StudyEntity> page = studyRepository.findTop4ByOrderByIdDesc();
        List<StudyDto> pageDto = page.stream().map(StudyDto::new).collect(Collectors.toList());
        return pageDto;
    }

    // 생성
    public void createStudy(StudyCreateDto studyCreateDto) {
        StudyEntity studyEntity = new StudyEntity().createStudy(studyCreateDto);
        studyRepository.save(studyEntity);
    }

    // 카드 정보 제공.
    public StudyDetailDto clickStudy(Long studyID, Long userID) {
        StudyEntity study = studyRepository.findByid(studyID);
        StudyDetailDto detailDto = new StudyDetailDto(study);

        LeaderMapping user = userRepository.findByid(study.getUserID());

        detailDto.setLeaderInfo(leaderMap(user));// leader Info add
        detailDto.setStudySub(likeMap(studyID, userID));
        detailDto.setMemberID(membersMap(studyID)); // members add
        detailDto.setMemberList(memberList(studyID)); // memberID only
        studyViewManager(studyID);

        return detailDto;
    }

    // study update
    public Boolean updateStudy(Long studyID, StudyCreateDto createDto) throws IOException {
        StudyEntity studyEntity = studyRepository.findByid(studyID);
        if (studyEntity != null) {
            studyEntity.stuEntUpdate(createDto);
            studyRepository.save(studyEntity);
            return true;
        }
        return null;
    }

    // study delete
    public boolean deleteStudy(Long studyID, Long userID) {
        try {
            StudyEntity study = studyRepository.findByid(studyID);
            if (study.getUserID() == userID) {
                studyRepository.deleteById(studyID);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean submitStudy(StudySubmitDto submitDto) {
        try {
            boolean chk = submitRepository.existsByUserIDAndStudyID(submitDto.getUserID(), submitDto.getStudyID());
            if (!chk) {
                StudySubmitEntity submitEntity = new StudySubmitEntity().createSubmit(submitDto);
                submitRepository.save(submitEntity);
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private boolean likeMap(Long studyID, Long userID) {

        if (userID != 0) {
            System.out.println("userID : " + userID + ", studyID : " + studyID);
            return studyLikeRepository.existsByUserIDAndStudyID(userID, studyID);
        } else {
            return false;
        }
    }

    private List<HashMap<String, Object>> membersMap(Long studyID) {
        List<MemberMapping> memberID = submitRepository.findByStudyIDAndAccept(studyID, true);
        List<HashMap<String, Object>> memList = new ArrayList<>();

        for (int i = 0; i < memberID.size(); i++) {

            LeaderMapping user = userRepository.findByid((long) memberID.get(i).getUserID());
            StudySubmitEntity member = submitRepository.findByUserIDAndStudyID((long) memberID.get(i).getUserID(), studyID);

            HashMap<String, Object> memberInfo = new HashMap<>();
            memberInfo.put("memberID", user.getId());
            memberInfo.put("memberField", member.getField()); //no unique 발생해서 projectID 추가함.
            memberInfo.put("memberName", user.getUserNickName());
            memberInfo.put("memberImg", user.getPicture());
            memberInfo.put("memberIntroduce", user.getIntroduce());
            memberInfo.put("memberInterestSub", user.getUserInterestSub());
            memberInfo.put("memberAbility", user.getUserAbility());

            memList.add(i, memberInfo);
        }
        return memList;
    }

    private List memberList(Long studyID) {
        List<MemberMapping> memberID = submitRepository.findByStudyIDAndAccept(studyID, true);
        List memList = new ArrayList();
        for (int i = 0; i < memberID.size(); i++) {
            LeaderMapping user = userRepository.findByid((long) memberID.get(i).getUserID());
            memList.add(user.getId());
        }
        return memList;
    }

    // leader 정보
    private HashMap<String, Object> leaderMap(LeaderMapping user) {
        HashMap<String, Object> leaderInfo = new HashMap<>();
        leaderInfo.put("leaderID", user.getId());
        leaderInfo.put("leaderName", user.getUserNickName());
        leaderInfo.put("leaderImg", user.getPicture());
        leaderInfo.put("introduce", user.getIntroduce());

        return leaderInfo;
    }

    // 조회수 카운터
    private void studyViewManager(Long projectID) {
        StudyEntity study = studyRepository.findByid(projectID);
        int stuView = study.getStudyView();
        stuView += 1;
        study.updateView(stuView);
        studyRepository.save(study);
    }

    // 조건이 없다.
    private List<StudyDto> filterManger() {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM study ");
        Query query = em.createNativeQuery(sb.toString());
        JpaResultMapper result = new JpaResultMapper();
        return result.list(query, StudyDto.class);
    }

    // filter 조건이 두개
    private List<StudyDto> filterManager(String one, String two, String attribute1, String attribute2) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM study ");
        sb.append("WHERE study_" + attribute1 + " = ? AND study_" + attribute2 + " = ?");
        Query query = em.createNativeQuery(sb.toString()).setParameter(1, one).setParameter(2, two);
        JpaResultMapper result = new JpaResultMapper();
        return result.list(query, StudyDto.class);
    }

    // filter 조건이 한개
    private List<StudyDto> filterManager(String one, String attribute) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT * FROM study ");
        sb.append("WHERE study_" + attribute + " = ?");
        Query query = em.createNativeQuery(sb.toString()).setParameter(1, one);
        JpaResultMapper result = new JpaResultMapper();
        return result.list(query, StudyDto.class);
    }

    // study Filter
    public List<StudyDto> studyFilter(String area, String field) {
        if (Objects.equals(area, "상관없음") && Objects.equals(field, "상관없음")) {
           return filterManger();
        } else if (Objects.equals(area, "상관없음") && !Objects.equals(field, "상관없음")) {
            return filterManager(field, "field");
        } else if (!Objects.equals(area, "상관없음") && Objects.equals(field, "상관없음")) {
            return filterManager(area, "area");
        } else {
            return filterManager(area, field, "area", "field");

        }
    }
}
