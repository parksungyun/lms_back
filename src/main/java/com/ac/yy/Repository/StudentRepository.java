package com.ac.yy.Repository;

import com.ac.yy.Entity.StudentEntity;
import com.ac.yy.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
    public boolean existsByUid(int uid);
    Optional<StudentEntity> findByStudentId(int studentId);
    Optional<StudentEntity> findByUid(int uid);
    List<StudentEntity> findByCourseId(int courseId);
}
