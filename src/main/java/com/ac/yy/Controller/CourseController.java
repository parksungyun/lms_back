package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/course")
public class CourseController {
    @Autowired CourseService courseService;
    // 과정 정보 불러오기
    @GetMapping("/{id}")
    public ResponseDTO<?> getCourseById(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getCourseById(id);
        return result;
    }

    // 매니저의 담당 과정 불러오기
    @GetMapping("/academic/{id}")
    public ResponseDTO<?> getCourseByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getCourseByAcademicId(id);
        return result;
    }

    // 현재 모집중인 과정 정보 불러오기
    @GetMapping("/recruit")
    public ResponseDTO<?> getRecruitingCourses() {
        ResponseDTO<?> result = courseService.getRecruitingCourses();
        return result;
    }
}
