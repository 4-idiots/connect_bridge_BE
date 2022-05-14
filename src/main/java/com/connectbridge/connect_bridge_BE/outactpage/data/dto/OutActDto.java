package com.connectbridge.connect_bridge_BE.outactpage.data.dto;

import com.connectbridge.connect_bridge_BE.outactpage.data.entity.OutAct;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.LocalDateTime;



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

    public OutActDto(BigInteger outActID, String outActName, String outActImg, int outActView, int outActLike, String outActLink, Boolean outActSub, Timestamp outActDate) {
        this.outActID = outActID.longValue();
        this.outActName = outActName;
        this.outActImg = outActImg;
        this.outActView = outActView;
        this.outActLike = outActLike;
        this.outActLink = outActLink;
        this.outActSub = outActSub;
        this.outActDate = outActDate.toLocalDateTime();
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
