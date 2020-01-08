package com.group.capstone.attendance.model.Schedule.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentScheduleDto {
    private String teacher;
    private String subject;
    private String class_name;
    private Date date;
    private Time time_start;
    private String room;
    private Boolean present;
    private Boolean status;
}
