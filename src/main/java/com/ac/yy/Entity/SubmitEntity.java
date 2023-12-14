package com.ac.yy.Entity;

import com.ac.yy.DTO.SubmitWriteDTO;
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
@Table(name = "submits")
public class SubmitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "submit_id")
    private int submitId;

    @Column(name = "homework_id")
    private int homeworkId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "submit_content")
    private String submitContent;

    @Column(name = "submit_file_name")
    private String submitFileName;

    @Column(name = "submit_file_url")
    private String submitFileUrl;

    @Column(name = "submit_reg_date")
    @CreationTimestamp
    private LocalDateTime submitRegDate;

    @Column(name = "submit_mod_date")
    @UpdateTimestamp
    private LocalDateTime submitModDate;

    public SubmitEntity(SubmitWriteDTO dto) {
        this.homeworkId = dto.getHomeworkId();
        this.studentId = dto.getStudentId();
        this.submitContent = dto.getSubmitContent();
        this.submitFileName = dto.getSubmitFileName();
        this.submitFileUrl = dto.getSubmitFileUrl();
    }
}
