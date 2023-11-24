package com.ac.yy.Service;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired CourseRepository courseRepository;
    public ResponseDTO<?> getCourseById(int id) {
        CourseEntity course = null;

        try {
            course = courseRepository.findById(id).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Course Load Success!", course);
    }

    public ResponseDTO<?> getCourseByAcademicId(int id) {
        List<CourseEntity> courses;

        try {
            courses = courseRepository.findByAcademicId(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Courses Load Success!", courses);
    }
}
