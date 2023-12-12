package com.ac.yy.Repository;

import com.ac.yy.Entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, Integer> {
    public boolean existsByStudentIdAndLectureId(int studentId, int lectureId);
    Optional<StudyEntity> findByStudentIdAndLectureId(int studentId, int lectureId);
    List<StudyEntity> findByLectureId(int lectureId);
    List<StudyEntity> findByStudentId(int studentId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE studies SET studies.progress_time=?2 WHERE studies.study_id=?1", nativeQuery = true)
    int modifyingProgressTimebyStudyId(int id, int time);

    @Transactional
    @Modifying
    @Query(value = "UPDATE studies SET studies.is_study=?2 WHERE studies.study_id=?1", nativeQuery = true)
    int modifyingIsStudybyStudyId(int id, int study);
}
