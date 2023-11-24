package com.ac.yy.Repository;

import com.ac.yy.Entity.AcademicEntity;
import com.ac.yy.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AcademicRepository extends JpaRepository<AcademicEntity, Integer> {
    public boolean existsByUid(int uid);
    Optional<AcademicEntity> findByAcademicId(int academicId);
    Optional<AcademicEntity> findByUid(int uid);
}
