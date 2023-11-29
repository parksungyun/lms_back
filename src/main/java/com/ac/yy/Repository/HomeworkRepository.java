package com.ac.yy.Repository;

import com.ac.yy.Entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Integer> {
    List<HomeworkEntity> findBySubjectIdOrderByRegDateDesc(int subjectId);
    List<HomeworkEntity> findByAcademicId(int academicId);
}
