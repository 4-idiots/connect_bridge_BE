package com.connectbridge.connect_bridge_BE.studypage.Service;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.LeaderMapping;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudySubmitEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyCreateDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDetailDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudySubmitDto;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudySubmitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyService {
    private final S3Service s3Service;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final StudySubmitRepository submitRepository;

    public StudyService(S3Service s3Service, StudyRepository studyRepository, UserRepository userRepository, StudySubmitRepository submitRepository) {
        this.s3Service = s3Service;
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
        this.submitRepository = submitRepository;
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

    // 카드 정보 제공.
    public StudyDetailDto clickStudy(Long studyID,Long userID){
        StudyEntity study = studyRepository.findByid(studyID);
        StudyDetailDto detailDto = new StudyDetailDto(study);

        LeaderMapping user = userRepository.findByid(study.getUserID());

        //detailDto.setLeaderInfo(leaderMap(user));// leader Info add
        //detailDto.setStudySub(likeMap(studyID,userID));
        //detailDto.setMemberID(membersMap(studyID)); // members add
        //detailDto.setMemberList(memberList(studyID)); // memberID only
        //studyViewManager(studyID)

        return detailDto;
    }

    // study update
    public Boolean updateStudy(Long studyID, MultipartFile studyImg, StudyCreateDto createDto) throws IOException {
        StudyEntity studyEntity = studyRepository.findByid(studyID);
        if(studyEntity != null){
            String now = s3Service.upload(studyImg,"study");
            createDto.setStudyStrImg(now);

            String old = studyEntity.getStudyImg();
            s3Service.deleteS3(old);

            studyEntity.stuEntUpdate(createDto);
            studyRepository.save(studyEntity);
            return true;
        }
        return null;
    }

    // study delete
    public boolean deleteStudy(Long studyID, Long userID){
        try{
            StudyEntity study = studyRepository.findByid(studyID);
            if(study.getUserID()==userID){
                String path = study.getStudyImg();
                s3Service.deleteS3(path);
                studyRepository.deleteById(studyID);
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }

    public boolean submitStudy(StudySubmitDto submitDto){
        try{
            boolean chk = submitRepository.existsByUserIDAndStudyID(submitDto.getUserID(),submitDto.getStudyID());
            if(!chk){
                StudySubmitEntity submitEntity = new StudySubmitEntity().createSubmit(submitDto);
                submitRepository.save(submitEntity);
                return true;
            }
            return false;
        }catch (Exception e){
            System.out.println(e);
            return false;
        }
    }


    /*
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
            memberInfo.put("memberName",user.getUserNickName());
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
