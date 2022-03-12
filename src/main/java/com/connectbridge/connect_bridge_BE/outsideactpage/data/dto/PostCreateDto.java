package com.connectbridge.connect_bridge_BE.outsideactpage.data.dto;

import com.connectbridge.connect_bridge_BE.outsideactpage.data.entity.OutAct;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class PostCreateDto {

    String outActName;
    MultipartFile outActImg;
    String outActLink;


    @Builder
    public PostCreateDto(String outActName,MultipartFile outActImg, String outActLink) {
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }


}

