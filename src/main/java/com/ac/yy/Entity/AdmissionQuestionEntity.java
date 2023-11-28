package com.ac.yy.Entity;

import com.ac.yy.DTO.AdmissionWriteDTO;
import com.ac.yy.DTO.RegisterDTO;
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
@Table(name = "admission_questions")
public class AdmissionQuestionEntity {
    @Id
    @Column(name = "admission_question_id")
    private int admissionQuestionId;

    @Column(name = "post_pw")
    private String postPw;

    @Column(name = "writer_name")
    private String writerName;

    @Column
    private int age;

    @Column
    private String phone;

    @Column(name = "final_school")
    private String finalSchool;

    @Column(name = "desired_course")
    private int desiredCourse;

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @UpdateTimestamp
    private LocalDateTime modDate;

    public AdmissionQuestionEntity(AdmissionWriteDTO dto) {
        this.postPw = dto.getPostPw();
        this.writerName = dto.getWriterName();
        this.age = dto.getAge();
        this.phone = dto.getPhone();
        this.finalSchool = dto.getFinalSchool();
        this.desiredCourse = dto.getDesiredCourse();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
