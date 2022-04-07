package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.*;
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

    // 페이징
    public List<ProjectDto> pageProject() {
        List<Project> list = projectRepository.findAll();
        return list.stream().map(Project::toProjectDto).collect(Collectors.toList());
    }

    // 상세 페이지
    public DetailedDto inquiryProject(Long projectID) {
        Project project = projectRepository.findByProjectID(projectID);

        //DetailedDto dto = modelMapper.map(project,DetailedDto.class);
        return new DetailedDto(project);
    }

    // 생성
    public void createProject(CreateDto createReqDto) {
        projectRepository.save(createReqDto.Create());
    }

    // 삭제
    public void deleteProject(Long projectID) {
        projectRepository.deleteById(projectID);
    }

    // 업데이트
    public void updateProject(UpdateReqDto updateDto) {
        Project project = projectRepository.findByProjectID(updateDto.getProjectID());
        project.update(updateDto);
        projectRepository.save(project);
    }
}
