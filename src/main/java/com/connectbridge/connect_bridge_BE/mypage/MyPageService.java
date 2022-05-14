package com.connectbridge.connect_bridge_BE.mypage;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.community.*;
import com.connectbridge.connect_bridge_BE.community.like.CommunityLikeRepository;
import com.connectbridge.connect_bridge_BE.follow.Follow;
import com.connectbridge.connect_bridge_BE.follow.FollowDto;
import com.connectbridge.connect_bridge_BE.follow.FollowRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.UpdateRegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.UpdateRegisterDto2;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import com.connectbridge.connect_bridge_BE.outactpage.repository.OutActLikeRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectLikeRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
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
    private final CommunityLikeRepository communityLikeRepository;
    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectRepository projectRepository;
    private final FollowRepository followRepository;
    private final OutActLikeRepository outActLikeRepository;
    private final RegisterRepository registerRepository;
    private final CommunityService communityService;
    private final CommunityRepository communityRepository;
    private final StudyRepository studyRepository;
    private final S3Service s3Service;
    private final PasswordEncoder passwordEncoder;
    private final EntityManager em;

    //내 정보 get 불러오기
    @Transactional
    public RegisterDto getMyPage(long fromUserId){
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

    //내 정보 수정하기
    public void updateMyInfo(UpdateRegisterDto updateRegisterDto){
        RegisterEntity registerEntity = registerRepository.findByid(updateRegisterDto.getId());
        String encodePassword = passwordEncoder.encode(updateRegisterDto.getUserPW());

        try {
            String url = s3Service.upload(updateRegisterDto.getImg(), "user");
            updateRegisterDto.setUserPicture(url);
            String newUrl = updateRegisterDto.getUserPicture();
            String oldUrl = registerEntity.getUserPicture();

            if (oldUrl == null){ //기존 url 안뀌어도
                registerEntity.updateRegister(encodePassword, updateRegisterDto.getUserNickname(),
                        updateRegisterDto.getUserAbility(), updateRegisterDto.getUserArea(),
                        updateRegisterDto.getUserTime(), updateRegisterDto.getUserInterestMain(),
                        updateRegisterDto.getUserInterestSub(), updateRegisterDto.getUserIntroduce(),
                        updateRegisterDto.getUserPortfolio(), newUrl);
                registerRepository.save(registerEntity);

            }else if (newUrl != null && !newUrl.isEmpty()) {
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
    public void updateMyInfo2(UpdateRegisterDto2 updateRegisterDto2){
        RegisterEntity registerEntity = registerRepository.findByid(updateRegisterDto2.getId());
        String encodePassword = passwordEncoder.encode(updateRegisterDto2.getUserPW());
        registerEntity.updateRegister2(encodePassword, updateRegisterDto2.getUserNickname(),
                updateRegisterDto2.getUserAbility(), updateRegisterDto2.getUserArea(),
                updateRegisterDto2.getUserTime(), updateRegisterDto2.getUserInterestMain(),
                updateRegisterDto2.getUserInterestSub(), updateRegisterDto2.getUserIntroduce(),
                updateRegisterDto2.getUserPortfolio());
        registerRepository.save(registerEntity);
    }

    //내가 쓴 커뮤니티 페이지 *** 엔티티 -> DTO 변경 기능 ***
    public List<MyCommunityDto> getCommunityPage(long fromUserId){
        RegisterEntity registerEntity = registerRepository.findById(fromUserId).get();

        List<MyCommunityDto> community = communityRepository.findAllCommunityByUserNicknameOrderByIdDesc(registerEntity.getUserNickname())
                .stream().map(MyCommunityDto::fromEntity2).collect(Collectors.toList());

        return community;
    }

    //구독 페이지
    public HashMap myPageSub(long fromUserId){
        //List<ProjectEntity> likeProject = projectLikeRepository.findProjectIDByUserID(fromUserId);
        //List<CommunityEntity> likeCommunity = communityLikeRepository.findByUserIDOrderByIdDesc(fromUserId);

        //List<OutAct> likeOutact = outActLikeRepository.findIdByUserIDOrderByIdDesc(fromUserId);
        //List<StudyEntity> likeStudy = studyRepository.findIdByUserIDOrderByIdDesc(fromUserId);


        HashMap<String,List> page = new HashMap<>();
        //page.put("project", likeProject);
        //page.put("study", likeStudy);
        //page.put("community",likeCommunity);
        page.put("team",getFollowing(fromUserId));
        //page.put("outact",likeOutact);

        return page;
    }
    @Transactional //구독페이지 사람 팔로우
    public List<FollowDto> getFollowing(long profileId) {
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.user_name, u.user_nickname ");
        sb.append("FROM users u, follow f ");
        sb.append("WHERE u.id = f.to_user_id AND f.from_user_id = ?");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, profileId);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<FollowDto> followDtoList = result.list(query, FollowDto.class);
        return followDtoList;
    }

    // 유저가 지원한 projet & study.
    // 참여해서 진행 중인 project & study + 리더로 진행 중인 프로젝트.
    // 완료된 프로젝트, 스터디.
    public void getProStu(){
        HashMap proStuList = new HashMap();
        // submit.findByUserIDAndAccept(userID,false); -> 지원한 프로젝트.->accept 0
        // stusubmit.findByUserID(userID);-> 지원한 스터디. //list로 가지고 있는다면?
        // submit.find.ByUserIDAndAccept(userID,true) -> 참여중인 프로젝트. -> accept 1
        // project.findByUserIDAndOnOff(userID,true) -> 자신이 리더인 프로젝트
        // 완료현황 체크를 어떻게 할 것인가?
        // 조건 : onoff=1(종료)
        // 1안. findByOnOff(true).getID ->
        //-> 완료된 프로젝트 목록 챙김.
    }
}
