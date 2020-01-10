package com.group.capstone.attendance.entity;


import com.group.capstone.attendance.model.Attendance.dto.TeacherAttendanceInfo;
import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "TeacherAttendanceInfo",
                classes = @ConstructorResult(
                        targetClass = TeacherAttendanceInfo.class,
                        columns = {
                                @ColumnResult(name = "student_id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "avatar", type = String.class),
                                @ColumnResult(name = "is_present", type = Boolean.class),
                        }
                )
        )
})

@NamedNativeQuery(name = "getAttendanceByScheduleId", resultSetMapping = "TeacherAttendanceInfo",
        query = "select u.id as student_id,\n" +
                "concat(u.last_name,\" \",u.first_name) as name,\n" +
                "u.picture as avatar, atd.is_present\n" +
                "from schedule sc\n" +
                "Join attendance atd on atd.schedule_id = sc.id\n" +
                "Join registration rg on atd.registration_id = rg.id\n" +
                "Join user u on rg.user_id = u.id\n" +
                "Where sc.id = 1")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @NotNull
    @Column(name = "isPresent")
    private boolean isPresent;

    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "createdAt")
    private Date createdAt;

    @NotNull
    @Column(name = "createdBy")
    private int createdBy;

    @NotNull
    @Column(name = "updatedAt")
    private Date updatedAt;

    @NotNull
    @Column(name = "updatedBy")
    private int updatedBy;
}

