package com.ac.yy.Repository;

import com.ac.yy.Entity.HomeworkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<HomeworkEntity, Integer> {
}
