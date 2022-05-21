package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.follow.FollowRepository;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EntityManager;
import javax.persistence.Query;
@RequiredArgsConstructor
@Slf4j
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final RegisterRepository registerRepository;
    private final FollowRepository followRepository;
    private final EntityManager em;

    @Transactional
    public TeamProfileDto getTeamProfile(long fromUserId, long toUserId) {

        TeamProfileDto teamProfileDto = new TeamProfileDto();

        RegisterEntity registerEntity = teamRepository.getById(toUserId);
        teamProfileDto.setMyid(registerEntity.getId());
        teamProfileDto.setUserName(registerEntity.getUserName());
        teamProfileDto.setUserNickname(registerEntity.getUserNickname());
        teamProfileDto.setUserAbility(registerEntity.getUserAbility());
        teamProfileDto.setUserArea(registerEntity.getUserArea());
        teamProfileDto.setUserInterestMain(registerEntity.getUserInterestMain());
        teamProfileDto.setUserInterestSub(registerEntity.getUserInterestSub());
        teamProfileDto.setUserPicture(registerEntity.getUserPicture());
        teamProfileDto.setUserIntroduce(registerEntity.getUserIntroduce());
        teamProfileDto.setUserTime(registerEntity.getUserTime());
        teamProfileDto.setUserPortfolio(registerEntity.getUserPortfolio());
        if (fromUserId != 0){
            if (followRepository.findByFromUserIdAndToUserId(fromUserId, toUserId) != null){
                teamProfileDto.setFollow(Long.valueOf(2));
                teamProfileDto.setColor("danger");//팔로우 함
            }else {teamProfileDto.setFollow(Long.valueOf(1));
                teamProfileDto.setColor("black");
            ;} //팔로우 안함
        }else{
            teamProfileDto.setFollow(Long.valueOf(3)); //로그인 안한 사람
        }
        return teamProfileDto;

    }

    public List<TeamMainDto> getList(Pageable pageable, int reqPage){

        // Pageable 설정
        pageable = PageRequest.of(reqPage,12, Sort.by(Sort.Direction.DESC,"id"));

        Page<RegisterEntity> page = teamRepository.findAll(pageable); // DB값 불러옴.

        List<TeamMainDto> pageDto = page.map(TeamMainDto::new).getContent();

        return pageDto;
    }

    public List<TeamMainDto> getArea(String area, String time, String interest){
        StringBuffer sb = new StringBuffer();
        sb.append("SELECT u.id, u.user_nickname, u.user_ability, u.user_interest_main, u.user_interest_sub, u.user_introduce,u.user_picture ");
        sb.append("FROM users u ");
        sb.append("WHERE u.user_area = ? AND u.user_time = ? AND u.user_interest_main = ?");

        // 쿼리 완성
        Query query = em.createNativeQuery(sb.toString())
                .setParameter(1, area)
                .setParameter(2, time)
                .setParameter(3, interest);

        //JPA 쿼리 매핑 - DTO에 매핑
        JpaResultMapper result = new JpaResultMapper();
        List<TeamMainDto> AreaFilter = result.list(query, TeamMainDto.class);
        return AreaFilter;
    }


}
