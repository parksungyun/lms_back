package com.ac.yy.Repository;

import com.ac.yy.Entity.StudyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRepository extends JpaRepository<StudyEntity, Integer> {
}
