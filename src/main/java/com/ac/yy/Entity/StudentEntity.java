package com.ac.yy.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    @Column(name = "student_id")
    private int studentId;

    @Column
    private int uid;

    @Column(name = "course_id")
    private int courseId;

    @Column
    private int available;

    @Column
    @CreationTimestamp
    private LocalDateTime reg_date;

    @Column
    @UpdateTimestamp
    private LocalDateTime mod_date;
}
