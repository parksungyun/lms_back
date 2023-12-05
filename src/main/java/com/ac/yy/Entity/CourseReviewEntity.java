package com.ac.yy.Entity;

import com.ac.yy.DTO.ReviewDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    public CourseReviewEntity(ReviewDTO dto) {
        this.subjectId = dto.getSubjectId();
        this.reviewScore = dto.getReviewScore();
        this.reviewComment = dto.getReviewComment();
        this.studentId = dto.getStudentId();
    }
}
