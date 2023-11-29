package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
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

    // 모든 과정 불러오기
    @GetMapping("/all")
    public ResponseDTO<?> getAllCourses() {
        ResponseDTO<?> result = courseService.getAllCourses();
        return result;
    }

    // 과정 공지사항 불러오기
    @GetMapping("/{id}/board")
    public ResponseDTO<?> getBoardByCourseId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getBoardByCourseId(id);
        return result;
    }

    // 작성자로 과정 공지사항 불러오기
    @GetMapping("/board/academic/{id}")
    public ResponseDTO<?> getBoardByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getBoardByAcademicId(id);
        return result;
    }

    // 과정 공지사항 게시글 불러오기
    @GetMapping("/board/{id}")
    public ResponseDTO<?> getBoardByCourseBoardId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getBoardByCourseBoardId(id);
        return result;
    }

    // 과정 QnA 불러오기
    @GetMapping("/{id}/qna")
    public ResponseDTO<?> getQnaByCourseId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaByCourseId(id);
        return result;
    }

    // 과정 QnA 게시글 불러오기
    @GetMapping("/qna/{id}")
    public ResponseDTO<?> getQnaByCourseQuestionId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaByCourseQuestionId(id);
        return result;
    }

    // 작성자로 과정 QnA 불러오기
    @GetMapping("/qna/student/{id}")
    public ResponseDTO<?> getQnaByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaByStudentId(id);
        return result;
    }

    // 답글 작성자로 과정 QnA 불러오기
    @GetMapping("/qna/academic/{id}")
    public ResponseDTO<?> getQnaByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaByAcademicId(id);
        return result;
    }
}
