package com.ac.yy.Service;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.DTO.SubjectDTO;
import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Entity.SubjectEntity;
import com.ac.yy.Repository.CourseRepository;
import com.ac.yy.Repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {
    @Autowired SubjectRepository subjectRepository;
    @Autowired CourseRepository courseRepository;
    public ResponseDTO<?> getSubjectById(int id) {
        SubjectDTO subject = new SubjectDTO();
        SubjectEntity subjectTemp = null;
        CourseEntity courseTemp= null;

        try {
            subjectTemp = subjectRepository.findById(id).get();
            courseTemp = courseRepository.findById(subjectTemp.getCourseId()).get();
            subject.setSubject(subjectTemp);
            subject.setCourse(courseTemp);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subject Load Success!", subject);
    }

    public ResponseDTO<?> getSubjectByAcademicId(int id) {
        List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
        List<SubjectEntity> temp = new ArrayList<SubjectEntity>();

        try {
            temp = subjectRepository.findByAcademicId(id);
            temp.forEach(data -> {
                SubjectDTO tempSubjectDTO = new SubjectDTO();
                tempSubjectDTO.setSubject(data);
                tempSubjectDTO.setCourse(courseRepository.findById(data.getCourseId()).get());
                subjects.add(tempSubjectDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subjects Load Success!", subjects);
    }

    public ResponseDTO<?> getSubjectByCourseId(int id) {
        List<SubjectDTO> subjects = new ArrayList<SubjectDTO>();
        List<SubjectEntity> temp = new ArrayList<SubjectEntity>();

        try {
            temp = subjectRepository.findByCourseId(id);
            temp.forEach(data -> {
                SubjectDTO tempSubjectDTO = new SubjectDTO();
                tempSubjectDTO.setSubject(data);
                tempSubjectDTO.setCourse(courseRepository.findById(id).get());
                subjects.add(tempSubjectDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Subjects Load Success!", subjects);
    }
}
