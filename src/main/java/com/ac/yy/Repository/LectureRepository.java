package com.ac.yy.Repository;

import com.ac.yy.Entity.LectureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface LectureRepository extends JpaRepository<LectureEntity, Integer> {
    List<LectureEntity> findBySubjectIdOrderByRegDateDesc(int subjectId);
    List<LectureEntity> findBySubjectId(int subjectId);
    List<LectureEntity> findByAcademicId(int academicId);
    List<LectureEntity> findByTitleContainingOrderByRegDateDesc(String title);
    List<LectureEntity> findByContentContainingOrderByRegDateDesc(String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE lectures SET lectures.hits = lectures.hits + 1 WHERE lectures.lecture_id=?1", nativeQuery = true)
    int modifyingHitsByLectureId(int lectureId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE lectures SET lectures.file_url=?3, lectures.file_name=?2 WHERE lectures.lecture_id=?1", nativeQuery = true)
    int modifyingFileInfoByLecturetId(int lectureId, String fileName, String fileUrl);

    @Transactional
    @Modifying
    @Query(value = "UPDATE lectures SET lectures.video_url=?2 WHERE lectures.lecture_id=?1", nativeQuery = true)
    int modifyingVideoByLecturetId(int lectureId, String videoUrl);
}
