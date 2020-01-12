package com.group.capstone.attendance.entity;

import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
import com.group.capstone.attendance.model.Schedule.dto.TeacherScheduleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "UserScheduleInfo",
                classes = @ConstructorResult(
                        targetClass = StudentScheduleDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "date", type = String.class),
                                @ColumnResult(name = "time_start", type = String.class),
                                @ColumnResult(name = "teacher", type = String.class),
                                @ColumnResult(name = "subject", type = String.class),
                                @ColumnResult(name = "class", type = String.class),
                                @ColumnResult(name = "room", type = String.class),
                                @ColumnResult(name = "present", type = Boolean.class),
                                @ColumnResult(name = "status", type = Boolean.class),
                        }
                )
        ),

        @SqlResultSetMapping(
                name = "TeacherScheduleInfo",
                classes = @ConstructorResult(
                        targetClass = TeacherScheduleDto.class,
                        columns = {
                                @ColumnResult(name = "schedule_id", type = Integer.class),
                                @ColumnResult(name = "date", type = String.class),
                                @ColumnResult(name = "time_start", type = String.class),
                                @ColumnResult(name = "class_name", type = String.class),
                                @ColumnResult(name = "subject", type = String.class),
                                @ColumnResult(name = "room", type = String.class),
                                @ColumnResult(name = "total", type = Integer.class),
                                @ColumnResult(name = "total_attendance", type = Integer.class),
                                @ColumnResult(name = "status", type = Boolean.class),
                        }
                )
        )
})




@NamedNativeQuery(name = "findScheduleByTeacherId", resultSetMapping = "TeacherScheduleInfo",
        query = "select sc.id as schedule_id, date_format(sc.date, \"%d/%m/%Y\") as date,\n" +
                "time_format(cg.time_start,\"%H:%i:%s\") as time_start, cl.name as class_name,\n" +
                "s.name as subject, r.name as room,\n" +
                "(select count(rgt.id)\n" +
                "from registration rgt\n" +
                "join class c on rgt.class_id = c.id\n" +
                "where c.id = cl.id) as total,\n" +
                "(select count(atd.id)\n" +
                "from attendance atd\n" +
                "where atd.schedule_id = sc.id and atd.is_present = 1) as total_attendance,\n" +
                "sc.status\n" +
                "from user u\n" +
                "join schedule sc on sc.user_id = u.id\n" +
                "join category cg on sc.category_id = cg.id\n" +
                "join class_term ct on sc.class_term_id = ct.id\n" +
                "join class cl on ct.class_id = cl.id\n" +
                "join subject s on sc.subject_id = s.id\n" +
                "join room r on sc.room_id = r.id\n" +
                "where u.id = ?1 and sc.date = ?2")

@NamedNativeQuery(name = "findScheduleByStudentId", resultSetMapping = "UserScheduleInfo",
        query = "select sc.id, \n" +
                "date_format(sc.date, '%d/%m/%Y') as date, \n" +
                "time_format(cg.time_start, '%H:%i:%s') as time_start, \n" +
                "(select concat(ur.last_name,\" \",ur.first_name)\n" +
                "from user ur\n" +
                "where ur.id = sc.user_id) as teacher, \n" +
                "s.name as subject,cl.name as class,\n" +
                "r.name as room, atd.is_present as present, sc.status\n" +
                "from user u\n" +
                "join registration rg on rg.user_id = u.id\n" +
                "join class cl on rg.class_id = cl.id\n" +
                "join class_term ct on ct.class_id = cl.id\n" +
                "join term t on ct.term_id = t.id\n" +
                "join schedule sc on sc.class_term_id = ct.id\n" +
                "join category cg on sc.category_id = cg.id\n" +
                "join subject s on sc.subject_id = s.id\n" +
                "join room r on sc.room_id = r.id\n" +
                "join attendance atd on atd.registration_id = rg.id and atd.schedule_id = sc.id\n" +
                "where u.id = ?1 and sc.date = ?2")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "classTerm_id")
    private ClassTerm classTerm;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User subTrainer;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @OneToMany(mappedBy = "schedule")
    private List<Attendance> attendancesList;

    @NotNull
    @Column(name = "date")
    private java.sql.Date date;

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

    @NotNull
    @Column(name = "status")
    private Boolean status;
}

