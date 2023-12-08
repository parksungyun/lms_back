package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ChangePwDTO {
    private String currentPw;
    private String newPw;
}
