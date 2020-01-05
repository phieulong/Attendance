package com.group.capstone.attendance.model.Schedule.mapper;

import com.group.capstone.attendance.entity.Schedule;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;

public class ScheduleMapper {
    public static StudentScheduleDto toStudentScheduleDto (Schedule schedule){
        StudentScheduleDto studentScheduleDto = new StudentScheduleDto();
        studentScheduleDto.setId(schedule.getId());
        studentScheduleDto.setClassname(schedule.getAClass().getName());
        studentScheduleDto.setSubject(schedule.getSubject().getName());
        studentScheduleDto.setTimeStart(schedule.getCategory().getTimeStart());
        studentScheduleDto.setTimeFinish(schedule.getCategory().getTimeFinish());
        studentScheduleDto.setRoom(schedule.getRoom().getName());
        studentScheduleDto.setStatus(schedule.getStatus());
        return studentScheduleDto;
    }
}
