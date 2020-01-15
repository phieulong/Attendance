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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    //Controller gọi đến schedule service để lấy thông tin thời khóa biểu
    //Ngày lấy thời khóa biểu được truyền vào từ client, id học sinh lấy từ token
    @ApiOperation(value = "Get a schedule by student", response = StudentScheduleDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/schedule/")
    public ResponseEntity<?> getScheduleByIdStudent(String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)authentication.getCredentials();
        List<StudentScheduleDetailDto> studentScheduleDetailDtoList = scheduleService.getScheduleByStudentId(id, date);
        return ResponseEntity.ok(studentScheduleDetailDtoList);
    }

    //Controller điểm danh bởi sinh viên
    //Student_id được lấy ra từ token, schedule_id được gửi lên từ client
    @ApiOperation(value="Take attendance by a student", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Record not found"),
            @ApiResponse(code = 500, message="Error Server"),
    })
    @PutMapping("/attendance/")
    public ResponseEntity<?> setAttendanceBySt(int schedule_id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)authentication.getCredentials();
        String result = attendanceService.setAttendanceByStudent(id,schedule_id);
        return ResponseEntity.ok(result);
    }

    //Controller lấy thông tin của sinh viên
    //student_id được lấy từ token
    @ApiOperation(value = "Get a student info", response = UserInfo.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/info")
    public ResponseEntity<?> getStudentInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)authentication.getCredentials();
        UserInfo userInfo = userService.getStudentInfo(id);
        return ResponseEntity.ok(userInfo);
    }

}
