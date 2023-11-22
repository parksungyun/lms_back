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
@Table(name = "lectures")
public class LectureEntity {
    @Id
    @Column(name = "lecture_id")
    private int lectureId;

    @Column(name = "subject_id")
    private int subjectId;

    @Column
    private String title;

    @Column
    private String content;

    @Column(name = "video_url")
    private String videoUrl;

    @Column(name = "lecture_time")
    private int lectureTime;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "academic_id")
    private int academicId;

    @Column
    private int hits;

    @Column
    @CreationTimestamp
    private LocalDateTime reg_date;

    @Column
    @UpdateTimestamp
    private LocalDateTime mod_date;
}
