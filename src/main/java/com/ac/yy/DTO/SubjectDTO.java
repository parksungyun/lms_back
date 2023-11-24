package com.ac.yy.DTO;

import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Entity.SubjectEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubjectDTO {
    private CourseEntity course;
    private SubjectEntity subject;
}
