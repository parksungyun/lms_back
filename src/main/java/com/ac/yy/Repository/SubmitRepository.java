package com.ac.yy.Repository;

import com.ac.yy.Entity.SubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubmitRepository extends JpaRepository<SubmitEntity, Integer> {
    public boolean existsByStudentIdAndHomeworkId(int studentId, int homeworkId);
    Optional<SubmitEntity> findByStudentIdAndHomeworkId(int studentId, int homeworkId);
    List<SubmitEntity> findByHomeworkId(int homeworkId);
}
