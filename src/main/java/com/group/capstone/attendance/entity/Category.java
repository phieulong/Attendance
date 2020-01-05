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
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @NotNull
    @Column(name = "name", unique = true)
    private String Name;

    @NotNull
    @Column(name = "timeStart")
    private Date TimeStart;

    @NotNull
    @Column(name = "timeFinish")
    private Date TimeFinish;

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
