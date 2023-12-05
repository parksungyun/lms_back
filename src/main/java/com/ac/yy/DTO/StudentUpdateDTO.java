package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentUpdateDTO {
    private String userBirth;
    private String userAddr;
    private String userPhone;
    private String userEmail;
}
