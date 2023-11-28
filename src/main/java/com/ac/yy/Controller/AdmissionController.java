package com.ac.yy.Controller;

import com.ac.yy.DTO.AdmissionWriteDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.AdmissionQuestionEntity;
import com.ac.yy.Service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admission")
public class AdmissionController {
    @Autowired AdmissionService admissionService;

    // 모든 입학 상담 게시글 불러오기
    @GetMapping("/all")
    public ResponseDTO<?> getAllAdmissionQuestions() {
        ResponseDTO<?> result = admissionService.getAllAdmissionQuestions();
        return result;
    }

    // 입학 상담 게시글 정보 불러오기
    @GetMapping("/{id}")
    public ResponseDTO<?> getAdmissionQuestionById(@PathVariable("id") int id) {
        ResponseDTO<?> result = admissionService.getAdmissionQuestionById(id);
        return result;
    }

    // 입학 상담 게시글 검색(제목 + 작성자)
    @GetMapping("/search/all/{keyword}")
    public ResponseDTO<?> getAdmissionQuestionsBySearchAll(@PathVariable("keyword") String keyword) {
        ResponseDTO<?> result = admissionService.getAdmissionQuestionsBySearch(keyword, 0);
        return result;
    }

    // 입학 상담 게시글 검색(제목)
    @GetMapping("/search/title/{keyword}")
    public ResponseDTO<?> getAdmissionQuestionsBySearchTitle(@PathVariable("keyword") String keyword) {
        ResponseDTO<?> result = admissionService.getAdmissionQuestionsBySearch(keyword, 1);
        return result;
    }

    // 입학 상담 게시글 검색(작성자)
    @GetMapping("/search/writer/{keyword}")
    public ResponseDTO<?> getAdmissionQuestionsBySearchWriter(@PathVariable("keyword") String keyword) {
        ResponseDTO<?> result = admissionService.getAdmissionQuestionsBySearch(keyword, 2);
        return result;
    }

    // 입학 상담 게시글 작성
    @PostMapping(value = "/write")
    public ResponseDTO<?> writeAdmissionQuestion(@RequestBody AdmissionWriteDTO requestBody) {
        System.out.println(requestBody.toString());
        ResponseDTO<?> result = admissionService.writeAdmissionQuestion(requestBody);
        return result;
    }
}
