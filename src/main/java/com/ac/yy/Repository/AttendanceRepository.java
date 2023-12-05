package com.ac.yy.Repository;

import com.ac.yy.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
    List<AttendanceEntity> findByStudentIdOrderByAttendDateDesc(int studentId);
    List<AttendanceEntity> findByStudentIdAndAbsenceId(int studentId, int absenceId);
}
