package com.connectbridge.connect_bridge_BE.outactpage.data.dto;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutAct;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Builder
@Getter
@Setter
public class OutActDto {
    Long outActID;
    String outActName;
    String outActImg;
    LocalDateTime outActDate;
    int outActView;
    int outActLike;
    String outActLink;
    Boolean outActSub;

    @Builder
    public OutActDto(Long outActID, String outActName,String outActImg, LocalDateTime outActDate, int outActView, int outActLike, String outActLink, Boolean outActSub) {
        this.outActID = outActID;
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActDate = outActDate;
        this.outActView = outActView;
        this.outActLike = outActLike;
        this.outActLink = outActLink;
        this.outActSub = outActSub;
    }

    public OutActDto(OutAct outAct){
        this.outActID = outAct.getId();
        this.outActName = outAct.getOutActName();
        this.outActImg = outAct.getOutActImg();
        this.outActDate = outAct.getCreateDate();
        this.outActView = outAct.getOutActView();
        this.outActLike = outAct.getOutActLike();
        this.outActLink = outAct.getOutActLink();
        this.outActSub = outAct.getOutActSub();
    }
}
