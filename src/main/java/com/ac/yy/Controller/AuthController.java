package com.ac.yy.Controller;

import com.ac.yy.DTO.*;
import com.ac.yy.Entity.UserEntity;
import com.ac.yy.Repository.UserRepository;
import com.ac.yy.Service.AuthService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Autowired AuthService authService;
    // 회원가입
    @PostMapping(value = "/register")
    public ResponseDTO<?> register(@RequestBody RegisterDTO requestBody) {
        System.out.println(requestBody.toString());
        ResponseDTO<?> result = authService.register(requestBody);
        return result;
    }

    // 로그인
    @PostMapping(value = "/login")
    public ResponseDTO<LoginResponseDTO> login(@RequestBody LoginDTO requestBody) {
        System.out.println(requestBody.toString());
        ResponseDTO<LoginResponseDTO> result = authService.login(requestBody);
        return result;
    }

    //아이디 찾기
    @PostMapping(value = "/findID")
    public ResponseDTO<?> findID(@RequestBody FindIdDTO requestBody){
        System.out.println(requestBody.toString());
        ResponseDTO<?> result = authService.findID(requestBody);
        return result;
    }

    //비밀번호 찾기
    @PostMapping(value = "/findPW")
    public ResponseDTO<?> findPW(@RequestBody FindPwDTO requestBody){
        System.out.println(requestBody.toString());
        ResponseDTO<?> result = authService.findPW(requestBody);
        return result;
    }

    //아이디 중복체크
    @PostMapping(value = "/checkID")
    public ResponseDTO<?> checkID(@RequestBody CheckIdDTO requestBody){
        System.out.println(requestBody.toString());
        ResponseDTO<?> result = authService.checkID(requestBody);
        return result;
    }

    //비밀번호 변경
    @PostMapping(value = "/changePW")
    public ResponseDTO<?> changePW(@RequestBody LoginDTO requestBody){
        System.out.println(requestBody.toString());
        ResponseDTO<?> result = authService.changePW(requestBody);
        return result;
    }

    //비밀번호 초기화
    @GetMapping("/{id}/resetPW")
    public ResponseDTO<?> resetPW(@PathVariable("id") int id){
        System.out.println(id);
        ResponseDTO<?> result = authService.resetPW(id);
        return result;
    }
}
