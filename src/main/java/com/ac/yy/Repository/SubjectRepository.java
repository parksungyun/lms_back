package com.ac.yy.Repository;

import com.ac.yy.Entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    List<SubjectEntity> findByCourseId(int courseId);
    List<SubjectEntity> findByAcademicId(int academicId);
}
