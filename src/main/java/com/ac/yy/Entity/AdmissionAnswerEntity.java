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
@Table(name = "admission_answers")
public class AdmissionAnswerEntity {
    @Id
    @Column(name = "admission_question_id")
    private int admissionQuestionId;

    @Column(name = "academic_id")
    private int academic_id;

    @Column(name = "answer_content")
    private String answer_content;

    @Column
    @CreationTimestamp
    private LocalDateTime answer_reg_date;

    @Column
    @UpdateTimestamp
    private LocalDateTime answer_mod_date;
}
