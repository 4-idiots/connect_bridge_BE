package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "projectfollow")
public class ProjectFollowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userID;

    @Column(name = "project_id")
    private Long projectID;

    public ProjectFollowEntity createProjectFollow(Long userID, Long projectID){
        return ProjectFollowEntity.builder()
                .userID(userID)
                .projectID(projectID)
                .build();
    }
}
