package com.ac.yy.Controller;

import com.ac.yy.DTO.FeedbackWriteDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.DTO.StudyDTO;
import com.ac.yy.DTO.SubmitWriteDTO;
import com.ac.yy.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // 과목 공지사항 불러오기
    @GetMapping("/{id}/board")
    public ResponseDTO<?> getBoardBySubjectId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getBoardBySubjectId(id);
        return result;
    }

    // 작성자로 과목 공지사항 불러오기
    @GetMapping("/board/academic/{id}")
    public ResponseDTO<?> getBoardByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getBoardByAcademicId(id);
        return result;
    }

    // 과목 공지사항 게시글 불러오기
    @GetMapping("/board/{id}")
    public ResponseDTO<?> getBoardBySubjectBoardId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getBoardBySubjectBoardId(id);
        return result;
    }

    // 과목 과제 불러오기
    @GetMapping("/{id}/homework")
    public ResponseDTO<?> getHomeworksBySubjectId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getHomeworksBySubjectId(id);
        return result;
    }

    // 과정의 모든 과목의 과제 불러오기
    @GetMapping("/course/{id}/homework")
    public ResponseDTO<?> getHomeworksByCourseId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getHomeworksByCourseId(id);
        return result;
    }

    // 작성자로 과제 불러오기
    @GetMapping("/homework/academic/{id}")
    public ResponseDTO<?> getHomeworksByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getHomeworksByAcademicId(id);
        return result;
    }

    // 과목 과제 게시글 불러오기
    @GetMapping("/homework/{id}")
    public ResponseDTO<?> getHomeworkByHomeworkId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getHomeworkByHomeworkId(id);
        return result;
    }

    // 과목 강의 불러오기
    @GetMapping("/{id}/lecture")
    public ResponseDTO<?> getLecturesBySubjectId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getLecturesBySubjectId(id);
        return result;
    }

    // 작성자로 강의 불러오기
    @GetMapping("/lecture/academic/{id}")
    public ResponseDTO<?> getLecturesByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getLecturesByAcademicId(id);
        return result;
    }

    // 과목 강의 게시글 불러오기
    @GetMapping("/lecture/{id}")
    public ResponseDTO<?> getLectureByLectureId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getLectureByLectureId(id);
        return result;
    }

    // 과목 QnA 불러오기
    @GetMapping("/{id}/qna")
    public ResponseDTO<?> getQnaBySubjectId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaBySubjectId(id);
        return result;
    }

    // 과목 QnA 게시글 불러오기
    @GetMapping("/qna/{id}")
    public ResponseDTO<?> getQnaBySubjectQuestionId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaBySubjectQuestionId(id);
        return result;
    }

    // 작성자로 과목 QnA 불러오기
    @GetMapping("/qna/student/{id}")
    public ResponseDTO<?> getQnaByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaByStudentId(id);
        return result;
    }

    // 답글 작성자로 과목 QnA 불러오기
    @GetMapping("/qna/academic/{id}")
    public ResponseDTO<?> getQnaByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaByAcademicId(id);
        return result;
    }

    // 과제 제출
    @PostMapping(value = "/submit")
    public ResponseDTO<?> submitHomework(@RequestBody SubmitWriteDTO requestBody) {
        ResponseDTO<?> result = subjectService.submitHomework(requestBody);
        return result;
    }

    // 제출한 과제 게시글 확인
    @GetMapping("/submit/{id}")
    public ResponseDTO<?> getSubmitBySubmitId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubmitBySubmitId(id);
        return result;
    }

    // 작성자로 제출 과제 불러오기
    @GetMapping("/submit/student/{id}")
    public ResponseDTO<?> getSubmitsByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubmitsByStudentId(id);
        return result;
    }

    // 과제의 모든 제출 확인
    @GetMapping("homework/{id}/submit")
    public ResponseDTO<?> getSubmitsByHomeworkId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubmitsByHomeworkId(id);
        return result;
    }

    // 과제 제출에 대한 피드백 작성
    @PostMapping(value = "/feedback")
    public ResponseDTO<?> feedbackSubmit(@RequestBody FeedbackWriteDTO requestBody) {
        ResponseDTO<?> result = subjectService.feedbackSubmit(requestBody);
        return result;
    }

    // 강의 학습
    @PostMapping(value = "/study")
    public ResponseDTO<?> studyLecture(@RequestBody StudyDTO requestBody) {
        ResponseDTO<?> result = subjectService.studyLecture(requestBody);
        return result;
    }

    // 학생의 과목별 강의 진행도 불러오기
    @GetMapping("/{subjectId}/progress/{studentId}")
    public ResponseDTO<?> getProgressByStudentIdAndSubjectId(@PathVariable("studentId") int studentId, @PathVariable("subjectId") int subjectId) {
        ResponseDTO<?> result = subjectService.getProgressByStudentIdAndSubjectId(studentId, subjectId);
        return result;
    }

    // 학생의 모든 과목 강의 진행도 불러오기
    @GetMapping("/progress/{studentId}")
    public ResponseDTO<?> getProgressByStudentId(@PathVariable("studentId") int studentId) {
        ResponseDTO<?> result = subjectService.getProgressByStudentId(studentId);
        return result;
    }
}
