package com.connectbridge.connect_bridge_BE.studypage.data.Entity;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectLikeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "studylike")
public class StudyLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "study_id")
    private Long studyID;

    public StudyLikeEntity createStudyLike(Long userID, Long studyID){
        return StudyLikeEntity.builder()
                .userID(userID)
                .studyID(studyID)
                .build();
    }
}
