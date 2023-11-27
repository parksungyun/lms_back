package com.ac.yy.Repository;

import com.ac.yy.Entity.CourseBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseBoardRepository extends JpaRepository<CourseBoardEntity, Integer> {
}
