package com.ac.yy.Repository;

import com.ac.yy.Entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<LectureEntity, Integer> {
}
