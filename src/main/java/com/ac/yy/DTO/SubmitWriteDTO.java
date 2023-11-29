package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SubmitWriteDTO {
    private int homeworkId;
    private int studentId;
    private String submitContent;
    private String submitFileName;
    private String submitFileUrl;
}
