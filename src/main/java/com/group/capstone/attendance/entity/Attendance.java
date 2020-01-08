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

