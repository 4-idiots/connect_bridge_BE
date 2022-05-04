package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.*;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.SubmitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final S3Service s3Service;
    private final SubmitRepository submitRepository;

    @Autowired
    private ModelMapper modelMapper;

    public ProjectService(ProjectRepository projectRepository, S3Service s3Service, SubmitRepository submitRepository) {
        this.projectRepository = projectRepository;
        this.s3Service = s3Service;
        this.submitRepository = submitRepository;
    }


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
    public DetailDto detailProject(Long projectID) {
        ProjectEntity project = projectRepository.findByid(projectID);
        DetailDto detailDto = new DetailDto(project);
        projectViewManager(projectID);
        return detailDto;
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
            ProjectEntity project = projectRepository.findByid(submitDto.getProjectID());
            //project.get(submitDto.getField());
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

    private void projectViewManager(Long projectID){
        ProjectEntity project = projectRepository.findByid(projectID);
        int proView = project.getProjectView();
        proView += 1;
        project.updateView(proView);
        projectRepository.save(project);
    }


}

