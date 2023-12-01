package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Service.FileService;
import com.ac.yy.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Part;
import java.io.File;

@RestController
@RequestMapping(value = "/api/file")
public class FileController {
    @Autowired FileService fileService;

    @PostMapping("/upload/{id}")
    public ResponseDTO<?> fileUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.fileUpload(file, id);
        return result;
    }
}
