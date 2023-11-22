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
@Table(name = "course_answers")
public class CourseAnswerEntity {
    @Id
    @Column(name = "course_question_id")
    private int courseQuestionId;

    @Column(name = "academic_id")
    private int academicId;

    @Column(name = "answer_content")
    private String answerContent;

    @Column
    @CreationTimestamp
    private LocalDateTime answer_reg_date;

    @Column
    @UpdateTimestamp
    private LocalDateTime answer_mod_date;
}
