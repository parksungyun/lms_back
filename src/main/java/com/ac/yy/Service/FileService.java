package com.ac.yy.Service;

import com.ac.yy.DTO.LectureDTO;
import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Entity.LectureEntity;
import com.ac.yy.Entity.SubjectEntity;
import com.ac.yy.Entity.SubjectQuestionEntity;
import com.ac.yy.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {
    @Autowired
    AcademicRepository academicRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    SubjectBoardRepository subjectBoardRepository;

    @Autowired
    CourseBoardRepository courseBoardRepository;

    @Autowired
    CourseQuestionRepository courseQuestionRepository;

    @Autowired
    SubjectQuestionRepository subjectQuestionRepository;

    @Autowired
    HomeworkRepository homeworkRepository;

    @Autowired
    SubmitRepository submitRepository;

    @Autowired
    AttendanceRepository attendanceRepository;

    public ResponseDTO<?> fileUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\images";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            academicRepository.modifyingPhotoByUid(id, filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", filePath);
    }

    public ResponseDTO<?> CoursefileUpload(MultipartFile file, int id) {
        CourseEntity course = null;
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\images";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            course = courseRepository.findById(id).get();
            courseRepository.modifyingPhotoByCourseId(course.getCourseId(), filePath);
            System.out.println(course);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", course);
    }

    public ResponseDTO<?> lectureFileUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\academics";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            lectureRepository.modifyingFileInfoByLectureId(id, file.getOriginalFilename(), filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> videoFileUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\videos";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            lectureRepository.modifyingVideoByLectureId(id, filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> subjectBoardUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\academics";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            subjectBoardRepository.modifyingFileInfoBySubjectBoardId(id, file.getOriginalFilename(), filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> courseBoardUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\academics";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            courseBoardRepository.modifyingFileInfoByCourseBoardId(id, file.getOriginalFilename(), filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> courseQuestionUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\students";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            courseQuestionRepository.modifyingFileInfoByCourseQuestionId(id, file.getOriginalFilename(), filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> subjectQuestionUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\students";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            subjectQuestionRepository.modifyingFileInfoBySubjectQuestionId(id, file.getOriginalFilename(), filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> homeworkUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\academics";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            homeworkRepository.modifyingFileInfoByHomeworkId(id, file.getOriginalFilename(), filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> submitUpload(MultipartFile file, int id) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\students";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            submitRepository.modifyingFileInfoBySubmitId(id, file.getOriginalFilename(), filePath);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }

    public ResponseDTO<?> approveAttendance(MultipartFile file, int id, int code) {
        String extension = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String fileName = UUID.randomUUID().toString().concat(extension);
        String savePath = System.getProperty("user.dir") + "\\academics";
        if (!new File(savePath).exists()) {
            try {
                new File(savePath).mkdir();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        String filePath = savePath + "\\" + fileName;

        try {
            file.transferTo(new File(filePath));
            attendanceRepository.modifyingInfoById(id, file.getOriginalFilename(), filePath, code);
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Upload Success!", null);
    }
}
