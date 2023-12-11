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
    int modifyingPhotoByCourseId(int courseId, String filepath);

    @Transactional
    @Modifying
    @Query(value = "UPDATE courses SET courses.course_info=?10, courses.recruit_end=?9, courses.recruit_start=?8, courses.end_date=?7, courses.start_date=?6 ,courses.capacity=?5, courses.subject_no=?4, courses.course_name=?3, courses.academic_id=?2 WHERE courses.course_id=?1", nativeQuery = true)
    int modifyingInfoByCourseId(int courseId, int academicId, String name, int subjectNo, int capacity, String startDate, String endDate, String recruitStart, String recruitEnd, String courseInfo);
}
