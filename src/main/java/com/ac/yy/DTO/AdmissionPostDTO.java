package com.ac.yy.DTO;

import com.ac.yy.Entity.AdmissionAnswerEntity;
import com.ac.yy.Entity.AdmissionQuestionEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AdmissionPostDTO {
    private AdmissionQuestionEntity question;
    private AdmissionAnswerEntity answer;
}
