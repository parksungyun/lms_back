package com.ac.yy.Service;

import com.ac.yy.DTO.AcademicDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.DTO.StudentDTO;
import com.ac.yy.Entity.UserEntity;
import com.ac.yy.Repository.AcademicRepository;
import com.ac.yy.Repository.StudentRepository;
import com.ac.yy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired AcademicRepository academicRepository;
    public ResponseDTO<?> getUserByUid(int uid) {
        StudentDTO studentDTO = new StudentDTO();
        AcademicDTO academicDTO = new AcademicDTO();
        UserEntity user = null;

        try {
            user = userRepository.findByUid(uid).get();
            user.setUserPw("");

            boolean isStudentExist = studentRepository.existsByUid(user.getUid());
            boolean isAcademicExist = academicRepository.existsByUid(user.getUid());

            if(isStudentExist) {
                studentDTO.setUser(user);
                studentDTO.setStudent(studentRepository.findByUid(user.getUid()).get());
                System.out.println(studentDTO);
                return ResponseDTO.setSuccess("Student Load Success!", studentDTO);
            }
            if(isAcademicExist) {
                academicDTO.setUser(user);
                academicDTO.setAcademic(academicRepository.findByUid(user.getUid()).get());
                System.out.println(academicDTO);
                return ResponseDTO.setSuccess("Academic Load Success!", academicDTO);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("User Load Success!", user);
    }
}