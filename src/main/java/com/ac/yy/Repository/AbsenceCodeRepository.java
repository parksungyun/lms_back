package com.ac.yy.Repository;

import com.ac.yy.Entity.AbsenceCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceCodeRepository extends JpaRepository<AbsenceCodeEntity, Integer> {
}
