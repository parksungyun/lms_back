package com.ac.yy.Controller;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.SubjectEntity;
import com.ac.yy.Service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    // 학생Id와 과제Id로 제출 과제 불러오기
    @GetMapping("/submit/student/{studentId}/homework/{homeworkId}")
    public ResponseDTO<?> getSubmitByStudentIdAndHomeworkId(@PathVariable("studentId") int studentId, @PathVariable("homeworkId") int homeworkId) {
        ResponseDTO<?> result = subjectService.getSubmitByStudentIdAndHomeworkId(studentId, homeworkId);
        return result;
    }

    // 과목과 작성자로 제출 과제 불러오기
    @GetMapping("/submit/student/{studentId}/{subjectId}")
    public ResponseDTO<?> getSubmitsByStudentId(@PathVariable("studentId") int studentId, @PathVariable("subjectId") int subjectId) {
        ResponseDTO<?> result = subjectService.getSubmitsByStudentIdAndSubjectId(studentId, subjectId);
        return result;
    }

    // 과제의 모든 제출 확인
    @GetMapping("homework/{id}/submit")
    public ResponseDTO<?> getSubmitsByHomeworkId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubmitsByHomeworkId(id);
        return result;
    }

    // 과목의 모든 과제의 모든 제출 확인
    @GetMapping("/{subjectId}/submits")
    public ResponseDTO<?> getSubmitsBySubjectId(@PathVariable("subjectId") int id) {
        ResponseDTO<?> result = subjectService.getSubmitsBySubjectId(id);
        return result;
    }

    // 과제 제출에 대한 피드백 작성/수정
    @PostMapping(value = "/{id}/feedback")
    public ResponseDTO<?> writeFeedback(@PathVariable("id") int id, @RequestBody FeedbackWriteDTO requestBody) {
        ResponseDTO<?> result = subjectService.writeFeedback(id, requestBody);
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

    // 학생의 과목에 대한 모든 강의 학습 정보 불러오기
    @GetMapping("/{subjectId}/study/{studentId}")
    public ResponseDTO<?> getStudyByStudentIdAndSubjectId(@PathVariable("studentId") int studentId, @PathVariable("subjectId") int subjectId) {
        ResponseDTO<?> result = subjectService.getStudyByStudentIdAndSubjectId(studentId, subjectId);
        return result;
    }

    // 학생의 강의에 대한 강의 학습 정보 불러오기
    @GetMapping("/study/{lectureId}/{studentId}")
    public ResponseDTO<?> getStudyByStudentIdAndLectureId(@PathVariable("studentId") int studentId, @PathVariable("lectureId") int lectureId) {
        ResponseDTO<?> result = subjectService.getStudyByStudentIdAndLectureId(studentId, lectureId);
        return result;
    }

    // 과목으로 강의에 대한 강의 학습 정보 불러오기
    @GetMapping("/{subjectId}/study")
    public ResponseDTO<?> getStudyBySubjectId(@PathVariable("subjectId") int subjectId) {
        ResponseDTO<?> result = subjectService.getStudyBySubjectId(subjectId);
        return result;
    }

    // 강의에 대한 강의 학습 정보 불러오기
    @GetMapping("/study/{lectureId}")
    public ResponseDTO<?> getStudyByLectureId(@PathVariable("lectureId") int lectureId) {
        ResponseDTO<?> result = subjectService.getStudyByLectureId(lectureId);
        return result;
    }

    // 학생으로 과정의 과목 정보 불러오기
    @GetMapping("/student/{id}")
    public ResponseDTO<?> getSubjectByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubjectByStudentId(id);
        return result;
    }

    // 과목 공지 검색(제목 + 내용)
    @GetMapping("/{id}/board/search/all/{keyword}")
    public ResponseDTO<?> getBoardBySearchAll(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getBoardBySearch(keyword, 0, id);
        return result;
    }

    // 과목 공지 검색(제목)
    @GetMapping("/{id}/board/search/title/{keyword}")
    public ResponseDTO<?> getBoardBySearchTitle(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getBoardBySearch(keyword, 1, id);
        return result;
    }

    // 과목 공지 검색(내용)
    @GetMapping("/{id}/board/search/content/{keyword}")
    public ResponseDTO<?> getBoardBySearchContent(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getBoardBySearch(keyword, 2, id);
        return result;
    }

    // 강의 검색(제목 + 내용)
    @GetMapping("/{id}/lecture/search/all/{keyword}")
    public ResponseDTO<?> getLectureBySearchAll(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getLectureBySearch(keyword, 0, id);
        return result;
    }

    // 강의 검색(제목)
    @GetMapping("/{id}/lecture/search/title/{keyword}")
    public ResponseDTO<?> getLectureBySearchTitle(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getLectureBySearch(keyword, 1, id);
        return result;
    }

    // 강의 검색(내용)
    @GetMapping("/{id}/lecture/search/content/{keyword}")
    public ResponseDTO<?> getLectureBySearchContent(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getLectureBySearch(keyword, 2, id);
        return result;
    }

    // 과목 QnA 검색 (제목 + 내용 + 작성자)
    @GetMapping("/{id}/qna/search/all/{keyword}")
    public ResponseDTO<?> getQnaBySearchAll(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaBySearch(keyword, 0, id);
        return result;
    }

    // 과목 QnA 검색(제목)
    @GetMapping("/{id}/qna/search/title/{keyword}")
    public ResponseDTO<?> getQnaBySearchTitle(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaBySearch(keyword, 1, id);
        return result;
    }

    // 과목 QnA 검색(내용)
    @GetMapping("/{id}/qna/search/content/{keyword}")
    public ResponseDTO<?> getQnaBySearchContent(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaBySearch(keyword, 2, id);
        return result;
    }

    // 과목 QnA 검색(작성자)
    @GetMapping("/{id}/qna/search/writer/{keyword}")
    public ResponseDTO<?> getQnaBySearchWriter(@PathVariable("keyword") String keyword, @PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getQnaBySearch(keyword, 3, id);
        return result;
    }

    // 과목 QnA 작성
    @PostMapping("/qna/write")
    public ResponseDTO<?> writeSubjectQuestion(@RequestBody SubjectQuestionWriteDTO dto) {
        ResponseDTO<?> result = subjectService.writeSubjectQuestion(dto);
        return result;
    }

    // 과목 QnA 수정
    @PostMapping("/qna/{id}/mod")
    public ResponseDTO<?> writeSubjectQuestion(@PathVariable("id") int id, @RequestBody SubjectQuestionWriteDTO dto) {
        ResponseDTO<?> result = subjectService.writeSubjectQuestion(id, dto);
        return result;
    }

    // 과목에 대한 강의 평가 불러오기
    @GetMapping("/{id}/review")
    public ResponseDTO<?> getSubjectReviewBySubjectId(@PathVariable("id") int id) {
        ResponseDTO<?> result = subjectService.getSubjectReviewBySubjectId(id);
        return result;
    }

    //admin course에서 subject 추가
    @PostMapping("/add/{id}")
    public ResponseDTO<?> add(@RequestBody List<SubjectAddDTO> subjects, @PathVariable("id") int id) {
        System.out.println(subjects);
        ResponseDTO<?> result = subjectService.add(subjects, id);
        return result;
    }

    //admin course에서 subject 수정
    @PostMapping("/mod/{id}/{check}")
    public ResponseDTO<?> mod(@RequestBody List<SubjectAddDTO> subjects, @PathVariable("id") int id, @PathVariable("check") int check) {
        System.out.println(subjects);
        ResponseDTO<?> result = subjectService.mod(subjects, id, check);
        return result;
    }

    // 과목 공지사항 작성
    @PostMapping("/board/write")
    public ResponseDTO<?> writeBoard(@RequestBody SubjectBoardWriteDTO dto) {
        ResponseDTO<?> result = subjectService.writeBoard(dto);
        return result;
    }

    // 과목 공지사항 수정
    @PostMapping("/board/{id}/mod")
    public ResponseDTO<?> writeBoard(@PathVariable("id") int id, @RequestBody SubjectBoardWriteDTO dto) {
        ResponseDTO<?> result = subjectService.writeBoard(id, dto);
        return result;
    }

    // 과목 QnA 답변 작성/수정
    @PostMapping("/qna/{id}/reply")
    public ResponseDTO<?> writeReply(@PathVariable("id") int id, @RequestBody ReplyDTO dto) {
        ResponseDTO<?> result = subjectService.writeReply(id, dto);
        return result;
    }

    // 강사가 강의 추가
    @PostMapping("/{state}/lecture/write")
    public ResponseDTO<?> writeLecture(@PathVariable("state") int subjectId, @RequestBody LectureDTO dto) {
        ResponseDTO<?> result = subjectService.writeLecture(subjectId, dto);
        return result;
    }

    // 강사가 과제 작성
    @PostMapping("/homework/write")
    public ResponseDTO<?> writeHomework(@RequestBody HomeworkDTO dto) {
        ResponseDTO<?> result = subjectService.writeHomework(dto);
        return result;
    }

    // 강사가 과제 수정
    @PostMapping("/homework/{id}/mod")
    public ResponseDTO<?> writeHomework(@PathVariable("id") int id, @RequestBody HomeworkDTO dto) {
        ResponseDTO<?> result = subjectService.writeHomework(id, dto);
        return result;
    }

    // 강사가 강의 수정
    @PostMapping("/{state}/lecture/mod")
    public ResponseDTO<?> modLecture(@PathVariable("state") int lectureId, @RequestBody LectureDTO dto) {
        ResponseDTO<?> result = subjectService.modLecture(lectureId, dto);
        return result;
    }
}
