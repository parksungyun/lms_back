package com.ac.yy.Service;

import com.ac.yy.DTO.AcademicDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.DTO.StudentDTO;
import com.ac.yy.Entity.AcademicEntity;
import com.ac.yy.Entity.PositionEntity;
import com.ac.yy.Entity.StudentEntity;
import com.ac.yy.Entity.UserEntity;
import com.ac.yy.Repository.AcademicRepository;
import com.ac.yy.Repository.PositionRepository;
import com.ac.yy.Repository.StudentRepository;
import com.ac.yy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    @Autowired StudentRepository studentRepository;
    @Autowired AcademicRepository academicRepository;
    @Autowired PositionRepository positionRepository;
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

    public ResponseDTO<?> getStudentsByCourseId(int id) {
        List<StudentDTO> students = new ArrayList<StudentDTO>();
        List<StudentEntity> temp = new ArrayList<StudentEntity>();

        try {
            temp = studentRepository.findByCourseId(id);
            temp.forEach(data -> {
                StudentDTO tempStudentDTO = new StudentDTO();
                tempStudentDTO.setStudent(data);
                tempStudentDTO.setUser(userRepository.findByUid(data.getUid()).get());
                students.add(tempStudentDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Students Load Success!", students);
    }

    public ResponseDTO<?> getAcademicsByDept(int dept) {
        List<AcademicDTO> academics = new ArrayList<AcademicDTO>();
        List<AcademicEntity> temp = new ArrayList<AcademicEntity>();

        try {
            temp = academicRepository.findByDept(dept);
            temp.forEach(data -> {
                AcademicDTO tempAcademicDTO = new AcademicDTO();
                tempAcademicDTO.setAcademic(data);
                tempAcademicDTO.setUser(userRepository.findByUid(data.getUid()).get());
                PositionEntity tempPositionEntity = positionRepository.findById(tempAcademicDTO.getAcademic().getPosition()).get();
                tempAcademicDTO.setPosition(tempPositionEntity.getPositionName());
                academics.add(tempAcademicDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Academics Load Success!", academics);
    }
}