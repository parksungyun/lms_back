package com.ac.yy.Entity;

import com.ac.yy.DTO.HomeworkDTO;
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
@Table(name = "homeworks")
public class HomeworkEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_id")
    private int homeworkId;

    @Column(name = "subject_id")
    private int subjectId;

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "academic_id")
    private int academicId;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @UpdateTimestamp
    private LocalDateTime modDate;

    public HomeworkEntity(HomeworkDTO dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.academicId = dto.getAcademicId();
        this.subjectId = dto.getSubjectId();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
    }
}
