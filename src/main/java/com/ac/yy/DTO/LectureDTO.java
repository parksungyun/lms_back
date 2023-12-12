package com.ac.yy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureDTO {
    private String title;
    private String content;
    private int videoTime;
    private int academicId;
}
