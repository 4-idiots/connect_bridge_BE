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
import java.util.List;

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
/*
    public List<ProjectEntity> pagingProject(){
        List<ProjectEntity> paging = projectRepository.findAll();
        return paging;
    }
 */

    // 생성
    public void createProject(CreateDto createDto){
        /*
        Project create-> Apply create(project_id is fk).
        -Multipart Img. clear.
        -platform, content 변환해서 보내기.
        */
        ProjectEntity projectEntity = new ProjectEntity().createProject(createDto);
        projectRepository.save(projectEntity);
    }

    // 상세
    public DetailDto detailProject(Long projectID){
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

        if(projectEntity != null) {
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
    public boolean deleteProject(Long projectID){
        try {
            ProjectEntity project = projectRepository.findByid(projectID);
            String path = project.getProjectImg();
            s3Service.deleteS3(path);

            projectRepository.deleteById(projectID);

            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    public boolean submitProject(SubmitDto submitDto){
        try {
            boolean chker = submitRepository.existsByUserIDAndProjectID(submitDto.getUserID(), submitDto.getProjectID());
            if (!chker) {
                SubmitEntity submitEntity = new SubmitEntity().createSubmit(submitDto);
                submitRepository.save(submitEntity);
                return true;
            }
        }catch(Exception e){
            System.out.println(e);
                return false;
            }
            return false;
        }
}
