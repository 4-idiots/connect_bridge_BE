package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.CreateReqDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.PatchInfoDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    // 생성.
    public void createProject(CreateReqDto createReqDto){
        projectRepository.save(createReqDto.CreateReqDto());
    }

    // 삭제
    public void deleteProject(Long projectID){
        projectRepository.deleteById(projectID);
    }

    // 수정 정보
    public PatchInfoDto provideInfo(Long projectID){
        Optional<Project> project = projectRepository.findById(projectID);
        System.out.println(project.get());
        return modelMapper.map(project,PatchInfoDto.class);
    }

}
