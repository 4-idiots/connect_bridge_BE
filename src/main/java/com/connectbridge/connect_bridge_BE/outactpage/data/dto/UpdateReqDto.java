package com.connectbridge.connect_bridge_BE.outactpage.data.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UpdateReqDto {

    private Long outActID;

    private String outActName;

    private String outActImg;

    private String outActLink;

    @Builder
    public UpdateReqDto(Long outActID, String outActName, String outActImg, String outActLink) {
        this.outActID = outActID;
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }
}
