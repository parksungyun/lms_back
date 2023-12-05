package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ReviewDTO {
    private int subjectId;
    private int reviewScore;
    private String reviewComment;
    private int studentId;
}
