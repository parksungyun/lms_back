package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Service.CourseService;
import com.ac.yy.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/subject")
public class SubjectController {
    @Autowired SubjectService subjectService;
    // 과목 정보 불러오기
    @GetMapping("/{id}")
    public ResponseDTO<?> getSubjectById(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubjectById(id);
        return result;
    }

    // 강사의 담당 과정 불러오기
    @GetMapping("/academic/{id}")
    public ResponseDTO<?> getSubjectByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubjectByAcademicId(id);
        return result;
    }

    // 과정의 과목 불러오기
    @GetMapping("/course/{id}")
    public ResponseDTO<?> getSubjectByCourseId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubjectByCourseId(id);
        return result;
    }
}
