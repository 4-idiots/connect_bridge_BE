package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SubmitDto {
    Long projectID;
    Long userID;
    String field;
}
