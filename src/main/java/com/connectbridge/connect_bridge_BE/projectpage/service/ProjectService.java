package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.loginpage.login.data.entity.User;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.LeaderMapping;
import com.connectbridge.connect_bridge_BE.loginpage.login.repository.UserRepository;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.*;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final S3Service s3Service;
    private final SubmitRepository submitRepository;
    private final UserRepository userRepository;
    private final ProjectLikeRepository projectLikeRepository;

    public ProjectService(ProjectRepository projectRepository, S3Service s3Service, SubmitRepository submitRepository, UserRepository userRepository, ProjectLikeRepository projectLikeRepository) {
        this.projectRepository = projectRepository;
        this.s3Service = s3Service;
        this.submitRepository = submitRepository;
        this.userRepository = userRepository;
        this.projectLikeRepository = projectLikeRepository;
    }

    // 페이징
    public List<ProjectDto> pagingProject(Pageable pageable,int reqPage) {
        pageable = PageRequest.of(reqPage,5, Sort.by(Sort.Direction.DESC,"id"));
        Page<ProjectEntity> page = projectRepository.findAll(pageable);
        List<ProjectDto> pageDto = page.map(ProjectDto::new).getContent();
        System.out.println("Project GetList 동작");
        return pageDto;
    }

    // 생성
    public void createProject(CreateDto createDto) {
        ProjectEntity projectEntity = new ProjectEntity().createProject(createDto);
        projectRepository.save(projectEntity);
    }

    // 상세
    public DetailDto detailProject(Long projectID, Long userID) {
        ProjectEntity project = projectRepository.findByid(projectID);
        DetailDto detailDto = new DetailDto(project);

        LeaderMapping user = userRepository.findByid(project.getUserID());

        detailDto.setLeaderInfo(leaderMap(user)); // leader Info add

        //detailDto.setProjectSub(likeMap(projectID,userID));// subInfo add
        detailDto.setProjectSub(likeMap(projectID,userID)); //no hashMap

        detailDto.setMemberID(membersMap(projectID)); // members add

        projectViewManager(projectID);

        return detailDto;
    }

/*
    // 혹시 헤쉬안에 넣어야하니?
    private HashMap<String,Object> setTestHashMeth(Long projectID, Long userID){
        HashMap<String,Object> testHashMap = new HashMap<>();
        ProjectEntity project = projectRepository.findByid(projectID);
        LeaderMapping user = userRepository.findByid(project.getUserID());
        testHashMap.put("subMap",subMap(projectID, userID));
        testHashMap.put("memberMap",membersMap(projectID));
        testHashMap.put("leaderMap",leaderMap(user));
        return testHashMap;
    }
*/

    private boolean likeMap(Long projectID, Long userID){

        if(userID !=0){
            System.out.println("userID : " +userID + ", projectID : "+projectID);
            return projectLikeRepository.existsByUserIDAndProjectID(userID, projectID);
        }else {
            return false;
        }
    }
    /*
    // like 정보
    private HashMap<String, Object> likeMap(Long projectID, Long userID){
        HashMap<String,Object> subInfo = new HashMap<>();

        if(userID !=0){
            System.out.println("userID : " +userID + ", projectID : "+projectID);
            subInfo.put("projectSub",projectLikeRepository.existsByUserIDAndProjectID(userID, projectID));
        }else{
            subInfo.put("projectSub", false);
        }
        return subInfo;
    }

     */

    // member 정보
    private List<Integer> membersMap(Long projectID) {
        List<MemberMapping> memberID = submitRepository.findByProjectIDAndAccept(projectID,true);
        List<Integer> memList = new ArrayList<>();

        for(int i =0; i< memberID.size();i++){
            memList.add(i,memberID.get(i).getUserID());
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

    // 갱신
    public Boolean updateProject(Long projectID, MultipartFile projectImg, CreateDto createDto) throws IOException {
        ProjectEntity projectEntity = projectRepository.findByid(projectID);

        if (projectEntity != null) {
            String now = s3Service.upload(projectImg, "project");
            createDto.setProjectStrImg(now);

            String old = projectEntity.getProjectImg();
            s3Service.deleteS3(old);

            projectEntity.proEntUpdate(createDto);
            projectRepository.save(projectEntity);
            return true;
        }
        return null;
    }

    // 삭제
    public boolean deleteProject(Long projectID) {
        try {
            ProjectEntity project = projectRepository.findByid(projectID);
            String path = project.getProjectImg();
            s3Service.deleteS3(path);

            projectRepository.deleteById(projectID);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    // 지원 신청
    public boolean submitProject(SubmitDto submitDto) {
        try {
            boolean chker = submitRepository.existsByUserIDAndProjectID(submitDto.getUserID(), submitDto.getProjectID());
            if (!chker) {
                SubmitEntity submitEntity = new SubmitEntity().createSubmit(submitDto);
                submitRepository.save(submitEntity);
                System.out.println("submit save done");
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("submit save fail");
            return false;
        }
        return false;
    }

    // 조회수 카운터
    private void projectViewManager(Long projectID){
        ProjectEntity project = projectRepository.findByid(projectID);
        int proView = project.getProjectView();
        proView += 1;
        project.updateView(proView);
        projectRepository.save(project);
    }

    // 상세 페이지- 공지
    public void projectNoticeCreate(Long userID, Long projectID, String content){
        ProjectEntity projectEntity = projectRepository.findByid(projectID);
        if(projectEntity.getUserID().equals(userID)){

        }
    }
}

