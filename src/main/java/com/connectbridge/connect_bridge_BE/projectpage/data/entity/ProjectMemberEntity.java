package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import lombok.Getter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@DynamicUpdate
@Table(name = "projectmember")
public class ProjectMemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private Long projectID;

    private String member;

    public void addMember(int memID){
        this.member +=memID;
    }
}
