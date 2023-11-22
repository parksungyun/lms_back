package com.ac.yy.Controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MainController {
    @GetMapping("")
    @CrossOrigin(originPatterns = "http://localhost:3000")
    public String hello() {
        return "Connection Success";
    }
}
