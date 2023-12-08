package com.ac.yy.Controller;

import com.ac.yy.DTO.AcademicAdminDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.DTO.StudentUpdateDTO;
import com.ac.yy.Service.UserService;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired UserService userService;
    // uid로 유저 정보 불러오기(User-Student Or User-Academic)
    @GetMapping("/{id}")
    public ResponseDTO<?> getUserByUid(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getUserByUid(id);
        return result;
    }

    // 과정에 속한 학생 정보 불러오기
    @GetMapping("/students/{id}")
    public ResponseDTO<?> getStudentsByCourseId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getStudentsByCourseId(id);
        return result;
    }

    // 과목으로 과정에 속한 학생 정보 불러오기
    @GetMapping("/students/subject/{id}")
    public ResponseDTO<?> getStudentsBySubjectId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getStudentsBySubjectId(id);
        return result;
    }

    // 모든 강사 정보 불러오기
    @GetMapping("/trainers")
    public ResponseDTO<?> getAllTrainers() {
        ResponseDTO<?> result = userService.getAcademicsByDept(1);
        return result;
    }
    
    // 모든 매니저 정보 불러오기
    @GetMapping("/managers")
    public ResponseDTO<?> getAllManagers() {
        ResponseDTO<?> result = userService.getAcademicsByDept(0);
        return result;
    }

    // studentId로 student 정보 불러오기
    @GetMapping("/student/{id}")
    public ResponseDTO<?> getStudentByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getStudentByStudentId(id);
        return result;
    }

    // academicId로 academic 정보 불러오기
    @GetMapping("/academic/{id}")
    public ResponseDTO<?> getAcademicByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getAcademicByAcademicId(id);
        return result;
    }

    //userId로 user 정보 불러오기
    @GetMapping("/search/{id}")
    public ResponseDTO<?> getUserByUserId(@PathVariable("id") String userId) {
        ResponseDTO<?> result = userService.getUserByUserId(userId);
        return result;
    }

    //user에 uid로 상태 활성화 비활성화
    @GetMapping("/{id}/changeAvailable/{value}")
    public ResponseDTO<?> changeAvailable(@PathVariable("id") int id, @PathVariable("value") int value) {
        ResponseDTO<?> result = userService.changeAvailable(id, value);
        return result;
    }

    //admin이 academic 강사, 매니저 정보 수정
    @PostMapping("/academic/mod")
    public ResponseDTO<?> mod(@RequestBody AcademicAdminDTO dto) {
        ResponseDTO<?> result = userService.mod(dto);
        return result;
    }

    @PostMapping("/academic/add")
    public ResponseDTO<?> add(@RequestBody AcademicAdminDTO dto) {
        ResponseDTO<?> result = userService.add(dto);
        return result;
    }

    // 학생 개인정보 수정
    @PostMapping("/student/{uid}/update")
    public ResponseDTO<?> studentUpdate(@PathVariable("uid") int id, @RequestBody StudentUpdateDTO dto) {
        ResponseDTO<?> result = userService.studentUpdate(id, dto);
        return result;
    }

    // 학생 출결 불러오기
    @GetMapping("/student/{id}/attendance")
    public ResponseDTO<?> getStudentAttendanceByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getStudentAttendanceByStudentId(id);
        return result;
    }

    // 모든 학생 불러오기
    @GetMapping("/students")
    public ResponseDTO<?> getAllStudents() {
        ResponseDTO<?> result = userService.getAllStudents();
        return result;
    }

    // 학생이 작성한 게시글 불러오기
    @GetMapping("/posts/student/{id}")
    public ResponseDTO<?> getAllMyPostsByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getAllPosts(id, 0);
        return result;
    }

    // 매니저나 강사가 작성한 게시글 불러오기
    @GetMapping("/posts/academic/{id}")
    public ResponseDTO<?> getAllMyPostsByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getAllPosts(id, 1);
        return result;
    }

    // 매니저나 강사가 작성한 답글 불러오기
    @GetMapping("/replies/academic/{id}")
    public ResponseDTO<?> getAllMyRepliesByAcademicId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getAllReplies(id);
        return result;
    }

    // 학생 성적 조회
    @GetMapping("/score/student/{id}")
    public ResponseDTO<?> getScoreByStudentId(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getScoreByStudentId(id);
        return result;
    }

    @PostMapping("/student/{uid}/add/{id}")
    public ResponseDTO<?> studentadd(@PathVariable("uid") int userUid ,@PathVariable("id") int id) {
        System.out.println(userUid);
        ResponseDTO<?> result = userService.studentadd(userUid, id);
        return result;
    }
}