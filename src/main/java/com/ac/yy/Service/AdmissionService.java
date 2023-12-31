package com.ac.yy.Service;

import com.ac.yy.DTO.AdmissionPostDTO;
import com.ac.yy.DTO.AdmissionWriteDTO;
import com.ac.yy.DTO.ReplyDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.AdmissionAnswerEntity;
import com.ac.yy.Entity.AdmissionQuestionEntity;
import com.ac.yy.Entity.CourseAnswerEntity;
import com.ac.yy.Entity.CourseBoardEntity;
import com.ac.yy.Repository.AdmissionAnswerRepository;
import com.ac.yy.Repository.AdmissionQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdmissionService {
    @Autowired AdmissionQuestionRepository admissionQuestionRepository;
    @Autowired AdmissionAnswerRepository admissionAnswerRepository;
    // 모든 입학 상담 게시글 불러오기
    public ResponseDTO<?> getAllAdmissionQuestions() {
        List<AdmissionPostDTO> posts = new ArrayList<AdmissionPostDTO>();
        List<AdmissionQuestionEntity> temp = new ArrayList<AdmissionQuestionEntity>();

        try {
            temp = admissionQuestionRepository.findAllByOrderByRegDateDesc();
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

    // 입학상담 게시글 정보 불러오기
    public ResponseDTO<?> getAdmissionQuestionById(int id) {
        AdmissionPostDTO post = new AdmissionPostDTO();
        AdmissionQuestionEntity temp = null;

        try {
            temp = admissionQuestionRepository.findById(id).get();
            post.setQuestion(temp);
            if(admissionAnswerRepository.existsById(id)){
                post.setAnswer(admissionAnswerRepository.findById(id).get());
            }
            else post.setAnswer(null);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Admission Question Load Success!", post);
    }

    // 입학상담 게시글 검색
    public ResponseDTO<?> getAdmissionQuestionsBySearch(String keyword, int type) {
        List<AdmissionPostDTO> posts = new ArrayList<AdmissionPostDTO>();
        List<AdmissionQuestionEntity> temp = new ArrayList<AdmissionQuestionEntity>();

        try {
            // 제목으로 검색
            if(type == 1 || type == 0) {
                temp = admissionQuestionRepository.findByTitleContainingOrderByRegDateDesc(keyword);
                temp.forEach(data -> {
                    AdmissionPostDTO tempAdmissionPostDTO = new AdmissionPostDTO();
                    tempAdmissionPostDTO.setQuestion(data);
                    if(admissionAnswerRepository.existsById(data.getAdmissionQuestionId())) {
                        tempAdmissionPostDTO.setAnswer(admissionAnswerRepository.findById(data.getAdmissionQuestionId()).get());
                    }
                    else {
                        tempAdmissionPostDTO.setAnswer(null);
                    }
                    posts.add(tempAdmissionPostDTO);
                });
            }
            // 작성자로 검색
            if(type == 2 || type == 0) {
                temp = admissionQuestionRepository.findByWriterNameContainingOrderByRegDateDesc(keyword);
                temp.forEach(data -> {
                    AdmissionPostDTO tempAdmissionPostDTO = new AdmissionPostDTO();
                    tempAdmissionPostDTO.setQuestion(data);
                    if(admissionAnswerRepository.existsById(data.getAdmissionQuestionId())) {
                        tempAdmissionPostDTO.setAnswer(admissionAnswerRepository.findById(data.getAdmissionQuestionId()).get());
                    }
                    else {
                        tempAdmissionPostDTO.setAnswer(null);
                    }
                    posts.add(tempAdmissionPostDTO);
                });
            }
            if(type == 0) {
                Collections.sort(posts, (o1, o2) -> o2.getQuestion().getRegDate().compareTo(o1.getQuestion().getRegDate()));
                Set<AdmissionPostDTO> tempPosts = new HashSet<>(posts);
                List<AdmissionPostDTO> result = new ArrayList<>(tempPosts);
                return ResponseDTO.setSuccess("Searched Admission Questions Search Success!", result);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Searched Admission Questions Search Success!", posts);
    }

    public ResponseDTO<?> writeAdmissionQuestion(AdmissionWriteDTO dto) {
        AdmissionQuestionEntity admissionQuestionEntity = new AdmissionQuestionEntity(dto);
        try {
            admissionQuestionRepository.save(admissionQuestionEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Admission Question Write Success!", null);
    }

    public ResponseDTO<?> modAdmissionQuestion(int postId, AdmissionWriteDTO dto) {
        try {
            AdmissionQuestionEntity entity = admissionQuestionRepository.findById(postId).get();

            AdmissionQuestionEntity temp = new AdmissionQuestionEntity(dto);
            temp.setAdmissionQuestionId(postId);
            temp.setRegDate(entity.getRegDate());

            admissionQuestionRepository.save(temp);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("Admission Question Modify Success!", null);
    }

    public ResponseDTO<?> writeReply(int id, ReplyDTO dto) {
        AdmissionAnswerEntity reply = null;
        try {
            if(admissionAnswerRepository.existsById(id)) {
                reply = admissionAnswerRepository.findById(id).get();
                reply.setAnswerContent(dto.getContent());
                admissionAnswerRepository.save(reply);
            }
            else {
                reply = new AdmissionAnswerEntity(dto);
                reply.setAdmissionQuestionId(id);
                admissionAnswerRepository.save(reply);
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Write Reply Success!", null);
    }

    public ResponseDTO<?> deleteAdmissionQuestion(int id) {
        try {
            if(admissionAnswerRepository.existsById(id)) {
                admissionAnswerRepository.deleteById(id);
            }
            admissionQuestionRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Question Success!", null);
    }

    public ResponseDTO<?> deleteAdmissionReply(int id) {
        try {
            admissionAnswerRepository.deleteById(id);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Delete Reply Success!", null);
    }
}
