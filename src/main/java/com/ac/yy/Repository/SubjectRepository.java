package com.ac.yy.Repository;

import com.ac.yy.Entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    List<SubjectEntity> findByCourseId(int courseId);
    List<SubjectEntity> findByAcademicId(int academicId);
}
