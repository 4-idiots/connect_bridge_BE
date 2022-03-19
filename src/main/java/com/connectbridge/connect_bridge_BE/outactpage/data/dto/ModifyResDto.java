package com.connectbridge.connect_bridge_BE.outactpage.data.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ModifyResDto {

    private String outActName;

    private String outActImg;

    private String outActLink;

    @Builder
    public ModifyResDto(String outActName, String outActImg, String outActLink) {
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }

}
