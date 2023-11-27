package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseReviewRepository extends JpaRepository<CourseReviewEntity, Integer> {
}
