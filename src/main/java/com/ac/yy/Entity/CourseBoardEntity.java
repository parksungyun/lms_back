package com.ac.yy.Entity;

import com.ac.yy.DTO.CourseBoardWriteDTO;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "course_board")
public class CourseBoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_board_id")
    private int courseBoardId;

    @Column(name = "course_id")
    private int courseId;

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

    @Column
    private int hits;

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @UpdateTimestamp
    private LocalDateTime modDate;

    public CourseBoardEntity(CourseBoardWriteDTO dto) {
        this.courseId = dto.getCourseId();
        this.academicId = dto.getAcademicId();
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
