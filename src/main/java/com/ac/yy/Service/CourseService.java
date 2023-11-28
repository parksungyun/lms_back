package com.ac.yy.Service;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public ResponseDTO<?> getRecruitingCourses() {
        List<CourseEntity> courses = new ArrayList<CourseEntity>();
        LocalDate now = LocalDate.now();

        try {
            List<CourseEntity> temp = courseRepository.findAll();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            temp.forEach(data -> {
                LocalDate start = LocalDate.parse(data.getRecruitStart(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                LocalDate end = LocalDate.parse(data.getRecruitEnd(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if((now.compareTo(start) >= 0) && (now.compareTo(end) <= 0)) {
                    courses.add(data);
                }
            });

        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Recruiting Courses Load Success!", courses);
    }

    public ResponseDTO<?> getAllCourses() {
        List<CourseEntity> courses = new ArrayList<CourseEntity>();
        try {
            courses = courseRepository.findAll();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("All Courses Load Success!", courses);
    }
}
