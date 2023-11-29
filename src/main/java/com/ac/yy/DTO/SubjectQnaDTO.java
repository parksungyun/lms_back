package com.ac.yy.DTO;

import com.ac.yy.Entity.SubjectAnswerEntity;
import com.ac.yy.Entity.SubjectQuestionEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubjectQnaDTO {
    private SubjectQuestionEntity question;
    private SubjectAnswerEntity answer;
}
