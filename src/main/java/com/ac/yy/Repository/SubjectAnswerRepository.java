package com.ac.yy.Repository;

import com.ac.yy.Entity.SubjectAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectAnswerRepository extends JpaRepository<SubjectAnswerEntity, Integer> {
    List<SubjectAnswerEntity> findByAcademicIdOrderByAnswerRegDateDesc(int academicId);
}
