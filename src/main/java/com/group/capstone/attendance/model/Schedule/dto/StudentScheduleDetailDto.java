package com.group.capstone.attendance.model.Schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentScheduleDetailDto {
    private int schedule_id;
    private String date;
    private String time_start;
    private String teacher;
    private String subject;
    private String class_name;
    private String room;
    private Boolean present;
    private Boolean status;
    private List<String>  avatar_5_friends;
}
