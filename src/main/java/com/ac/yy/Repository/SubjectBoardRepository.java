package com.ac.yy.Repository;

import com.ac.yy.Entity.SubjectBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectBoardRepository extends JpaRepository<SubjectBoardEntity, Integer> {
}
