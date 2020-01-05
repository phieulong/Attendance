package com.group.capstone.attendance.model.Schedule.dto;

import com.group.capstone.attendance.entity.Category;
import com.group.capstone.attendance.entity.Class;
import com.group.capstone.attendance.entity.Room;
import com.group.capstone.attendance.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentScheduleDto {
    private int id;
    private String classname;
    private Date timeStart;
    private Date timeFinish;
    private String subject;
    private String room;
    private Boolean Status;
}
