package com.undoschool.booking_system.dto;

import lombok.Data;

@Data
public class BookingRequest {

    private Long parentId;
    private Long offeringId;
}