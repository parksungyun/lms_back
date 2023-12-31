package com.ac.yy.Repository;

import com.ac.yy.Entity.AcademicEntity;
import com.ac.yy.Entity.PositionEntity;
import com.ac.yy.Entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AcademicRepository extends JpaRepository<AcademicEntity, Integer> {
    public boolean existsByUid(int uid);
    Optional<AcademicEntity> findByAcademicId(int academicId);
    Optional<AcademicEntity> findByUid(int uid);
    List<AcademicEntity> findByDept(int dept);

    @Transactional
    @Modifying
    @Query(value = "UPDATE academics SET academics.available=?6,academics.remark=?5,academics.position=?4,academics.dept=?3,academics.auth=?2 WHERE academics.uid=?1", nativeQuery = true)
    int modifyingInfoByUid(int uid, int userAuth, int userDept, int posotion, String userRemark, int userAvailable);

    @Transactional
    @Modifying
    @Query(value = "UPDATE academics SET academics.user_photo=?2 WHERE academics.uid=?1", nativeQuery = true)
    int modifyingPhotoByUid(int id, String filepath);
}
