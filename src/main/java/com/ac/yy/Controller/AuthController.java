package com.ac.yy.Controller;

import com.ac.yy.DTO.LoginDTO;
import com.ac.yy.DTO.LoginResponseDTO;
import com.ac.yy.DTO.RegisterDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {
    @Autowired AuthService authService;
    @PostMapping(value = "/register")
    public ResponseDTO<?> register(@RequestBody RegisterDTO requestBody) {
        System.out.println(requestBody.toString());
        ResponseDTO<?> result = authService.register(requestBody);
        return result;
    }

    @PostMapping(value = "/login")
    public ResponseDTO<LoginResponseDTO> login(@RequestBody LoginDTO requestBody) {
        System.out.println(requestBody.toString());
        ResponseDTO<LoginResponseDTO> result = authService.login(requestBody);
        return result;
    }
}
