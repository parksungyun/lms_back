package com.ac.yy.Repository;

import com.ac.yy.Entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {
    List<SubjectEntity> findByCourseId(int courseId);
    List<SubjectEntity> findByAcademicId(int academicId);

    public boolean existsByCourseId(int courseId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE subjects SET subjects.subject_name=?2, subjects.academic_id=?3 WHERE subjects.subject_id=?1", nativeQuery = true)
    int modifyingSubjectBySubjectId(int subjectId, String name, int academicId);

}
