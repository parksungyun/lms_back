package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CourseBoardRepository extends JpaRepository<CourseBoardEntity, Integer> {
    List<CourseBoardEntity> findByCourseIdOrderByRegDateDesc(int courseId);
    List<CourseBoardEntity> findByAcademicId(int academicId);
    List<CourseBoardEntity> findByTitleContainingOrderByRegDateDesc(String title);
    List<CourseBoardEntity> findByContentContainingOrderByRegDateDesc(String content);

    @Transactional
    @Modifying
    @Query(value = "UPDATE course_board SET course_board.hits = course_board.hits + 1 WHERE course_board.course_board_id=?1", nativeQuery = true)
    int modifyingHitsByCourseBoardId(int courseBoardId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE course_board SET course_board.file_url=?3, course_board.file_name=?2 WHERE course_board.course_board_id=?1", nativeQuery = true)
    int modifyingFileInfoByCourseBoardId(int courseBoardId, String filename, String fileurl);
}
