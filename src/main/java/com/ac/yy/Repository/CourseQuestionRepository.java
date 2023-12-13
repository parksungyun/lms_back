package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseQuestionRepository extends JpaRepository<CourseQuestionEntity, Integer> {
    List<CourseQuestionEntity> findByCourseIdOrderByRegDateDesc(int courseId);
    List<CourseQuestionEntity> findByStudentIdOrderByRegDateDesc(int studentId);
    List<CourseQuestionEntity> findByTitleContainingOrderByRegDateDesc(String title);
    List<CourseQuestionEntity> findByContentContainingOrderByRegDateDesc(String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE course_questions SET course_questions.file_url=?3, course_questions.file_name=?2 WHERE course_questions.course_question_id=?1", nativeQuery = true)
    int modifyingFileInfoByCourseQuestionId(int courseQuestionId, String filename, String fileurl);
}
