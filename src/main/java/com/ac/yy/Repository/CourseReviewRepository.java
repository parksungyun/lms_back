package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReviewEntity, Integer> {
    public boolean existsByStudentIdAndSubjectId(int studentId, int subjectId);
    List<CourseReviewEntity> findBySubjectIdOrderByReviewScoreDesc(int subjectId);
    List<CourseReviewEntity> findByStudentId(int studentId);
    Optional<CourseReviewEntity> findByStudentIdAndSubjectId(int studentId, int subjectId);
}
