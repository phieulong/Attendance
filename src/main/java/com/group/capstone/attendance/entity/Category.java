package com.group.capstone.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "category")
    private List<Schedule> scheduleList;

    @NotNull
    @Column(name = "name", unique = true)
    private String name;

    @NotNull
    @Column(name = "timeStart")
    private Date timeStart;

    @NotNull
    @Column(name = "timeFinish")
    private Date timeFinish;

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
