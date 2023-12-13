package com.ac.yy.Repository;

import com.ac.yy.Entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Integer> {
    List<HomeworkEntity> findBySubjectIdOrderByStartDateDesc(int subjectId);
    List<HomeworkEntity> findByAcademicId(int academicId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE homeworks SET homeworks.file_url=?3, homeworks.file_name=?2 WHERE homeworks.homework_id=?1", nativeQuery = true)
    int modifyingFileInfoByHomeworkId(int lectureId, String fileName, String fileUrl);
}
