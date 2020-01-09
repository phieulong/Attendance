package com.group.capstone.attendance.model.Schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherScheduleDto {
    private int schedule_id;
    private String date;
    private String time_start;
    private String class_name;
    private String subject;
    private String room;
    private int total;
    private int total_attendance;
    private Boolean status;
}

