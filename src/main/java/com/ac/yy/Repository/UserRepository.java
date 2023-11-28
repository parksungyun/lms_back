package com.ac.yy.Repository;

import com.ac.yy.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.beans.Transient;
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

    @Transactional
    @Modifying
    @Query(value = "UPDATE users SET users.user_pw=?1 WHERE users.user_id=?2", nativeQuery = true)
    int modifyingUserPwByUserId(String userPw, String userId);
}
