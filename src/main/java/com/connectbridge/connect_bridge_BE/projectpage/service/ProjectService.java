package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.CreateReqDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.DetailedDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.ProjectDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.UpdateReqDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.Project;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDto> providePage(){
        List<Project> list = projectRepository.findAll();
        List<ProjectDto> dtoList = list.stream().map(l-> modelMapper.map(l,ProjectDto.class)).collect(Collectors.toList());
        System.out.println(dtoList);
        //Failed to instantiate instance of destination
        return dtoList;
    }

    // 상세 조회.
    public DetailedDto inquiryProject(Long projectID){
        Project project = projectRepository.findByProjectID(projectID);
        DetailedDto detailedDto = modelMapper.map(project,DetailedDto.class);
        return detailedDto;
    }

    // 생성.
    public void createProject(CreateReqDto createDto) {
        projectRepository.save(createDto.Create());
    }

    // 삭제
    public void deleteProject(Long projectID) {
        projectRepository.deleteById(projectID);
    }
/*
    // 업데이트
    public void updateProject(UpdateReqDto updateDto) {
        Project project = projectRepository.findByProjectID(updateDto.getProjectID());
        project.update(updateDto);
        projectRepository.save(project);

        // modelMapper.map(project.get(), UpdateReqDto.class);
    }

 */
}
