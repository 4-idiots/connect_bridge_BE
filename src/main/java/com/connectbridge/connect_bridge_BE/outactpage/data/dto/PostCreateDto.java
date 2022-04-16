package com.connectbridge.connect_bridge_BE.outactpage.data.dto;

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
    //MultipartFile outActImg;
    String outActImg;
    String outActLink;


    @Builder
    public PostCreateDto(String outActName,String outActImg, String outActLink) {
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActLink = outActLink;
    }


}
