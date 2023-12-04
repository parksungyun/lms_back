package com.ac.yy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectQuestionWriteDTO {
    private int subjectId;
    private int studentId;
    private String title;
    private String content;
}
