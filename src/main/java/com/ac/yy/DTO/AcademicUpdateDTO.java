package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AcademicUpdateDTO {
    private String userPhone;
    private String userAddr;
    private String userEmail;
    private String userRemark;
}
