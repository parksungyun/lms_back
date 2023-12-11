package com.ac.yy.Repository;

import com.ac.yy.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
    List<AttendanceEntity> findByStudentIdOrderByAttendDateDesc(int studentId);
    List<AttendanceEntity> findByStudentIdAndAbsenceIdOrderByAttendDateDesc(int studentId, int absenceId);
    public boolean existsByStudentIdAndAttendDate(int studentId, LocalDate attendDate);
    Optional<AttendanceEntity> findByStudentIdAndAttendDate(int studentId, LocalDate attendDate);
}
