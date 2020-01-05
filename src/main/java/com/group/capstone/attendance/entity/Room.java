package com.group.capstone.attendance.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @NotNull
    @Column(name = "name", unique = true)
    private String Name;

    @Column(name = "description")
    private String Description;

    @NotNull
    @Column(name = "status")
    private Boolean Status;
}

