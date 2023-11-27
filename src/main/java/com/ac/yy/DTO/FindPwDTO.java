package com.ac.yy.DTO;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class FindPwDTO {
    private String userId;
    private String userPhone;
}
