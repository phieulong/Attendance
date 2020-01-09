package com.group.capstone.attendance.controller;

import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import com.group.capstone.attendance.service.Schedule.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/teacher")
@Api(value = "Teacher Apis")
public class TeacherController {
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value="Get a schedule by teacher_id", response = TeacherScheduleDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Record not found"),
            @ApiResponse(code = 500, message="Error Server"),
    })
    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> getScheduleByIdTeacher(@PathVariable int id, String date) {
        List<TeacherScheduleDto> teacherScheduleDtoList = scheduleService.getScheduleByIdTeacher(id, date);
        return ResponseEntity.ok(teacherScheduleDtoList);
    }
}