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
@Table(name="classTerm")
public class ClassTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class aClass;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private Term term;

    @OneToMany(mappedBy = "classTerm")
    private List<Schedule> scheduleList;

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
