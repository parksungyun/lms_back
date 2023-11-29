package com.ac.yy.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcademicAdminDTO {
    private int uid;
    private String userId;
    private String userName;
    private String userBirth;
    private int userDept;
    private File userPhoto;
    private String userPosition;
    private String userPhone;
    private String userAddr;
    private String userEmail;
    private String userRemark;
    private int userAuth;
    private int userAvailable;
}
