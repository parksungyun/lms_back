package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired UserService userService;
    @GetMapping("/{id}")
    public ResponseDTO<?> getUserByUid(@PathVariable("id") int id) {
        ResponseDTO<?> result = userService.getUser(id);
        return result;
    }
}