package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.amazonS3.S3Service;
import com.connectbridge.connect_bridge_BE.projectpage.data.dto.*;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.SubmitRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

<<<<<<< HEAD
    public List<ProjectEntity> pagingProject(){
=======
    public List<ProjectEntity> pagingProject() {
>>>>>>> main
        List<ProjectEntity> paging = projectRepository.findAll();
        Long count = projectRepository.count();
        //List<ProjectDto> page = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            System.out.println(paging.get(i));
        }
        //List<ProjectDto> page = paging.stream().map(p-> modelMapper.map(p,ProjectDto.class)).collect(Collectors.toList());
        return paging;
    }
<<<<<<< HEAD

=======
>>>>>>> main

    // 생성
    public void createProject(CreateDto createDto) {
        /*
        Project create-> Apply create(project_id is fk).
        -Multipart Img. clear.
        -platform, content 변환해서 보내기.
        */
        ProjectEntity projectEntity = new ProjectEntity().createProject(createDto);
        projectRepository.save(projectEntity);
    }

    // 상세
    public DetailDto detailProject(Long projectID) {
        ProjectEntity project = projectRepository.findByid(projectID);
        DetailDto detailDto = new DetailDto(project);
        return detailDto;
    }

    // 갱신
    /* 갱신
        1. new Img upload to s3 server
        2. new Img url save to String now
        3. old Img url get to projectEntity
        4. delete old Img to s3 server
         */
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
    /*
    1. apply table delete
    2. s3 server Img delete
    3. project table delete
     */
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

    // 지원.
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

}

