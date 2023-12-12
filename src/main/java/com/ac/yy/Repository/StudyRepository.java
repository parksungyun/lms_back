package com.ac.yy.Repository;

import com.ac.yy.Entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudyRepository extends JpaRepository<StudyEntity, Integer> {
    public boolean existsByStudentIdAndLectureId(int studentId, int lectureId);
    Optional<StudyEntity> findByStudentIdAndLectureId(int studentId, int lectureId);
    List<StudyEntity> findByLectureId(int lectureId);
    List<StudyEntity> findByStudentId(int studentId);
}
