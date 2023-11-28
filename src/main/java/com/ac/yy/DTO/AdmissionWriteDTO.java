package com.ac.yy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionWriteDTO {
    private int admissionQuestionId;
    private String postPw;
    private String writerName;
    private int age;
    private String phone;
    private String finalSchool;
    private int desiredCourse;
    private String title;
    private String content;
}
