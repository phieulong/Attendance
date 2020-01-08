package com.group.capstone.attendance.entity;

import com.group.capstone.attendance.model.Schedule.dto.StudentScheduleDto;
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
                                @ColumnResult(name = "date"),
                                @ColumnResult(name = "time_start"),
                                @ColumnResult(name = "teacher"),
                                @ColumnResult(name = "subject"),
                                @ColumnResult(name = "class"),
                                @ColumnResult(name = "room"),
                                @ColumnResult(name = "present"),
                                @ColumnResult(name = "status"),
                        }
                )
        )
})
@NamedNativeQuery(name = "findScheduleByStudentId", resultSetMapping = "UserScheduleInfo",
        query = "select \n" +
                "(select user.first_name \n" +
                "from user where user.id = sc.user_id) as teacher,\n" +
                "s.name as subject, cl.name as class, sc.date, cg.time_start, r.name as room,\n" +
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
                "where u.id = ?1")
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

