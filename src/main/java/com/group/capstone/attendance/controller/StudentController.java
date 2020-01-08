package com.group.capstone.attendance.controller;

import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.service.Schedule.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/student")
@Api(value = "Student Apis")
public class StudentController {
    @Autowired
    private ScheduleService scheduleService;

    @ApiOperation(value="Get a schedule by student_id", response = StudentScheduleDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Record not found"),
            @ApiResponse(code = 500, message="Error Server"),
    })
    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> getScheduleByIdStudent(@PathVariable int id) {
        List<StudentScheduleDto> studentScheduleDtoList = scheduleService.getScheduleByIdStudent(id);
        return ResponseEntity.ok(studentScheduleDtoList);
    }
}
