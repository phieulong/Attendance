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
@Table(name="userRole")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @ManyToOne
    @JoinColumn(name = "roles_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @NotNull
    @Column(name = "createdAt")
    private Date CreatedAt;

    @NotNull
    @Column(name = "createdBy")
    private int CreatedBy;

    @NotNull
    @Column(name = "updatedAt")
    private int UpdatedAt;

    @NotNull
    @Column(name = "updatedBy")
    private int UpdatedBy;
}
