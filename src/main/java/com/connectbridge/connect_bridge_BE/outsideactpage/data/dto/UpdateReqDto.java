package com.connectbridge.connect_bridge_BE.outsideactpage.data.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class UpdateReqDto {

    private Long outActID;

    private String outActName;

    private MultipartFile outActImg;

    private String outActLink;

    @Builder
    public UpdateReqDto(Long outActID, String outActName, MultipartFile outActImg, String outActLink) {
        this.outActID = outActID;
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }
}

