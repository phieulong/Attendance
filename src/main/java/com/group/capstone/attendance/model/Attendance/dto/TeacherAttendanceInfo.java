package com.group.capstone.attendance.model.Attendance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherAttendanceInfo {
    private int student_id;
    private String name;
    private String avatar;
    private int is_present;
}