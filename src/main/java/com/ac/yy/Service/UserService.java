package com.ac.yy.Service;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.UserEntity;
import com.ac.yy.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired UserRepository userRepository;
    public ResponseDTO<?> getUser(int uid) {
        UserEntity user = null;
        try {
            user = userRepository.findByUid(uid).get();
            user.setUserPw("");
        } catch (Exception e) {
            ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Load Success!", user);
    }
}