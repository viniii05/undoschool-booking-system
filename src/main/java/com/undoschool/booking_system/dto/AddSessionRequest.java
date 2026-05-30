package com.undoschool.booking_system.dto;

import lombok.Data;

@Data
public class AddSessionRequest {

    private String startTime;
    private String endTime;
    private String timezone;
}