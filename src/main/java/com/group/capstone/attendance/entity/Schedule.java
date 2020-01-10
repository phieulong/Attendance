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
        query = "select sc.id as schedule_id, DATE_FORMAT(sc.date, '%d/%m/%Y') as date," +
                " TIME_FORMAT(cg.time_start, '%H:%i:%s') as time_start , " +
                "c.name as class_name, " +
                "s.name as subject, r.name as room, \n" +
                "(select count(rgt.id)\n" +
                "from registration rgt\n" +
                "join class cl on rgt.class_id = cl.id\n" +
                "where cl.id = c.id) as total,\n" +
                "(select count(atd.id)\n" +
                "from attendance atd\n" +
                "where atd.schedule_id = sc.id and atd.is_present = 1) as total_attendance, sc.status\n" +
                "from schedule sc\n" +
                "Join user u on sc.user_id = u.id\n" +
                "Join category cg on sc.category_id = cg.id\n" +
                "Join subject s on sc.subject_id = s.id\n" +
                "Join room r on sc.room_id = r.id\n" +
                "Join class_term ct on sc.class_term_id = ct.id\n" +
                "Join class c on ct.class_id = c.id\n" +
                "where u.id = ?1 and sc.date = ?2")

@NamedNativeQuery(name = "findScheduleByStudentId", resultSetMapping = "UserScheduleInfo",
        query = "select sc.id,\n" +
                "(select concat(u.last_name,\" \",u.first_name)\n" +
                "from user where user.id = sc.user_id) as teacher,\n" +
                "s.name as subject, cl.name as class, DATE_FORMAT(sc.date, '%d/%m/%Y') as date," +
                " TIME_FORMAT(cg.time_start, '%H:%i:%s') as time_start," +
                " r.name as room,\n" +
                " a.is_present as present, sc.status\n" +
                "From user u\n" +
                "Join registration rg on u.id = rg.user_id\n" +
                "Join class cl on rg.class_id = cl.id\n" +
                "Join class_term ct on cl.id = ct.class_id\n" +
                "Join schedule sc on ct.id = sc.class_term_id\n" +
                "Join category cg on sc.category_id = cg.id\n" +
                "Join subject s on sc.subject_id = s.id\n" +
                "Join room r on sc.room_id = r.id\n" +
                "Join attendance a on a.schedule_id = sc.id and a.registration_id = rg.id\n" +
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

