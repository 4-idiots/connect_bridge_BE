package com.connectbridge.connect_bridge_BE.studypage.data.Entity;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.SubmitDto;
import com.connectbridge.connect_bridge_BE.projectpage.data.entity.SubmitEntity;
import com.connectbridge.connect_bridge_BE.studypage.data.dto.StudySubmitDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
@Builder
@Table(name = "submitstudy")
public class StudySubmitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "study_id")
    private Long studyID;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "field")
    private String field;

    @Column(name = "accept")
    private boolean accept;

    public StudySubmitEntity createSubmit(StudySubmitDto submitDto){
        return StudySubmitEntity.builder()
                .studyID(submitDto.getStudyID())
                .userID(submitDto.getUserID())
                .field(submitDto.getField())
                .build();
    }

    public void updateAccept(boolean accept){
        this.accept= accept;
    }
}
