package com.ac.yy.Service;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Repository.AcademicRepository;
import com.ac.yy.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URL;
import java.util.UUID;

@Service
public class FileService {
    @Autowired AcademicRepository academicRepository;

    @Autowired CourseRepository courseRepository;

    public ResponseDTO<?> fileUpload(MultipartFile file, int id){
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\images";
        if (!new File(savePath).exists()) {
            try{
                new File(savePath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            academicRepository.modifyingPhotoByUid(id, filePath);
        }
        catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", filePath);
    }

    public ResponseDTO<?> CoursefileUpload(MultipartFile file, String name){
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\images";
        if (!new File(savePath).exists()) {
            try{
                new File(savePath).mkdir();
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            courseRepository.modifyingPhotoByCourseName(name, filePath);
        }
        catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", filePath);
    }
}
