package com.group.capstone.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @NotNull
    @Column(name = "account", unique = true)
    private String Account;

    @NotNull
    @Column(name = "password")
    private String Password;

    @NotNull
    @Column(name = "firstName")
    private String FirstName;

    @NotNull
    @Column(name = "lastName")
    private String LassName;

    @NotNull
    @Column(name = "email", unique = true)
    private String Email;

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
    private boolean Status;
}
