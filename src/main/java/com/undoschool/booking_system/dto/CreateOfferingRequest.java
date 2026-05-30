package com.undoschool.booking_system.dto;

import lombok.Data;

@Data
public class CreateOfferingRequest {

    private Long courseId;
    private Long teacherId;
    private String name;
}