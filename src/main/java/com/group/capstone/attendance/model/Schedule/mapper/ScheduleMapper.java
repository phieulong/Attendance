package com.group.capstone.attendance.model.Schedule.mapper;

import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDetailDto;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;

import java.util.List;

public class ScheduleMapper {
    public static StudentScheduleDetailDto toStudentScheduleDetailDto
            (StudentScheduleDto studentScheduleDto, List<String> avatar_5_friends){
        StudentScheduleDetailDto studentScheduleDetailDto = new StudentScheduleDetailDto();
        studentScheduleDetailDto.setSchedule_id(studentScheduleDto.getSchedule_id());
        studentScheduleDetailDto.setDate(studentScheduleDto.getDate());
        studentScheduleDetailDto.setTime_start(studentScheduleDto.getTime_start());
        studentScheduleDetailDto.setTeacher(studentScheduleDto.getTeacher());
        studentScheduleDetailDto.setSubject(studentScheduleDto.getSubject());
        studentScheduleDetailDto.setClass_name(studentScheduleDto.getClass_name());
        studentScheduleDetailDto.setRoom(studentScheduleDto.getRoom());
        studentScheduleDetailDto.setPresent(studentScheduleDto.getPresent());
        studentScheduleDetailDto.setStatus(studentScheduleDto.getStatus());
        studentScheduleDetailDto.setAvatar_5_friends(avatar_5_friends);
        return studentScheduleDetailDto;
    }
}
