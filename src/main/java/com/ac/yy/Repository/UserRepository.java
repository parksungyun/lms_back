package com.ac.yy.Repository;

import com.ac.yy.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public boolean existsByUserIdAndUserPw(String userId, String userPw);
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByUid(int uid);

    public boolean existsByUserNameAndUserPhone(String userName, String userPhone);
    Optional<UserEntity> findByUserNameAndUserPhone(String userName, String userPhone);
    public boolean existsByUserIdAndUserPhone(String userId, String userPhone);
    Optional<UserEntity> findByUserIdAndUserPhone(String userId, String userPhone);
    public boolean existsByUserId(String userId);
    List<UserEntity> findByUserNameContaining(String userName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET users.user_pw=?1 WHERE users.user_id=?2", nativeQuery = true)
    int modifyingUserPwByUserId(String userPw, String userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET users.user_pw=?1 WHERE users.uid=?2", nativeQuery = true)
    int modifyingUserPwByUid(String userPw, int uid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE academics SET academics.available=?2 WHERE academics.uid=?1", nativeQuery = true)
    int modifyingAcademicAvailableByUid(int uid, int value);

    @Transactional
    @Modifying
    @Query(value = "UPDATE students SET students.available=?2 WHERE students.uid=?1", nativeQuery = true)
    int modifyingStudentAvailableByUid(int uid, int value);

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET users.user_name=?2, users.user_birth=?3, users.user_addr=?4, users.user_phone=?5, users.user_email=?6 WHERE users.uid=?1", nativeQuery = true)
    int modifyingInfoByUid(int uid, String name, String birth, String addr, String phone, String email);
}
