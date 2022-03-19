package com.connectbridge.connect_bridge_BE.teampage;

import com.connectbridge.connect_bridge_BE.loginpage.register.data.dto.RegisterDto;
import com.connectbridge.connect_bridge_BE.loginpage.register.data.entity.RegisterEntity;
import com.connectbridge.connect_bridge_BE.loginpage.register.repository.RegisterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class TeamService {
    private final TeamRepository teamRepository;
    private final RegisterRepository registerRepository;

    public TeamService(TeamRepository teamRepository, RegisterRepository registerRepository) {
        this.teamRepository = teamRepository;
        this.registerRepository = registerRepository;
    }

    @Transactional
    public TeamProfileDto getTeamProfile(Long id) {

        TeamProfileDto teamProfileDto = new TeamProfileDto();

        RegisterEntity registerEntity = teamRepository.getById(id);
        teamProfileDto.setId(registerEntity.getId());
        teamProfileDto.setUserName(registerEntity.getUserName());
        teamProfileDto.setUserNickname(registerEntity.getUserNickname());
        teamProfileDto.setUserAbility(registerEntity.getUserAbility());
        teamProfileDto.setUserArea(registerEntity.getUserArea());
        teamProfileDto.setUserInterest(registerEntity.getUserInterest());
        teamProfileDto.setUserIntroduce(registerEntity.getUserIntroduce());
        teamProfileDto.setUserTime(registerEntity.getUserTime());
        return teamProfileDto;
    }

    public List<TeamProfileDto> getList(Pageable pageable, int reqPage){

        // Pageable 설정
        pageable = PageRequest.of(reqPage,3, Sort.by(Sort.Direction.DESC,"id"));

        Page<RegisterEntity> page = teamRepository.findAll(pageable); // DB값 불러옴.

        List<TeamProfileDto> pageDto = page.map(TeamProfileDto::new).getContent(); // List로 받게 바꿔봄 ㅋ

        System.out.println("Service getList 동작 됨" + page);

        return pageDto;
    }


}
