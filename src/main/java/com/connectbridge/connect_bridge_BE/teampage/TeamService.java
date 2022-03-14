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
        teamProfileDto.setRegisterDto(RegisterDto.builder()
                .ID(registerEntity.getId())
                .userName(registerEntity.getUserName())
                .userNickname(registerEntity.getUserNickname())
                .userAbility(registerEntity.getUserAbility())
                .userInterest(registerEntity.getUserInterest())
                .userIntroduce(registerEntity.getUserIntroduce())
                .userArea(registerEntity.getUserArea())
                .userTime(registerEntity.getUserTime())
                .build());
        return teamProfileDto;
    }

}
