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
@Table(name="classes")
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "users_id")
    private User masterTrainer;

    @ManyToOne
    @JoinColumn(name = "terms_id")
    private Term term;

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
    private String Status;
}
