package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubjectBoardWriteDTO {
    private int academicId;
    private int subjectId;
    private String title;
    private String content;
}
