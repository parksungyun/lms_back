package com.ac.yy.Service;

import com.ac.yy.DTO.AcademicAdminDTO;
import com.ac.yy.DTO.AcademicDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.DTO.StudentDTO;
import com.ac.yy.Entity.AcademicEntity;
import com.ac.yy.Entity.PositionEntity;
import com.ac.yy.Entity.StudentEntity;
import com.ac.yy.Entity.UserEntity;
import com.ac.yy.Repository.*;
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
    @Autowired CourseRepository courseRepository;
    @Autowired SubjectRepository subjectRepository;
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
                academicDTO.setPosition(positionRepository.findById(academicDTO.getAcademic().getPosition()).get().getPositionName());
                if(academicDTO.getAcademic().getDept() == 0) {
                    academicDTO.setNum(courseRepository.findByAcademicId(academicDTO.getAcademic().getAcademicId()).size());
                }
                else {
                    academicDTO.setNum(subjectRepository.findByAcademicId(academicDTO.getAcademic().getAcademicId()).size());
                }
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

    public ResponseDTO<?> getStudentsBySubjectId(int id) {
        List<StudentDTO> students = new ArrayList<StudentDTO>();
        List<StudentEntity> temp = new ArrayList<StudentEntity>();

        try {
            int courseId = subjectRepository.findById(id).get().getCourseId();
            temp = studentRepository.findByCourseId(courseId);
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
                if(data.getDept() == 0) {
                    tempAcademicDTO.setNum(courseRepository.findByAcademicId(data.getAcademicId()).size());
                }
                else {
                    tempAcademicDTO.setNum(subjectRepository.findByAcademicId(data.getAcademicId()).size());
                }
                academics.add(tempAcademicDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Academics Load Success!", academics);
    }

    public ResponseDTO<?> getStudentByStudentId(int id) {
        StudentDTO student = new StudentDTO();
        StudentEntity temp = null;

        try {
            temp = studentRepository.findByStudentId(id).get();
            student.setStudent(temp);
            student.setUser(userRepository.findByUid(temp.getUid()).get());
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Student Load Success!", student);
    }

    public ResponseDTO<?> getAcademicByAcademicId(int id) {
        AcademicDTO academic = new AcademicDTO();
        AcademicEntity temp = null;

        try {
            temp = academicRepository.findByAcademicId(id).get();
            academic.setAcademic(temp);
            academic.setUser(userRepository.findByUid(temp.getUid()).get());
            PositionEntity tempPositionEntity = positionRepository.findById(academic.getAcademic().getPosition()).get();
            academic.setPosition(tempPositionEntity.getPositionName());
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Academic Load Success!", academic);
    }

    public ResponseDTO<?> getUserByUserId(String userId) {
        UserEntity userEntity = null;

        try {
            userEntity = userRepository.findByUserId(userId).get();
            userEntity.setUserPw("");
            boolean isStudent = studentRepository.existsByUid(userEntity.getUid());
            boolean isAcademic = academicRepository.existsByUid(userEntity.getUid());
            if(isStudent || isAcademic) {
                userEntity = null;
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("User Load Success!", userEntity);
    }

    public ResponseDTO<?> changeAvailable(int id, int value) {
        if (value == 0) {
            value = 1;
        } else {value = 0;}

        try {
            userRepository.modifyingAvailableByUid(id, value);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("ChangeAvailable Success!", null);
    }

    public ResponseDTO<?> mod(AcademicAdminDTO dto) {
            int position;
        try {
            position = positionRepository.findByPositionName(dto.getUserPosition()).get().getPositionId();
            academicRepository.modifyingInfoByUid(dto.getUid(), dto.getUserAuth(), dto.getUserDept(), position, dto.getUserRemark(), dto.getUserAvailable());
            userRepository.modifyingInfoByUid(dto.getUid(), dto.getUserName(), dto.getUserBirth(), dto.getUserAddr(), dto.getUserPhone(), dto.getUserEmail());
        }
        catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Mod Success!", null);
    }
}