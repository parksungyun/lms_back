package com.ac.yy.DTO;

import com.ac.yy.Entity.CourseAnswerEntity;
import com.ac.yy.Entity.CourseQuestionEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CourseQnaDTO {
    private CourseQuestionEntity question;
    private CourseAnswerEntity answer;
}
