package com.ac.yy.Controller;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/info")
public class InfoController {
    @Autowired InfoService infoService;

    // 부서 정보 조회
    @GetMapping("/dept")
    public ResponseDTO<?> getAllDepartment() {
        ResponseDTO<?> result = infoService.getAllDepartment();
        return result;
    }

    // 포지션 정보 조회
    @GetMapping("/positions")
    public ResponseDTO<?> getAllPositions() {
        ResponseDTO<?> result = infoService.getAllPositions();
        return result;
    }

    // 출결 코드 정보 조회
    @GetMapping("/absence")
    public ResponseDTO<?> getAllAbsenceCode() {
        ResponseDTO<?> result = infoService.getAllAbsenceCode();
        return result;
    }
}
