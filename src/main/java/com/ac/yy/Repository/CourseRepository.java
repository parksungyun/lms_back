package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseEntity;
import com.ac.yy.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
    List<CourseEntity> findByAcademicId(int academicId);

    Optional<CourseEntity> findTopByCourseNameOrderByRegDateDesc(String name);

    @Transactional
    @Modifying
    @Query(value = "UPDATE courses SET courses.course_photo=?2 WHERE courses.course_id=?1", nativeQuery = true)
    int modifyingPhotoByCourseId(int id, String filepath);
}
