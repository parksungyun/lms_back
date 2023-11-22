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
@Table(name = "studies")
public class StudyEntity {
    @Id
    @Column(name = "study_id")
    private int studyId;

    @Column(name = "lecture_id")
    private int lectureId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "is_study")
    private int isStudy;

    @Column(name = "progress_time")
    private int progressTime;

    @Column
    @CreationTimestamp
    private LocalDateTime study_date;
}
