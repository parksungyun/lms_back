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
    private int lecture;
    private int homework;
    private int attendance;
    private float total;
    private List<StudentSubjectScoreDTO> subjectScore;
}
