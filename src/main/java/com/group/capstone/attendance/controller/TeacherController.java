package com.group.capstone.attendance.controller;

import com.group.capstone.attendance.entity.Schedule;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @ApiOperation(value="Get a schedule list by teacher_id", response = TeacherScheduleDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Record not found"),
            @ApiResponse(code = 500, message="Error Server"),
    })
    @GetMapping("/schedule/")
    public ResponseEntity<?> getScheduleByIdTeacher(String date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)authentication.getCredentials();
        List<TeacherScheduleDto> teacherScheduleDtoList = scheduleService.getScheduleByTeacherId(id, date);
        return ResponseEntity.ok(teacherScheduleDtoList);
    }
    @ApiOperation(value = "Get a teacher info", response = UserInfo.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })

    @GetMapping("/info/")
    public ResponseEntity<?> getTeacherInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)authentication.getCredentials();
        UserInfo userInfo = userService.getTeacherInfo(id);
        return ResponseEntity.ok(userInfo);
    }

    @ApiOperation(value = "Get a attendance list by teacher", response = TeacherAttendanceInfo.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "Record not found"),
            @ApiResponse(code = 500, message = "Error Server"),
    })
    @GetMapping("/attendance/{schedule_id}")
    public ResponseEntity<?> getAttendanceList(@PathVariable int schedule_id) {
        List<TeacherAttendanceInfo> teacherAttendanceInfoList = attendanceService.getAttendanceListByScheduleId(schedule_id);
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

    //Controller tạo mới thời khóa biểu
    //Gọi đến schedule service
    @ApiOperation(value="Create a schedule", response = CreateScheduleRequest.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Can not insert the data, this record is exist in database"),
            @ApiResponse(code = 500, message="Server is getting in troubles"),
    })
    @PostMapping("/createSchedule")
    public ResponseEntity<?> createSchedule(@RequestBody @Valid CreateScheduleRequest createScheduleRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)authentication.getCredentials();
        String result = scheduleService.createSchedule(id, createScheduleRequest);
        return ResponseEntity.ok(result);
    }

    //Controller thêm mới sinh viên
    //Gọi đến user service
    @ApiOperation(value="Create a user", response = CreateUserRequest.class)
    @ApiResponses({
            @ApiResponse(code = 400, message="Can not insert the data, this record is exist in database"),
            @ApiResponse(code = 500, message="Server is getting in troubles"),
    })
    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int id = (Integer)authentication.getCredentials();
        UserInfo userInfo = userService.createUser(id, createUserRequest);
        return ResponseEntity.ok(userInfo);
    }

    @ApiOperation(value="Take attendance by a teacher", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 404, message="Record not found"),
            @ApiResponse(code = 500, message="Error Server"),
    })
    @PutMapping("/take-attendance/{id}")
    public ResponseEntity<?> setAttendanceBySt(@PathVariable int id, int scheduleId, boolean isPresent) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int teacher_id = (Integer)authentication.getCredentials();
        String result = attendanceService.setAttendanceByTeacher(teacher_id, id,scheduleId, isPresent);
        return ResponseEntity.ok(result);
    }
}
