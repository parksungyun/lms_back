package com.ac.yy.DTO;

import com.ac.yy.Entity.StudentEntity;
import com.ac.yy.Entity.UserEntity;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StudentDTO {
    private UserEntity user;
    private StudentEntity student;
}
