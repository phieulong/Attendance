package com.group.capstone.attendance.model.Term.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TermDto {
    private int id;
    private String name;
    private String time_begin;
    private String time_end;
}
