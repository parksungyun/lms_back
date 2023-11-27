package com.ac.yy.DTO;

import com.ac.yy.Entity.AcademicEntity;
import com.ac.yy.Entity.StudentEntity;
import com.ac.yy.Entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AcademicDTO {
    private UserEntity user;
    private AcademicEntity academic;
    private String position;
}
