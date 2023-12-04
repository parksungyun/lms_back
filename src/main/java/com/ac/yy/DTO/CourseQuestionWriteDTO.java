package com.ac.yy.DTO;

import com.ac.yy.Entity.CourseQuestionEntity;
import lombok.*;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CourseQuestionWriteDTO {
    private int studentId;
    private String title;
    private String content;
}
