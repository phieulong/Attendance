package com.group.capstone.attendance.entity;

import com.group.capstone.attendance.model.Term.dto.TermDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "TermInfo",
                classes = @ConstructorResult(
                        targetClass = TermDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "time_begin", type = String.class),
                                @ColumnResult(name = "time_end", type = String.class),
                        }
                )
        )
})

@NamedNativeQuery(name = "getAllTermInfo", resultSetMapping = "TermInfo",
        query = "select id, name, \n" +
                "date_format(time_begin, \"%Y-%m-%d\") as time_begin, \n" +
                "date_format(time_end, \"%Y-%m-%d\") as time_end\n" +
                "from term\n" +
                "where status = 1")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToMany(mappedBy = "term")
    private List<ClassTerm> classTermList;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "timeBegin")
    private Date timeBegin;

    @NotNull
    @Column(name = "timeEnd")
    private Date timeEnd;

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
