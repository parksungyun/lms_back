package com.ac.yy.Repository;

import com.ac.yy.Entity.StudentEntity;
import com.ac.yy.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    public boolean existsByUid(int uid);
    Optional<StudentEntity> findByStudentId(int studentId);
    Optional<StudentEntity> findByUid(int uid);
}
