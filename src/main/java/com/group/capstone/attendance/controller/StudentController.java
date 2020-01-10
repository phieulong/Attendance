package com.group.capstone.attendance.controller;

import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDetailDto;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.User.dto.UserInfo;
import com.group.capstone.attendance.service.Attendance.AttendanceService;
import com.group.capstone.attendance.service.Schedule.ScheduleService;
import com.group.capstone.attendance.service.User.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
@Api(value = "Student Apis")
public class StudentController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "Get a schedule by student_id", response = StudentScheduleDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/schedule/{id}")
    public ResponseEntity<?> getScheduleByIdStudent(@PathVariable int id, String date) {
        List<StudentScheduleDetailDto> studentScheduleDetailDtoList = scheduleService.getScheduleByIdStudent(id, date);
        return ResponseEntity.ok(studentScheduleDetailDtoList);
    }

    @ApiOperation(value="attendance by a student", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Record not found"),
            @ApiResponse(code = 500, message="Error Server"),
    })
    @PutMapping("/attendance/{id}")
    public ResponseEntity<?> setAttendanceBySt(@PathVariable int id, int schedule_id) {
        String result = attendanceService.setAttendanceByStudent(id,schedule_id);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "Get a schedule by student_id", response = UserInfo.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/info/{id}")
    public ResponseEntity<?> getStudentInfo(@PathVariable int id) {
        UserInfo userInfo = userService.getStudentInfo(id);
        return ResponseEntity.ok(userInfo);
    }

}
