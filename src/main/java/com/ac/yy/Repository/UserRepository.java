package com.ac.yy.Repository;

import com.ac.yy.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    public boolean existsByUserIdAndUserPw(String userId, String userPw);
    Optional<UserEntity> findByUserId(String userId);
    Optional<UserEntity> findByUid(int uid);
}
