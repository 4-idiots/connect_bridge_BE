package com.connectbridge.connect_bridge_BE.outsideactpage.data.dto;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.entity.OutAct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateDto {

    String outActName;
    String outActImg;
    String outActLink;


    @Builder
    public PostCreateDto(String outActName, String outActImg, String outActLink) {
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }


}

