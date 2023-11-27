package com.ac.yy.Repository;

import com.ac.yy.Entity.AcademicEntity;
import com.ac.yy.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicRepository extends JpaRepository<AcademicEntity, Integer> {
    public boolean existsByUid(int uid);
    Optional<AcademicEntity> findByAcademicId(int academicId);
    Optional<AcademicEntity> findByUid(int uid);
    List<AcademicEntity> findByDept(int dept);
}
