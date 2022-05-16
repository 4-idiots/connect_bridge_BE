package com.connectbridge.connect_bridge_BE.studypage.Service;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.NoticeDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectNoticeEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyNoticeEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudyNoticeDto;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyNoticeRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudyNoticeService {
    private final StudyRepository studyRepository;
    private final StudyNoticeRepository studyNoticeRepository;

    public StudyNoticeService(StudyRepository studyRepository, StudyNoticeRepository studyNoticeRepository) {
        this.studyRepository = studyRepository;
        this.studyNoticeRepository = studyNoticeRepository;
    }

    public boolean createNotice(Long userID, StudyNoticeDto noticeDto){
        //p_id로 찾은 프로젝트에서 userID가져옴.
        StudyEntity studyEntity = studyRepository.findByid(noticeDto.getStudyID());
        //입력된 유저와 같으면 -> 리더가 맞음
        if(studyEntity.getUserID().equals(userID)){
            StudyNoticeEntity noticeEntity = new StudyNoticeEntity().createNotice(noticeDto.getStudyID(), noticeDto.getContent());
            //입력된 p_id, content 저장.
            studyNoticeRepository.save(noticeEntity);
            return true;
        }
        System.out.println("notice 저장 실패.");
        return false;
    }

    public List<StudyNoticeDto> mainNotice(Long studyID){
        List<StudyNoticeEntity> entityList = studyNoticeRepository.findByStudyID(studyID,Sort.by(Sort.Direction.DESC,"id"));
        List<StudyNoticeDto> dtoList = entityList.stream().map(StudyNoticeDto::toEntity).collect(Collectors.toList());
        return dtoList;
    }
}
