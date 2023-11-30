package com.ac.yy.Repository;

import com.ac.yy.Entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PositionRepository extends JpaRepository<PositionEntity, Integer> {
    Optional<PositionEntity> findByPositionName(String positionName);
}
