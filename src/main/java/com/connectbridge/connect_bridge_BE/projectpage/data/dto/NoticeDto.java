package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import com.connectbridge.connect_bridge_BE.projectpage.data.entity.ProjectNoticeEntity;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class NoticeDto {

    Long id;
    String content;
    Long projectID;

    public static NoticeDto toEntity(ProjectNoticeEntity noticeEntity){
        return NoticeDto.builder()
                .id(noticeEntity.getId())
                .content(noticeEntity.getContent())
                .projectID(noticeEntity.getProjectID())
                .build();
    }

}
