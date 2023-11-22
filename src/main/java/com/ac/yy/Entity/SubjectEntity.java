package com.ac.yy.Entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "subjects")
public class SubjectEntity {
    @Id
    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "academic_id")
    private int academicId;
}
