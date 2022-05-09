package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.SubmitDto;
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
@Table(name = "submit")
public class SubmitEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private Long projectID;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "field")
    private String field;

    @Column(name = "accept")
    private boolean accept;

    public SubmitEntity createSubmit(SubmitDto submitDto){
        return SubmitEntity.builder()
                .projectID(submitDto.getProjectID())
                .userID(submitDto.getUserID())
                .field(submitDto.getField())
                .build();
    }

    public void updateAccept(boolean accept){
        this.accept= accept;
    }
}
