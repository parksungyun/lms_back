package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseQuestionRepository extends JpaRepository<CourseQuestionEntity, Integer> {
    List<CourseQuestionEntity> findByCourseIdOrderByRegDateDesc(int courseId);
    List<CourseQuestionEntity> findByStudentIdOrderByRegDateDesc(int studentId);
    List<CourseQuestionEntity> findByTitleContainingOrderByRegDateDesc(String title);
    List<CourseQuestionEntity> findByContentContainingOrderByRegDateDesc(String content);
}
