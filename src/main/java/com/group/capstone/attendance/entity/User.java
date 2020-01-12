package com.group.capstone.attendance.entity;

import com.group.capstone.attendance.model.User.dto.UserInfo;
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
                name = "UserInfo",
                classes = @ConstructorResult(
                        targetClass = UserInfo.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "class", type = String.class),
                                @ColumnResult(name = "email", type = String.class),
                                @ColumnResult(name = "account", type = String.class),
                                @ColumnResult(name = "avatar", type = String.class),
                        }
                )
        )
})

@NamedNativeQuery(name = "getTeacherInfo", resultSetMapping = "UserInfo",
        query = "select u.id,concat(u.last_name,\" \", u.first_name) as name,\n" +
                "(select cl.name\n" +
                "from class cl\n" +
                "where cl.user_id = u.id) as class,\n" +
                "u.email, u.account, u.picture as avatar\n" +
                "from user u \n" +
                "join user_role ur on u.id = ur.user_id\n" +
                "join role r on ur.role_id = r.id\n" +
                "where r.id = 1 and u.status = 1 and u.id = ?1")


@NamedNativeQuery(name = "getStudentInfo", resultSetMapping = "UserInfo",
        query = "SELECT u.id,\n" +
                "concat(u.last_name,\" \",u.first_name) as name,\n" +
                "cl.name as class, u.email, u.account, u.picture as avatar\n" +
                "FROM user u \n" +
                "join registration rg on rg.user_id = u.id\n" +
                "join class cl on cl.id = rg.class_id\n" +
                "where u.id = ?1")

@NamedNativeQuery(name = "getAllTeacher", resultSetMapping = "UserInfo",
        query = "select u.id,concat(u.last_name,\" \", u.first_name) as name,\n" +
                "(select cl.name\n" +
                "from class cl\n" +
                "where cl.user_id = u.id) as class,\n" +
                "u.email, u.account, u.picture as avatar\n" +
                "from user u \n" +
                "join user_role ur on u.id = ur.user_id\n" +
                "join role r on ur.role_id = r.id\n" +
                "where r.id = 1 and u.status = 1")


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(mappedBy = "masterTrainer")
    private Class aClass;

    @OneToMany(mappedBy = "subTrainer")
    private List<Schedule> scheduleList;

    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoleList;

    @NotNull
    @Column(name = "account", unique = true)
    private String account;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "firstName")
    private String firstName;

    @NotNull
    @Column(name = "lastName")
    private String lassName;

    @NotNull
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "picture")
    private String picture;

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
