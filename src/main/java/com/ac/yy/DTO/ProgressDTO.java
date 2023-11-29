package com.ac.yy.DTO;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProgressDTO {
    private int studentId;
    private int subjectId;
    private String subjectName;
    private int numOfLecture;
    private int numOfStudy;
}
