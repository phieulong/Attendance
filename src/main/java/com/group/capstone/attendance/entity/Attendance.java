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
@Table(name="attendances")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "registrations_id")
    private Registration registration;

    @ManyToOne
    @JoinColumn(name = "schedules_id")
    private Schedule schedule;

    @NotNull
    @Column(name = "isPresent")
    private boolean IsPresent;

    @Column(name = "description")
    private String Description;

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

