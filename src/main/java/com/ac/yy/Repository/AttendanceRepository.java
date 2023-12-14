package com.ac.yy.Repository;

import com.ac.yy.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
    List<AttendanceEntity> findByStudentIdOrderByAttendDateDesc(int studentId);
    List<AttendanceEntity> findByStudentIdAndAbsenceIdOrderByAttendDateDesc(int studentId, int absenceId);
    public boolean existsByStudentIdAndAttendDate(int studentId, LocalDate attendDate);
    Optional<AttendanceEntity> findByStudentIdAndAttendDate(int studentId, LocalDate attendDate);

    @Transactional
    @Modifying
    @Query(value = "UPDATE attendances SET attendances.absence_id=?4, attendances.absence_file_url=?3, attendances.absence_file_name=?2 WHERE attendances.attendance_id=?1", nativeQuery = true)
    int modifyingInfoById(int Id, String fileName, String fileUrl, int code);
}
