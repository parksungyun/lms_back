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
@Table(name = "subject_questions")
public class SubjectQuestionEntity {
    @Id
    @Column(name = "subject_question_id")
    private int subjectQuestionId;

    @Column(name = "subject_id")
    private int subjectId;

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
    private int hits;

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @UpdateTimestamp
    private LocalDateTime modDate;
}
