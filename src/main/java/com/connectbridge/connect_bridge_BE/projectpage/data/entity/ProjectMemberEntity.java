package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import javax.persistence.*;

@Entity
@Table(name = "projectmember")
public class ProjectMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private Long projectID;

    private String member;
}
