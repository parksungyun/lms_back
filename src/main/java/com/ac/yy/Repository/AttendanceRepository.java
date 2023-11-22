package com.ac.yy.Repository;

import com.ac.yy.Entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceEntity, Integer> {
}
