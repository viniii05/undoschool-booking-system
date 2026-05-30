package com.undoschool.booking_system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.undoschool.booking_system.dto.CreateOfferingRequest;
import com.undoschool.booking_system.entity.Offering;
import com.undoschool.booking_system.service.TeacherService;
import com.undoschool.booking_system.dto.AddSessionRequest;
import com.undoschool.booking_system.entity.Session;
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/offerings")
    public Offering createOffering(
            @RequestBody CreateOfferingRequest request) {

        return teacherService.createOffering(request);
    }
    
    @PostMapping("/offerings/{offeringId}/sessions")
    public Session addSession(
            @PathVariable Long offeringId,
            @RequestBody AddSessionRequest request) {

        return teacherService.addSession(
                offeringId,
                request);
    }
    
    @GetMapping("/{teacherId}/offerings")
    public List<Offering> getTeacherOfferings(
            @PathVariable Long teacherId) {

        return teacherService.getTeacherOfferings(teacherId);
    }
}