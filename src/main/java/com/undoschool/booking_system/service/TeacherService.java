package com.undoschool.booking_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.undoschool.booking_system.dto.AddSessionRequest;
import com.undoschool.booking_system.dto.CreateOfferingRequest;
import com.undoschool.booking_system.entity.Course;
import com.undoschool.booking_system.entity.Offering;
import com.undoschool.booking_system.entity.Session;
import com.undoschool.booking_system.entity.Teacher;
import com.undoschool.booking_system.repository.CourseRepository;
import com.undoschool.booking_system.repository.OfferingRepository;
import com.undoschool.booking_system.repository.TeacherRepository;
import com.undoschool.booking_system.repository.SessionRepository;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TeacherService {

    @Autowired
    private OfferingRepository offeringRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;
    
    @Autowired
    private SessionRepository sessionRepository;

    public Offering createOffering(CreateOfferingRequest request) {

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        Teacher teacher = teacherRepository.findById(request.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        Offering offering = new Offering();

        offering.setName(request.getName());
        offering.setCourse(course);
        offering.setTeacher(teacher);

        return offeringRepository.save(offering);
    }
    
    public Session addSession(Long offeringId, AddSessionRequest request) {

    	Offering offering =
    			offeringRepository.findById(offeringId)
    				.orElseThrow(() ->
    					new RuntimeException("Offering not found"));

    	ZoneId zone =
    			ZoneId.of(request.getTimezone());

    	Instant startUtc =
    			LocalDateTime.parse(request.getStartTime())
    			.atZone(zone)
    			.toInstant();

    	Instant endUtc =
    			LocalDateTime.parse(request.getEndTime())
    			.atZone(zone)
    			.toInstant();

    	Session session = new Session();

    	session.setOffering(offering);
    	session.setStartTime(startUtc);
    	session.setEndTime(endUtc);

    	return sessionRepository.save(session);
    }
    
    public List<Offering> getTeacherOfferings(Long teacherId) {

        Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() ->
                        new RuntimeException("Teacher not found"));

        return offeringRepository.findByTeacherId(teacherId);
    }
}