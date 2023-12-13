package com.ac.yy.Repository;

import com.ac.yy.Entity.SubjectBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubjectBoardRepository extends JpaRepository<SubjectBoardEntity, Integer> {
    List<SubjectBoardEntity> findBySubjectIdOrderByRegDateDesc(int subjectId);
    List<SubjectBoardEntity> findByAcademicId(int academicId);
    List<SubjectBoardEntity> findByTitleContainingOrderByRegDateDesc(String title);
    List<SubjectBoardEntity> findByContentContainingOrderByRegDateDesc(String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE subject_board SET subject_board.hits = subject_board.hits + 1 WHERE subject_board.subject_board_id=?1", nativeQuery = true)
    int modifyingHitsBySubjectBoardId(int subjectBoardId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE subject_board SET subject_board.file_url=?3, subject_board.file_name=?2 WHERE subject_board.subject_board_id=?1", nativeQuery = true)
    int modifyingFileInfoBySubjectBoardId(int subjectBoardId, String filename, String fileurl);
}
