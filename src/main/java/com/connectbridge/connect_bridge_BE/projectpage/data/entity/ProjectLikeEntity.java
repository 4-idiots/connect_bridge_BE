package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

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
@Table(name = "projectlike")
public class ProjectLikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "project_id")
    private Long projectID;

    public ProjectLikeEntity createProjectLike(Long userID,Long projectID){
        return ProjectLikeEntity.builder()
                .userID(userID)
                .projectID(projectID)
                .build();
    }
}
