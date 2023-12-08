package com.ac.yy.Service;

import com.ac.yy.DTO.ResponseDTO;
import com.ac.yy.Entity.AbsenceCodeEntity;
import com.ac.yy.Entity.DepartmentEntity;
import com.ac.yy.Entity.PositionEntity;
import com.ac.yy.Repository.AbsenceCodeRepository;
import com.ac.yy.Repository.DepartmentRepository;
import com.ac.yy.Repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {
    @Autowired AbsenceCodeRepository absenceCodeRepository;
    @Autowired PositionRepository positionRepository;
    @Autowired DepartmentRepository departmentRepository;

    public ResponseDTO<?> getAllPositions() {
        List<PositionEntity> positions = new ArrayList<PositionEntity>();
        try {
            positions = positionRepository.findAll();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Position Info Load Success!", positions);
    }

    public ResponseDTO<?> getAllDepartment() {
        List<DepartmentEntity> dept = new ArrayList<DepartmentEntity>();
        try {
            dept = departmentRepository.findAll();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Department Info Load Success!", dept);
    }

    public ResponseDTO<?> getAllAbsenceCode() {
        List<AbsenceCodeEntity> code = new ArrayList<AbsenceCodeEntity>();
        try {
            code = absenceCodeRepository.findAll();
        } catch (Exception e) {
            return ResponseDTO.setFailed("Database Error");
        }
        return ResponseDTO.setSuccess("Absence Code Info Load Success!", code);
    }
}
