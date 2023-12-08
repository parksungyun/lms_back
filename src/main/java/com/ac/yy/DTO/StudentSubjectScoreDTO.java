package com.ac.yy.DTO;

import com.ac.yy.Entity.SubjectEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentSubjectScoreDTO {
    private SubjectEntity subject;
    private int lecture;
    private int homework;
}
