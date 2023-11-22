package com.ac.yy.Repository;

import com.ac.yy.Entity.AcademicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcademicRepository extends JpaRepository<AcademicEntity, Integer> {
}
