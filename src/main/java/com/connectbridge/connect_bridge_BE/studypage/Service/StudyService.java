package com.connectbridge.connect_bridge_BE.studypage.Service;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.LeaderMapping;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.MemberMapping;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyCreateDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDetailDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDto;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {
    private final S3Service s3Service;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    public StudyService(S3Service s3Service, StudyRepository studyRepository, UserRepository userRepository) {
        this.s3Service = s3Service;
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
    }
    // 페이징
    public List<StudyDto> studyGetPage(Pageable pageable, int reqPage) {
        pageable = PageRequest.of(reqPage,5, Sort.by(Sort.Direction.DESC,"id"));
        Page<StudyEntity> page = studyRepository.findAll(pageable);
        List<StudyDto> pageDto = page.map(StudyDto::new).getContent();
        System.out.println("study main get 동작");
        return pageDto;
    }

    // 게시글 top4
    public List<StudyDto> studyGetTop(){
        List<StudyEntity> page = studyRepository.findTop4ByOrderByIdDesc();
        List<StudyDto> pageDto = page.stream().map(StudyDto::new).collect(Collectors.toList());
        return pageDto;
    }

    // 생성
    public void createStudy(StudyCreateDto studyCreateDto){
        StudyEntity studyEntity = new StudyEntity().createStudy(studyCreateDto);
        studyRepository.save(studyEntity);
    }
/*
    // 카드 클릭
    public void clickStudy(Long studyID,Long userID){
        StudyEntity study = studyRepository.findByid(studyID);
        StudyDetailDto detailDto = new StudyDetailDto(study);

        LeaderMapping user = userRepository.findByid(study.getUserID());
        detailDto.setLeaderInfo(leaderMap(user));// leader Info add
        detailDto.setStudySub(likeMap(studyID,userID)); //no hashMap
        detailDto.setMemberID(membersMap(studyID)); // members add
    }



    private boolean likeMap(Long studyID, Long userID){

        if(userID !=0){
            System.out.println("userID : " +userID + ", studyID : "+studyID);
            return studyLikeRepository.existsByUserIDAndProjectID(userID, studyID);
        }else {
            return false;
        }
    }

    private List<HashMap<String,Object>> membersMap(Long studyID) {
        List<MemberMapping> memberID = studySubmitRepository.findByProjectIDAndAccept(studyID,true);
        List<HashMap<String,Object>> memList = new ArrayList<>();

        for(int i =0; i< memberID.size();i++){

            LeaderMapping user = userRepository.findByid((long) memberID.get(i).getUserID());
            HashMap<String,Object> memberInfo = new HashMap<>();
            memberInfo.put("memberID",user.getId());
            memberInfo.put("memberName",user.getUserName());
            memberInfo.put("memberImg",user.getPicture());
            memberInfo.put("Introduce",user.getIntroduce());

            memList.add(i,memberInfo);
        }
        return memList;
    }



    // leader 정보
    private HashMap<String,Object> leaderMap(LeaderMapping user){
        HashMap<String,Object> leaderInfo = new HashMap<>();
        leaderInfo.put("leaderID", user.getId());
        leaderInfo.put("leaderName",user.getUserName());
        leaderInfo.put("leaderImg", user.getPicture());
        leaderInfo.put("introduce", user.getIntroduce());

        return leaderInfo;
    }


    // 조회수 카운터
    private void studyViewManager(Long projectID){
        StudyEntity study = studyRepository.findByid(projectID);
        int stuView = study.getStudyView();
        stuView += 1;
        study.updateView(stuView);
        studyRepository.save(study);
    }
*/
}
