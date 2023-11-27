package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    List<CourseEntity> findByAcademicId(int academicId);
}
