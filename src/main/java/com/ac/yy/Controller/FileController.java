package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Service.FileService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/file")
public class FileController {
    @Autowired
    FileService fileService;

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
}