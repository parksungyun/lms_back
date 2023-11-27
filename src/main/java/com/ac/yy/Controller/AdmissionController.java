package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Service.AdmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admission")
public class AdmissionController {
    @Autowired AdmissionService admissionService;
    @GetMapping("/all")
    public ResponseDTO<?> getAllAdmissionQuestions() {
        ResponseDTO<?> result = admissionService.getAllAdmissionQuestions();
        return result;
    }
    @GetMapping("/{id}")
    public ResponseDTO<?> getAdmissionQuestionById(@PathVariable("id") int id) {
        ResponseDTO<?> result = admissionService.getAdmissionQuestionById(id);
        return result;
    }
}
