package com.group.capstone.attendance.model.Schedule.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateScheduleRequest {
    @NotNull(message = "Category id is required")
    @ApiModelProperty(
            example = "1",
            notes = "Category id cannot be empty",
            required = true
    )
    private int categoryId;

    @NotNull(message = "Class id is required")
    @ApiModelProperty(
            example = "1",
            notes = "Class id cannot be empty",
            required = true
    )
    private int classId;

    @NotNull(message = "Term id is required")
    @ApiModelProperty(
            example = "1",
            notes = "Term id cannot be empty",
            required = true
    )
    private int termId;

    @NotNull(message = "Room id is required")
    @ApiModelProperty(
            example = "1",
            notes = "Room id cannot be empty",
            required = true
    )
    private int roomId;

    @NotNull(message = "Subject id is required")
    @ApiModelProperty(
            example = "1",
            notes = "Subject id cannot be empty",
            required = true
    )
    private int subjectId;

    @NotNull(message = "Sub trainer id is required")
    @ApiModelProperty(
            example = "1",
            notes = "Sub trainer id cannot be empty",
            required = true
    )
    private int subTrainerId;

    @NotNull(message = "Date is required")
    @ApiModelProperty(
            example = "2020-01-08",
            notes = "Date cannot be empty",
            required = true
    )
    private Date date;
}
