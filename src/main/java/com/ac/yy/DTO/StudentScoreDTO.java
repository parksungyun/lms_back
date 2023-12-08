package com.ac.yy.DTO;

import com.ac.yy.Entity.CourseEntity;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentScoreDTO {
    private CourseEntity course;
    private float lecture;
    private float homework;
    private float attendance;
    private float total;
    private List<StudentSubjectScoreDTO> subjectScore;
}
