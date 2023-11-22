package com.ac.yy.Service;

import com.ac.yy.DTO.LoginDTO;
import com.ac.yy.DTO.LoginResponseDTO;
import com.ac.yy.DTO.RegisterDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.UserEntity;
import com.ac.yy.Repository.UserRepository;
import com.ac.yy.Security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired UserRepository userRepository;
    @Autowired TokenProvider tokenProvider;
    public ResponseDTO<?> register(RegisterDTO dto) {
        UserEntity userEntity = new UserEntity(dto);
        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Register Success!", null);
    }
    public ResponseDTO<LoginResponseDTO> login(LoginDTO dto) {
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();

        System.out.println(userId);
        System.out.println(userPw);

        try {
            boolean existed = userRepository.existsByUserIdAndUserPw(userId, userPw);
            if(!existed) {
                return ResponseDTO.setFailed("Login Info is Wrong");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserId(userId).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }

        userEntity.setUserPw("");

        String token = tokenProvider.createJwt(userEntity.getUid());
        int expireTime = 3600000;

        LoginResponseDTO loginResponseDTO = new LoginResponseDTO(token, expireTime, userEntity);
        System.out.println(loginResponseDTO);
        return ResponseDTO.setSuccess("Login Success", loginResponseDTO);
    }
}