package com.connectbridge.connect_bridge_BE.outsideactpage.data.dto;

import lombok.Builder;

public class getResDto {
    Long outActID;
    String outActName;
    String outActDate;
    int outActView;
    int outActLike;
    String outActLink;
    Boolean outActSub;

    @Builder
    public getResDto(Long outActID, String outActName, String outActDate, int outActView, int outActLike, String outActLink, Boolean outActSub) {
        this.outActID = outActID;
        this.outActName = outActName;
        this.outActDate = outActDate;
        this.outActView = outActView;
        this.outActLike = outActLike;
        this.outActLink = outActLink;
        this.outActSub = outActSub;
    }
}
