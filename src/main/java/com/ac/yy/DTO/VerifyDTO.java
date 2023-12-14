package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class VerifyDTO {
    private String toNumber;
    private String randomNumber;
}
