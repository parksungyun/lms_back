package com.ac.yy.Entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "attendances")
public class AttendanceEntity {
    @Id
    @Column(name = "attendance_id")
    private int attendanceId;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "attend_date")
    private LocalDate attendDate;

    @Column(name = "attend_time")
    private LocalDateTime attendTime;

    @Column(name = "leave_time")
    private LocalDateTime leaveTime;

    @Column(name = "absence_id")
    private int absenceId;

    @Column(name = "absence_file_name")
    private String absenceFileName;

    @Column(name = "absence_file_url")
    private String absenceFileUrl;
}
