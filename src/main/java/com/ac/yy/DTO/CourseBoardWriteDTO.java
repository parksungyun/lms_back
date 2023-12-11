package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CourseBoardWriteDTO {
    private int academicId;
    private int courseId;
    private String title;
    private String content;
}
