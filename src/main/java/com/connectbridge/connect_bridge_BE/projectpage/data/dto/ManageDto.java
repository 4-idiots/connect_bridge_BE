package com.connectbridge.connect_bridge_BE.projectpage.data.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class ManageDto {
    Long id;
    Long projectID;
    Long userID;
    String field;
}

