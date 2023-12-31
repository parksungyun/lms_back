package com.ac.yy.Entity;

import com.ac.yy.DTO.CourseQuestionWriteDTO;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @UpdateTimestamp
    private LocalDateTime modDate;

    public CourseQuestionEntity(CourseQuestionWriteDTO dto) {
        this.studentId = dto.getStudentId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
