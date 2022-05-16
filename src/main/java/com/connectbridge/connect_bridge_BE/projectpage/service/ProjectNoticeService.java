package com.connectbridge.connect_bridge_BE.projectpage.service;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.NoticeDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectNoticeEntity;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectNoticeRepository;
import com.connectbridge.connect_bridge_BE.projectpage.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectNoticeService {

    private final ProjectRepository projectRepository;
    private final ProjectNoticeRepository projectNoticeRepository;
    private ModelMapper modelMapper;
    public ProjectNoticeService(ProjectRepository projectRepository, ProjectNoticeRepository projectNoticeRepository) {
        this.projectRepository = projectRepository;
        this.projectNoticeRepository = projectNoticeRepository;
    }

    public boolean createNotice(Long userID,NoticeDto noticeDto){
        //p_id로 찾은 프로젝트에서 userID가져옴.
        ProjectEntity projectEntity = projectRepository.findByid(noticeDto.getProjectID());
        //입력된 유저와 같으면 -> 리더가 맞음
        if(projectEntity.getUserID().equals(userID)){
            ProjectNoticeEntity projectNoticeEntity = new ProjectNoticeEntity().createNotice(noticeDto.getProjectID(), noticeDto.getContent());
            //입력된 p_id, contnet 저장.
            projectNoticeRepository.save(projectNoticeEntity);
            return true;
        }
        System.out.println("notice 저장 실패.");
        return false;
    }

    public List<NoticeDto> mainNotice(Long projectID){
        List<ProjectNoticeEntity> entityList = projectNoticeRepository.findByProjectID(projectID,Sort.by(Sort.Direction.DESC,"id"));
        List<NoticeDto> dtoList = entityList.stream().map(NoticeDto::toEntity).collect(Collectors.toList());

        return dtoList;
    }
}
