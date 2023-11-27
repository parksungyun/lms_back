package com.ac.yy.Service;

import com.ac.yy.DTO.AdmissionPostDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.AdmissionQuestionEntity;
import com.ac.yy.Repository.AdmissionAnswerRepository;
import com.ac.yy.Repository.AdmissionQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdmissionService {
    @Autowired AdmissionQuestionRepository admissionQuestionRepository;
    @Autowired AdmissionAnswerRepository admissionAnswerRepository;
    // 모든 입학 상담 게시글 불러오기
    public ResponseDTO<?> getAllAdmissionQuestions() {
        List<AdmissionPostDTO> posts = new ArrayList<AdmissionPostDTO>();
        List<AdmissionQuestionEntity> temp = new ArrayList<AdmissionQuestionEntity>();

        try {
            temp = admissionQuestionRepository.findAll();
            temp.forEach(data -> {
                AdmissionPostDTO tempAdmissionPostDTO = new AdmissionPostDTO();
                tempAdmissionPostDTO.setQuestion(data);
                if(admissionAnswerRepository.existsById(data.getAdmissionQuestionId())){
                    tempAdmissionPostDTO.setAnswer(admissionAnswerRepository.findById(data.getAdmissionQuestionId()).get());
                }
                else tempAdmissionPostDTO.setAnswer(null);
                posts.add(tempAdmissionPostDTO);
            });
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("All Admission Questions Load Success!", posts);
    }
}
