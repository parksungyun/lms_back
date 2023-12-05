package com.ac.yy.Entity;

import com.ac.yy.DTO.AcademicAdminDTO;
import com.ac.yy.DTO.CourseDTO;
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
@Table(name = "courses")
public class CourseEntity {
    @Id
    @Column(name = "course_id")
    private int courseId;

    @Column
    private int academicId;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "subject_no")
    private int subjectNo;

    @Column
    private int capacity;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @Column(name = "recruit_start")
    private String recruitStart;

    @Column(name = "recruit_end")
    private String recruitEnd;

    @Column(name = "course_info")
    private String courseInfo;

    @Column(name = "course_photo")
    private String coursePhoto;

    @Column(name = "reg_date")
    @CreationTimestamp
    private LocalDateTime regDate;

    @Column(name = "mod_date")
    @UpdateTimestamp
    private LocalDateTime modDate;

    public CourseEntity(CourseDTO dto) {
        this.academicId = dto.getAcademicId();
        this.courseName = dto.getCourseName();
        this.subjectNo = dto.getSubjectNo();
        this.capacity = dto.getCapacity();
        this.startDate = dto.getStartDate();
        this.endDate = dto.getEndDate();
        this.recruitStart = dto.getRecruitStart();
        this.recruitEnd = dto.getRecruitEnd();
        this.courseInfo = dto.getCourseInfo();
    }
}
