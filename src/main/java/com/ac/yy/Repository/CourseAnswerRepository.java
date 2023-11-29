package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseAnswerRepository extends JpaRepository<CourseAnswerEntity, Integer> {
    List<CourseAnswerEntity> findByAcademicIdOrderByAnswerRegDateDesc(int academicId);
}
