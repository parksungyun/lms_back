package com.ac.yy.Repository;

import com.ac.yy.Entity.SubjectQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface SubjectQuestionRepository  extends JpaRepository<SubjectQuestionEntity, Integer> {
    List<SubjectQuestionEntity> findBySubjectIdOrderByRegDateDesc(int subjectId);
    List<SubjectQuestionEntity> findByStudentIdOrderByRegDateDesc(int studentId);
    List<SubjectQuestionEntity> findByTitleContainingOrderByRegDateDesc(String title);
    List<SubjectQuestionEntity> findByContentContainingOrderByRegDateDesc(String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE subject_questions SET subject_questions.hits = subject_questions.hits + 1 WHERE subject_questions.subject_question_id=?1", nativeQuery = true)
    int modifyingHitsBySubjectQuestionId(int subjectQuestionId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE subject_questions SET subject_questions.file_url=?3, subject_questions.file_name=?2 WHERE subject_questions.subject_question_id=?1", nativeQuery = true)
    int modifyingFileInfoBySubjectQuestionId(int subjectQuestionId, String filename, String fileurl);
}
