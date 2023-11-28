package com.ac.yy.Service;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.UserEntity;
import com.ac.yy.Repository.UserRepository;
import com.ac.yy.Security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;

@Service
public class AuthService {
    @Autowired UserRepository userRepository;
    @Autowired TokenProvider tokenProvider;
    public ResponseDTO<?> register(RegisterDTO dto) {
        UserEntity userEntity = new UserEntity(dto);
        try {
            userRepository.save(userEntity);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
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

    public ResponseDTO<?> findID(FindIdDTO dto) {
        String userName = dto.getUserName();
        String userPhone = dto.getUserPhone();

        System.out.println(userName);
        System.out.println(userPhone);

        try {
            boolean existed = userRepository.existsByUserNameAndUserPhone(userName, userPhone);
            if(!existed) {
                return ResponseDTO.setFailed("ID Info is Wrong");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserNameAndUserPhone(userName, userPhone).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Find ID Success", userEntity.getUserId());
    }

    public ResponseDTO<?> findPW(FindPwDTO dto) {
        String userId = dto.getUserId();
        String userPhone = dto.getUserPhone();

        System.out.println(userId);
        System.out.println(userPhone);

        try {
            boolean existed = userRepository.existsByUserIdAndUserPhone(userId, userPhone);
            if(!existed) {
                return ResponseDTO.setFailed("PW Info is Wrong");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByUserIdAndUserPhone(userId, userPhone).get();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Find PW Success", null);
    }

    public ResponseDTO<?> checkID(CheckIdDTO dto) {
        String userId = dto.getUserId();

        System.out.println(userId);

        try {
            boolean existed = userRepository.existsByUserId(userId);
            if(!existed) {
                return ResponseDTO.setFailed("Check ID Info is Wrong");
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
        return ResponseDTO.setSuccess("Find ID Success", userEntity.getUserId());
    }

    public ResponseDTO<?> changePW(LoginDTO dto) {
        String userId = dto.getUserId();
        String userPw = dto.getUserPw();

        System.out.println(userId);
        System.out.println(userPw);

        try {
            boolean existed = userRepository.existsByUserId(userId);
            if(!existed) {
                return ResponseDTO.setFailed("Check ID Info is Wrong");
            }
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error1");
        }

        try {
            int result = userRepository.modifyingUserPwByUserId(userPw,userId);
            if(result == 0) return ResponseDTO.setFailed("Error");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }

        return ResponseDTO.setSuccess("ChangePW Success", null);
    }
}