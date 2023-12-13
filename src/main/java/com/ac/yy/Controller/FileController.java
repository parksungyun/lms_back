package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.LectureEntity;
import com.ac.yy.Repository.LectureRepository;
import com.ac.yy.Service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/file")
public class FileController {
    @Autowired
    FileService fileService;

    @Autowired
    LectureRepository lectureRepository;

    @PostMapping("/upload/{id}")
    public ResponseDTO<?> fileUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.fileUpload(file, id);
        return result;
    }

    @PostMapping("/upload/course/{id}")
    public ResponseDTO<?> courseFileUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.CoursefileUpload(file, id);
        return result;
    }

    @PostMapping("/upload/lecture/{id}")
    public ResponseDTO<?> lectureFileUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.lectureFileUpload(file, id);
        return result;
    }

    @PostMapping("/upload/video/{id}")
    public ResponseDTO<?> videoFileUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.videoFileUpload(file, id);
        return result;
    }

    @GetMapping(value = "/video/{name}")
    public ResponseEntity<ResourceRegion> getVideo(@RequestHeader HttpHeaders headers, @PathVariable String name) throws IOException {

        UrlResource video = new UrlResource("file:///C:/Users/DW/Documents/lms_back/videos/"+name);
        ResourceRegion resourceRegion;

        final long chunkSize = 1000000L;
        long contentLength = video.contentLength();

        Optional<HttpRange> optional = headers.getRange().stream().findFirst();
        HttpRange httpRange;
        if (optional.isPresent()) {
            httpRange = optional.get();
            long start = httpRange.getRangeStart(contentLength);
            long end = httpRange.getRangeEnd(contentLength);
            long rangeLength = Long.min(chunkSize, end - start + 1);
            resourceRegion = new ResourceRegion(video, start, rangeLength);
        } else {
            long rangeLength = Long.min(chunkSize, contentLength);
            resourceRegion = new ResourceRegion(video, 0, rangeLength);
        }

        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
                .contentType(MediaTypeFactory.getMediaType(video).orElse(MediaType.APPLICATION_OCTET_STREAM))
                .body(resourceRegion);
    }

    @GetMapping("/download/academic/{name}")
    public ResponseEntity<Resource> downloadAcademicAttach(@PathVariable String name) throws MalformedURLException {

        UrlResource urlResource = new UrlResource("file:///C:/Users/DW/Documents/lms_back/academics/" + name);

        String encodeUploadFileName = UriUtils.encode(name, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

    @GetMapping("/download/student/{name}")
    public ResponseEntity<Resource> downloadStudentAttach(@PathVariable String name) throws MalformedURLException {

        UrlResource urlResource = new UrlResource("file:///C:/Users/DW/Documents/lms_back/students/" + name);

        String encodeUploadFileName = UriUtils.encode(name, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodeUploadFileName + "\"";
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }

    @PostMapping("/upload/subject/board/{id}")
    public ResponseDTO<?> subjectBoardUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.subjectBoardUpload(file, id);
        return result;
    }

    @PostMapping("/upload/course/board/{id}")
    public ResponseDTO<?> courseBoardUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.courseBoardUpload(file, id);
        return result;
    }

    @PostMapping("/upload/course/question/{id}")
    public ResponseDTO<?> courseQuestionUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.courseQuestionUpload(file, id);
        return result;
    }

    @PostMapping("/upload/subject/question/{id}")
    public ResponseDTO<?> subjectQuestionUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.subjectQuestionUpload(file, id);
        return result;
    }

    @PostMapping("/upload/homework/{id}")
    public ResponseDTO<?> homeworkUpload(@RequestParam(value = "file", required = false) MultipartFile file, @PathVariable("id") int id) {
        ResponseDTO<?> result = fileService.homeworkUpload(file, id);
        return result;
    }
}