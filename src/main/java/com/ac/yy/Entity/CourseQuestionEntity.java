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
@Table(name = "course_questions")
public class CourseQuestionEntity {
    @Id
    @Column(name = "course_question_id")
    private int courseQuestionId;

    @Column(name = "course_id")
    private int courseId;

    @Column(name = "student_id")
    private int studentId;

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url")
    private String fileUrl;

    @Column
    @CreationTimestamp
    private LocalDateTime reg_date;

    @Column
    @UpdateTimestamp
    private LocalDateTime mod_date;
}
