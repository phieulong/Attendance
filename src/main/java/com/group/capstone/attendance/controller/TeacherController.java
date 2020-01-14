package com.group.capstone.attendance.controller;

import com.group.capstone.attendance.model.Attendance.dto.TeacherAttendanceInfo;
import com.group.capstone.attendance.model.Category.dto.CategoryInfoDto;
import com.group.capstone.attendance.model.Class.dto.ClassDto;
import com.group.capstone.attendance.model.Room.dto.RoomDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import com.group.capstone.attendance.model.Schedule.request.CreateScheduleRequest;
import com.group.capstone.attendance.model.Subject.dto.SubjectDto;
import com.group.capstone.attendance.model.Term.dto.TermDto;
import com.group.capstone.attendance.model.User.dto.UserInfo;
import com.group.capstone.attendance.model.User.request.CreateUserRequest;
import com.group.capstone.attendance.service.Attendance.AttendanceService;
import com.group.capstone.attendance.service.Category.CategoryService;
import com.group.capstone.attendance.service.Class.ClassService;
import com.group.capstone.attendance.service.Room.RoomService;
import com.group.capstone.attendance.service.Schedule.ScheduleService;
import com.group.capstone.attendance.service.Subject.SubjectService;
import com.group.capstone.attendance.service.Term.TermService;
import com.group.capstone.attendance.service.User.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/teacher")
@Api(value = "Teacher Apis")
public class TeacherController {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private UserService userService;
    @Autowired
    private AttendanceService attendanceService;
    @Autowired
    private ClassService classService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private TermService termService;
    @Autowired
    private CategoryService categoryService;

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
    @ApiOperation(value = "Get a schedule by student_id", response = UserInfo.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })

    @GetMapping("/info/{id}")
    public ResponseEntity<?> getStudentInfo(@PathVariable int id) {
        UserInfo userInfo = userService.getTeacherInfo(id);
        return ResponseEntity.ok(userInfo);
    }

    @ApiOperation(value = "Get a schedule by student_id", response = TeacherAttendanceInfo.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/attendance/{id}")
    public ResponseEntity<?> getAttendanceList(@PathVariable int id) {
        List<TeacherAttendanceInfo> teacherAttendanceInfoList = attendanceService.getAttendanceListByScheduleId(id);
        return ResponseEntity.ok(teacherAttendanceInfoList);
    }

    @ApiOperation(value = "Get all teacher info", response = UserInfo.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/getAllTeacherInfo")
    public ResponseEntity<?> getAllTeacherInfo() {
        List<UserInfo> userInfoList = userService.getAllTeacher();
        return ResponseEntity.ok(userInfoList);
    }

    @ApiOperation(value = "Get all class info", response = ClassDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/getAllClassInfo")
    public ResponseEntity<?> getAllClassInfo() {
        List<ClassDto> classDtoList = classService.getAllClassInfo();
        return ResponseEntity.ok(classDtoList);
    }

    @ApiOperation(value = "Get all subject info", response = SubjectDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/getAllSubjectInfo")
    public ResponseEntity<?> getAllSubjectInfo() {
        List<SubjectDto> subjectDtoList = subjectService.getAllSubjectDto();
        return ResponseEntity.ok(subjectDtoList);
    }

    @ApiOperation(value = "Get all subject info", response = RoomDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/getAllRoomInfo")
    public ResponseEntity<?> getAllRoomInfo() {
        List<RoomDto> roomDtoList = roomService.getAllRoomInfo();
        return ResponseEntity.ok(roomDtoList);
    }

    @ApiOperation(value = "Get all term info", response = TermDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/getAllTermInfo")
    public ResponseEntity<?> getAllTermInfo() {
        List<TermDto> termDtoList = termService.getAllTermInfo();
        return ResponseEntity.ok(termDtoList);
    }

    @ApiOperation(value = "Get all category info", response = CategoryInfoDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/getAllCategoryInfo")
    public ResponseEntity<?> getAllCategoryInfo() {
        List<CategoryInfoDto> categoryInfoDtoList = categoryService.getAllCategoryInfo();
        return ResponseEntity.ok(categoryInfoDtoList);
    }

    @ApiOperation(value="Create a schedule", response = CreateScheduleRequest.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Da ton tai schedule nay"),
            @ApiResponse(code = 500, message="Server bi loi"),
    })
    @PostMapping("")
    public ResponseEntity<?> createSchedule(@RequestBody @Valid CreateScheduleRequest createScheduleRequest){
        String result = scheduleService.createSchedule(createScheduleRequest);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value="Create a user", response = CreateUserRequest.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Da ton tai schedule nay"),
            @ApiResponse(code = 500, message="Server bi loi"),
    })
    @PostMapping("/createUser")
    public ResponseEntity<?> createSchedule(@RequestBody @Valid CreateUserRequest createUserRequest){
        UserInfo userInfo = userService.createUser(createUserRequest);
        return ResponseEntity.ok(userInfo);
    }

    @ApiOperation(value="attendance by a teacher", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Record not found"),
            @ApiResponse(code = 500, message="Error Server"),
    })
    @PutMapping("/take-attendance/{id}")
    public ResponseEntity<?> setAttendanceBySt(@PathVariable int id, int scheduleId, boolean isPresent) {
        String result = attendanceService.setAttendanceByTeacher(id,scheduleId, isPresent);
        return ResponseEntity.ok(result);
    }
}
