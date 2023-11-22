package com.ac.yy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private int uid;
    private String userId;
    private String userPw;
    private String userName;
    private String userBirth;
    private String userAddr;
    private String userPhone;
    private String userEmail;
}
