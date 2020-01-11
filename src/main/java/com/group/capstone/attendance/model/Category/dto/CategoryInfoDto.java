package com.group.capstone.attendance.model.Category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryInfoDto {
    private int id;
    private String name;
    private String time_start;
    private String time_finish;
}
