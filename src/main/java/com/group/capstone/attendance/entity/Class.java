package com.group.capstone.attendance.entity;

import com.group.capstone.attendance.model.Class.dto.ClassDto;
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
                name = "ClassInfo",
                classes = @ConstructorResult(
                        targetClass = ClassDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "master_trainer", type = String.class),
                        }
                )
        )
})

@NamedNativeQuery(name = "getAllTeacherInfo", resultSetMapping = "ClassInfo",
        query = "select cl.id, cl.name, \n" +
                "concat(u.last_name,\" \", u.first_name) as master_trainer\n" +
                "from class cl\n" +
                "join user u on cl.user_id = u.id\n" +
                "where cl.status = 1")

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="class")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User masterTrainer;

    @OneToMany(mappedBy = "aClass")
    private List<ClassTerm> classTermList;

    @OneToMany(mappedBy = "aClass")
    private List<Registration> registrationList;

    @NotNull
    @Column(name = "name", unique = true)
    private String Name;

    @NotNull
    @Column(name = "createdAt")
    private Date CreatedAt;

    @NotNull
    @Column(name = "createdBy")
    private int CreatedBy;

    @NotNull
    @Column(name = "updatedAt")
    private Date UpdatedAt;

    @NotNull
    @Column(name = "updatedBy")
    private int UpdatedBy;

    @NotNull
    @Column(name = "status")
    private Boolean Status;
}
