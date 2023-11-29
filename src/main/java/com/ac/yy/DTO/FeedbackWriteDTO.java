package com.ac.yy.DTO;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FeedbackWriteDTO {
    private int submitId;
    private int hwScore;
    private String hwComment;
    private int academicId;
}
