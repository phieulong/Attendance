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
public class StudentScheduleDto {
    private Date date;
    private Date time_start;
    private String teacher;
    private String subject;
    private String class_name;
    private String room;
    private Boolean present;
    private Boolean status;
}
