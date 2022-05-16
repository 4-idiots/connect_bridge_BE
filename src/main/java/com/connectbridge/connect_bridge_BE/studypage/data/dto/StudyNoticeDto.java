package com.connectbridge.connect_bridge_BE.studypage.data.dto;

import com.connectbridge.connect_bridge_BE.studypage.data.Entity.StudyNoticeEntity;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class StudyNoticeDto {

    Long id;
    Long studyID;
    String content;

    public static StudyNoticeDto toEntity(StudyNoticeEntity noticeEntity){
        return StudyNoticeDto.builder()
                .id(noticeEntity.getId())
                .content(noticeEntity.getContent())
                .studyID(noticeEntity.getStudyID())
                .build();
    }
}
