package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class HomeworkDTO {
    private int academicId;
    private int subjectId;
    private String title;
    private String content;
    private String startDate;
    private String endDate;
}
