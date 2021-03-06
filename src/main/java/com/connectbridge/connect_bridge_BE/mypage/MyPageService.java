package com.connectbridge.connect_bridge_BE.mypage;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.community.*;
import com.connectbridge.connect_bridge_BE.community.CommunityMainDto;
import com.connectbridge.connect_bridge_BE.community.like.CommunityLikeRepository;

import com.connectbridge.connect_bridge_BE.follow.FollowRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.UpdateRegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.UpdateRegisterDto2;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.outactpage.data.dto.OutActDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.MyProPartInDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.MyProSubmitDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.ProjectDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.SubmitRepository;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudySubmitEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.MyStuSubmitDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyDto;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyPartInDto;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudySubmitRepository;
import com.connectbridge.connect_bridge_BE.teampage.TeamMainDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MyPageService {
    private final FollowRepository followRepository;
    private final RegisterRepository registerRepository;
    private final CommunityRepository communityRepository;
    private final CommunityLikeRepository communityLikeRepository;
    private final SubmitRepository projectSubmitRepository;
    private final StudySubmitRepository studySubmitRepository;
    private final ProjectRepository projectRepository;
    private final StudyRepository studyRepository;
    private final S3Service s3Service;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;

    public List jacksonMap(String json) {
        ObjectMapper mapper = new ObjectMapper();
        //List<Map<Object, Object>> map = mapper.readValue(json, List.class);
        List map = null;
        try {
            map = mapper.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return map;
    }
    //??? ?????? get ????????????
    @Transactional
    public RegisterDto getMyPage(long fromUserId) {
        RegisterDto registerDto = new RegisterDto();

        RegisterEntity registerEntity = registerRepository.getById(fromUserId);
        registerDto.setId(registerEntity.getId());
        registerDto.setUserID(registerEntity.getUserID());
        registerDto.setUserName(registerEntity.getUserName());
        registerDto.setUserEmail(registerEntity.getUserEmail());
        registerDto.setUserNickname(registerEntity.getUserNickname());
        registerDto.setUserIntroduce(registerEntity.getUserIntroduce());
        registerDto.setUserAbility(registerEntity.getUserAbility());
        registerDto.setUserArea(registerEntity.getUserArea());
        registerDto.setUserTime(registerEntity.getUserTime());
        registerDto.setUserPortfolio(registerEntity.getUserPortfolio());
        registerDto.setUserPicture(registerEntity.getUserPicture());
        registerDto.setUserInterestMain(registerEntity.getUserInterestMain());
        registerDto.setUserInterestSub(registerEntity.getUserInterestSub());
        registerDto.setRegisterDate(registerEntity.getCreateDate());
        return registerDto;
    }

    //??? ?????? ????????????
    public void updateMyInfo(UpdateRegisterDto updateRegisterDto) {
        RegisterEntity registerEntity = registerRepository.findByid(updateRegisterDto.getId());
        String encodePassword = passwordEncoder.encode(updateRegisterDto.getUserPW());

        try {
            String url = s3Service.upload(updateRegisterDto.getImg(), "user");
            updateRegisterDto.setUserPicture(url);
            String newUrl = updateRegisterDto.getUserPicture();
            String oldUrl = registerEntity.getUserPicture();

            if (oldUrl == null) { //?????? url ????????????
                registerEntity.updateRegister(encodePassword, updateRegisterDto.getUserNickname(),
                        updateRegisterDto.getUserAbility(), updateRegisterDto.getUserArea(),
                        updateRegisterDto.getUserTime(), updateRegisterDto.getUserInterestMain(),
                        updateRegisterDto.getUserInterestSub(), updateRegisterDto.getUserIntroduce(),
                        updateRegisterDto.getUserPortfolio(), newUrl);
                registerRepository.save(registerEntity);

            } else if (newUrl != null && !newUrl.isEmpty()) {
                s3Service.deleteS3(oldUrl);
                registerEntity.updateRegister(encodePassword, updateRegisterDto.getUserNickname(),
                        updateRegisterDto.getUserAbility(), updateRegisterDto.getUserArea(),
                        updateRegisterDto.getUserTime(), updateRegisterDto.getUserInterestMain(),
                        updateRegisterDto.getUserInterestSub(), updateRegisterDto.getUserIntroduce(),
                        updateRegisterDto.getUserPortfolio(), newUrl);
                registerRepository.save(registerEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void updateMyInfo2(UpdateRegisterDto2 updateRegisterDto2) {
        RegisterEntity registerEntity = registerRepository.findByid(updateRegisterDto2.getId());
        String encodePassword = passwordEncoder.encode(updateRegisterDto2.getUserPW());
        registerEntity.updateRegister2(encodePassword, updateRegisterDto2.getUserNickname(),
                updateRegisterDto2.getUserAbility(), updateRegisterDto2.getUserArea(),
                updateRegisterDto2.getUserTime(), updateRegisterDto2.getUserInterestMain(),
                updateRegisterDto2.getUserInterestSub(), updateRegisterDto2.getUserIntroduce(),
                updateRegisterDto2.getUserPortfolio());
        registerRepository.save(registerEntity);
    }

    //?????? ??? ???????????? ????????? *** ????????? -> DTO ?????? ?????? ***
    public List<MyCommunityDto> getCommunityPage(long fromUserId) {
        RegisterEntity registerEntity = registerRepository.findById(fromUserId).get();

        List<MyCommunityDto> community = communityRepository.findAllByUserIDOrderByIdDesc(registerEntity.getId())
                .stream().map(MyCommunityDto::fromEntity2).collect(Collectors.toList());

        return community;
    }

    //?????? ?????????
    public HashMap myPageSub(long fromUserId){
        HashMap<String,List> page = new HashMap<>();
        page.put("outact",getOutActLike(fromUserId));
        page.put("team",getFollowing(fromUserId));
        page.put("community",getCommunityLike(fromUserId));
        page.put("study", getStudyLike(fromUserId));
        page.put("project", getpProjectLike(fromUserId));

        return page;
    }

    @Transactional //??????????????? ?????? ?????????
    public List<TeamMainDto> getFollowing(long profileId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.user_nickname, u.user_ability, u.user_interest_main, u.user_interest_sub, u.user_introduce,u.user_picture ");
        sb.append("FROM users u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ? ");

        // ?????? ??????
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, profileId);

        //JPA ?????? ?????? - DTO??? ??????
        JpaResultMapper result = new JpaResultMapper();
        List<TeamMainDto> followDtoList = result.list(query, TeamMainDto.class);
        return followDtoList;
    }

    @Transactional //??????????????? ???????????? ?????????
    public List<CommunityMainDto> getCommunityLike(long profileId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT c.id, c.post_title, c.post_hashtag, c.post_contents, c.post_viewcount, c.post_likecount, c.post_commentcount ");
        sb.append("FROM community c, communitylike cl ");
        sb.append("WHERE c.id = cl.to_post_id AND cl.from_user_id = ?");
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, profileId);

        JpaResultMapper result = new JpaResultMapper();
        List<CommunityMainDto> CommunityDtoList = result.list(query, CommunityMainDto.class);
        return CommunityDtoList;
    }

    @Transactional //??????????????? ???????????? ?????????
    public List<OutActDto> getOutActLike(long profileId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT p.id, p.post_name, p.post_image, p.post_view, p.post_likes, p.post_link, p.post_sub, p.create_date ");
        sb.append("FROM post p, outactlike ol ");
        sb.append("WHERE p.id = ol.outact_id AND ol.user_id = ?");

        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, profileId);

        JpaResultMapper result = new JpaResultMapper();
        List<OutActDto> outActDtoList = result.list(query, OutActDto.class);
        return outActDtoList;
    }

    @Transactional //??????????????? ???????????? ?????????
    public List<ProjectDto> getpProjectLike(long profileId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT p.* ");
        sb.append("FROM project p, projectlike pl ");
        sb.append("WHERE p.id = pl.project_id AND pl.user_id = ?");
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, profileId);

        JpaResultMapper result = new JpaResultMapper();
        List<ProjectDto> projectDtoList = result.list(query, ProjectDto.class);
        return projectDtoList;
    }

    @Transactional

    public List<StudyDto> getStudyLike(long profildId){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT s.* ");
        sb.append("FROM study s, studylike sl ");
        sb.append("WHERE s.id = sl.study_id AND sl.user_id = ?");
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, profildId);

        JpaResultMapper result = new JpaResultMapper();
        List<StudyDto> studyDtoList = result.list(query, StudyDto.class);
        return studyDtoList;
    }

    public Boolean checkTeamLike(long fromUserId, long teamID) {
        Boolean checkTeam = followRepository.existsByFromUserIdAndToUserId(fromUserId, teamID);
        return checkTeam;
    }

    @Transactional
    public Boolean checkCoummintyLike(long fromUserId, long communityID) {
        Boolean checkCommunity = communityLikeRepository.existsByFromUserIdAndToPostId(fromUserId, communityID);
        return checkCommunity;
    }

    public HashMap<String, List> getProjectInfo(Long userID) {
        HashMap<String, List> projectList = new HashMap();
        //????????? ???????????? List put
        List<MyProSubmitDto> subList = subedPro(userID).stream().map(MyProSubmitDto::new).collect(Collectors.toList());
        for(int i =0; i<subList.size();i++){
            IdMapping test = projectSubmitRepository.findByProjectIDAndUserID(subList.get(i).getProjectID(),userID);
            subList.get(i).setSubmitID(test.getId());
        }

        List<MyProPartInDto> partList = partInPro(userID).stream().map(MyProPartInDto::new).collect(Collectors.toList());
        for(int i =0; i<partList.size();i++){
            boolean chk = projectSubmitRepository.existsByUserIDAndProjectID(userID,partList.get(i).getProjectID());
            if(chk){
                IdMapping test = projectSubmitRepository.findByProjectIDAndUserID(partList.get(i).getProjectID(),userID);
                partList.get(i).setSubmitID(test.getId());
            }else{
                partList.get(i).setSubmitID(0L);
            }
        }

        List<ProjectDto> completeList = completePro(userID).stream().map(ProjectDto::new).collect(Collectors.toList());

        projectList.put("submitPro", subList);
        projectList.put("partInPro",partList);
        projectList.put("completePro", completeList);

        return projectList;
    }

    //????????? ???????????? List ??????.
    private List<ProjectEntity> subedPro(Long userID) {
        List<SubmitEntity> subList = projectSubmitRepository.findByUserIDAndAccept(userID, false);
        List<ProjectEntity> proList = new ArrayList<>();
        for (int i = 0; i < subList.size(); i++) {
            ProjectEntity target = projectRepository.findByid(subList.get(i).getProjectID());
            if (target != null) {
                proList.add(target);
            }
        }
        return proList;
    }

    // ???????????? ???????????? List ??????.
    private List<ProjectEntity> partInPro(Long userID) {
        List<SubmitEntity> partInList = projectSubmitRepository.findByUserIDAndAccept(userID, true);
        List<IdMapping> leaderList = projectRepository.findByUserIDAndProjectOnOff(userID, true);
        List<ProjectEntity> proList = new ArrayList<>();
        // ????????? ????????? ????????????
        for (int i = 0; i < partInList.size(); i++) {
            ProjectEntity target = projectRepository.findByidAndProjectOnOff(partInList.get(i).getProjectID(), true);
            if (target != null) {
                proList.add(target);
            }
        }
        // ????????? ????????? ????????????
        for(int j=0;j<leaderList.size();j++){
            ProjectEntity target = projectRepository.findByid(leaderList.get(j).getId());
            if(target != null){
                proList.add(target);
            }
        }

        System.out.println("?????? ??????2");

        return proList;
    }

    // ????????? ????????????
    private List<ProjectEntity> completePro(Long userID) {
        List<SubmitEntity> partInList = projectSubmitRepository.findByUserIDAndAccept(userID, true);
        List<IdMapping> leaderList = projectRepository.findByUserIDAndProjectOnOff(userID, false);
        List<ProjectEntity> proList = new ArrayList<>();
        for (int i = 0; i < partInList.size(); i++) {
            ProjectEntity target = projectRepository.findByidAndProjectOnOff(partInList.get(i).getProjectID(), false);
            if (target != null) {
                proList.add(target);
            }
        }
        for(int j=0;j<leaderList.size();j++){
            ProjectEntity target = projectRepository.findByid(leaderList.get(j).getId());
            if(target != null){
                proList.add(target);
            }
        }
        return proList;
    }

    public HashMap<String, List> getStudyInfo(Long userID) {
        HashMap<String, List> studyList = new HashMap();
        //????????? ???????????? List put
        List<MyStuSubmitDto> subList = subedStu(userID).stream().map(MyStuSubmitDto::new).collect(Collectors.toList());

        for(int i =0; i<subList.size();i++){
            IdMapping test = studySubmitRepository.findByStudyIDAndUserID(subList.get(i).getStudyID(),userID);
            subList.get(i).setSubmitID(test.getId());
        }

        List<StudyPartInDto> partList = partInStu(userID).stream().map(StudyPartInDto::new).collect(Collectors.toList());
        for(int i =0; i<partList.size();i++){
            boolean chk = studySubmitRepository.existsByUserIDAndStudyID(userID,partList.get(i).getStudyID());
            if(chk){
                IdMapping test = studySubmitRepository.findByStudyIDAndUserID(partList.get(i).getStudyID(),userID);
                partList.get(i).setSubmitID(test.getId());
            }else{
                partList.get(i).setSubmitID(0L);
            }
        }

        List<StudyDto> completeList = completeStu(userID).stream().map(StudyDto::new).collect(Collectors.toList());

        studyList.put("submitStu", subList);
        studyList.put("partInStu",partList);
        studyList.put("completeStu", completeList);

        return studyList;
    }

    //????????? ????????? List ??????.
    private List<StudyEntity> subedStu(Long userID) {
        List<StudySubmitEntity> subList = studySubmitRepository.findByUserIDAndAccept(userID, false);
        List<StudyEntity> stuList = new ArrayList<>();
        for (int i = 0; i < subList.size(); i++) {
            StudyEntity target = studyRepository.findByid(subList.get(i).getStudyID());
            if (target != null) {
                stuList.add(target);
            }
        }
        return stuList;
    }

    // ???????????? ????????? List ??????.
    private List<StudyEntity> partInStu(Long userID) {
        List<StudySubmitEntity> partInList = studySubmitRepository.findByUserIDAndAccept(userID, true);
        List<IdMapping> leaderList = studyRepository.findByUserIDAndStudyOnOff(userID, true);
        List<StudyEntity> stuList = new ArrayList<>();
        // ????????? ????????? ????????????
        for (int i = 0; i < partInList.size(); i++) {
            StudyEntity target = studyRepository.findByidAndStudyOnOff(partInList.get(i).getStudyID(), true);
            if (target != null) {
                stuList.add(target);
            }
        }
        // ????????? ????????? ?????????
        for(int j=0;j<leaderList.size();j++){
            StudyEntity target = studyRepository.findByid(leaderList.get(j).getId());
            if(target != null){
                stuList.add(target);
            }
        }

        System.out.println("?????? ??????2");

        return stuList;
    }

    // ????????? ????????? List ??????
    private List<StudyEntity> completeStu(Long userID) {
        List<StudySubmitEntity> partInList = studySubmitRepository.findByUserIDAndAccept(userID, true);
        List<IdMapping> leaderList = studyRepository.findByUserIDAndStudyOnOff(userID, false);
        List<StudyEntity> stuList = new ArrayList<>();
        for (int i = 0; i < partInList.size(); i++) {
            StudyEntity target = studyRepository.findByidAndStudyOnOff(partInList.get(i).getStudyID(), false);
            if (target != null) {
                stuList.add(target);
            }
        }
        for(int j=0;j<leaderList.size();j++){
            StudyEntity target = studyRepository.findByid(leaderList.get(j).getId());
            if(target != null){
                stuList.add(target);
            }
        }
        return stuList;
    }
}
