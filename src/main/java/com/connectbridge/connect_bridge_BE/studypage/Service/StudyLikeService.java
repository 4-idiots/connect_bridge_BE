package com.connectbridge.connect_bridge_BE.studypage.Service;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyLikeEntity;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyLikeRepository;
import com.connectbridge.connect_bridge_BE.studypage.repository.StudyRepository;
import org.springframework.stereotype.Service;

@Service
public class StudyLikeService {
    private final StudyLikeRepository studyLikeRepository;
    private final StudyRepository studyRepository;

    public StudyLikeService(StudyLikeRepository studyLikeRepository, StudyRepository studyRepository) {
        this.studyLikeRepository = studyLikeRepository;
        this.studyRepository = studyRepository;
    }

    public boolean likeChk(Long userID, Long studyID) {
        return null == studyLikeRepository.findByUserIDAndStudyID(userID, studyID);
    }

    public void likeOff(Long userID, Long studyID){
        StudyLikeEntity studyLike = studyLikeRepository.findByUserIDAndStudyID(userID, studyID);
        studyLikeRepository.deleteById(studyLike.getId());
        likeCount(studyID);
    }

    public void likeOn(Long userID, Long studyID){
        StudyLikeEntity studyLike = new StudyLikeEntity().createStudyLike(userID,studyID);
        studyLikeRepository.save(studyLike);
        likeCount(studyID);

    }

    private void likeCount(Long studyID){
        long like = studyLikeRepository.countByStudyID(studyID);
        StudyEntity study = studyRepository.findByid(studyID);
        study.updateLike((int)like);
        studyRepository.save(study);
    }

    public boolean isLike(Long userID, Long studyID){
        return studyLikeRepository.existsByUserIDAndStudyID(userID,studyID);
    }
}
