package com.ac.yy.Entity;

import com.ac.yy.DTO.ReplyDTO;
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
    private int academicId;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "answer_reg_date")
    @CreationTimestamp
    private LocalDateTime answerRegDate;

    @Column(name = "answer_mod_date")
    @UpdateTimestamp
    private LocalDateTime answerModDate;

    public AdmissionAnswerEntity(ReplyDTO dto) {
        this.academicId = dto.getAcademicId();
        this.answerContent = dto.getContent();
    }
}
