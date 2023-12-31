package com.ac.yy.Controller;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    // 과정 공지 검색(제목 + 내용)
    @GetMapping("/{id}/board/search/all/{keyword}")
    public ResponseDTO<?> getBoardBySearchAll(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getBoardBySearch(keyword, 0, id);
        return result;
    }

    // 과정 공지 검색(제목)
    @GetMapping("/{id}/board/search/title/{keyword}")
    public ResponseDTO<?> getBoardBySearchTitle(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getBoardBySearch(keyword, 1, id);
        return result;
    }

    // 과정 공지 검색(내용)
    @GetMapping("/{id}/board/search/content/{keyword}")
    public ResponseDTO<?> getBoardBySearchContent(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getBoardBySearch(keyword, 2, id);
        return result;
    }

    // 과정 QnA 작성
    @PostMapping("/qna/write")
    public ResponseDTO<?> writeCourseQuestion(@RequestBody CourseQuestionWriteDTO dto) {
        ResponseDTO<?> result = courseService.writeCourseQuestion(dto);
        return result;
    }

    // 과정 QnA 수정
    @PostMapping("/qna/{id}/mod")
    public ResponseDTO<?> writeCourseQuestion(@PathVariable("id") int id, @RequestBody CourseQuestionWriteDTO dto) {
        ResponseDTO<?> result = courseService.writeCourseQuestion(id, dto);
        return result;
    }

    // 강의 평가 등록
    @PostMapping("/review/write")
    public ResponseDTO<?> writeReview(@RequestBody ReviewDTO dto) {
        ResponseDTO<?> result = courseService.writeReview(dto);
        return result;
    }

    // 과정의 모든 과목에 대한 강의 평가 불러오기
    @GetMapping("/{id}/review")
    public ResponseDTO<?> getSubjectReviewByCourseId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getSubjectReviewByCourseId(id);
        return result;
    }

    // 학생의 과정의 모든 과목에 대한 강의 평가 불러오기
    @GetMapping("/student/{id}/review")
    public ResponseDTO<?> getSubjectReviewByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getSubjectReviewByStudentId(id);
        return result;
    }

    // Admin에서 Course 새로 추가
    @PostMapping("/add")
    public ResponseDTO<?> add(@RequestBody CourseDTO dto) {
        ResponseDTO<?> result = courseService.add(dto);
        return result;
    }

    // Admin에서 Course 수정
    @PostMapping("/mod/{id}")
    public ResponseDTO<?> mod(@RequestBody CourseDTO dto, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.mod(dto, id);
        return result;
    }

    // 학생으로 과정 정보 불러오기
    @GetMapping("/student/{id}")
    public ResponseDTO<?> getCourseByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getCourseByStudentId(id);
        return result;
    }

    // 과정 공지사항 작성
    @PostMapping("/board/write")
    public ResponseDTO<?> writeBoard(@RequestBody CourseBoardWriteDTO dto) {
        ResponseDTO<?> result = courseService.writeBoard(dto);
        return result;
    }

    // 과정 공지사항 수정
    @PostMapping("/board/{id}/mod")
    public ResponseDTO<?> writeBoard(@PathVariable("id") int id, @RequestBody CourseBoardWriteDTO dto) {
        ResponseDTO<?> result = courseService.writeBoard(id, dto);
        return result;
    }

    // 과정 QnA 답변 작성/수정
    @PostMapping("/qna/{id}/reply")
    public ResponseDTO<?> writeReply(@PathVariable("id") int id, @RequestBody ReplyDTO dto) {
        ResponseDTO<?> result = courseService.writeReply(id, dto);
        return result;
    }

    // 과정 QnA 검색(제목 + 내용 + 작성자)
    @GetMapping("/{id}/qna/search/all/{keyword}")
    public ResponseDTO<?> getQnaBySearchAll(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaBySearch(keyword, 0, id);
        return result;
    }

    // 과정 QnA 검색(제목)
    @GetMapping("/{id}/qna/search/title/{keyword}")
    public ResponseDTO<?> getQnaBySearchTitle(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaBySearch(keyword, 1, id);
        return result;
    }

    // 과정 QnA 검색(내용)
    @GetMapping("/{id}/qna/search/content/{keyword}")
    public ResponseDTO<?> getQnaBySearchContent(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaBySearch(keyword, 2, id);
        return result;
    }

    // 과정 QnA 검색(작성자)
    @GetMapping("/{id}/qna/search/writer/{keyword}")
    public ResponseDTO<?> getQnaBySearchWriter(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.getQnaBySearch(keyword, 3, id);
        return result;
    }

    // 과정 공지 삭제
    @DeleteMapping("/board/{id}/delete")
    public ResponseDTO<?> deleteBoard(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.deleteBoard(id);
        return result;
    }

    // 과정 문의 답변 삭제
    @DeleteMapping("/qna/reply/{id}/delete")
    public ResponseDTO<?> deleteReply(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.deleteReply(id);
        return result;
    }

    // 과정 문의 삭제
    @DeleteMapping("/qna/{id}/delete")
    public ResponseDTO<?> deleteQuestion(@PathVariable("id") int id) {
        ResponseDTO<?> result = courseService.deleteQuestion(id);
        return result;
    }
}
