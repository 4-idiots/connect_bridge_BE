package com.connectbridge.connect_bridge_BE.projectpage.data.entity;

import com.connectbridge.connect_bridge_BE.projectpage.data.dto.NoticeDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "projectnotice")
@Entity
public class ProjectNoticeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_id")
    private Long projectID;

    @Column(name = "notice_content")
    private String content;


    public ProjectNoticeEntity createNotice(Long projectID, String content){
        return builder()
                .projectID(projectID)
                .content(content)
                .build();
    }

}
