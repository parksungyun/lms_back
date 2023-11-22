package com.ac.yy.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course_review")
public class CourseReviewEntity {
    @Id
    @Column(name = "review_id")
    private int reviewId;

    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "review_score")
    private int reviewScore;

    @Column(name = "review_comment")
    private String reviewComment;

    @Column(name = "student_id")
    private int studentId;

    @Column
    @CreationTimestamp
    private LocalDateTime reg_date;
}
