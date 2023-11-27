package com.ac.yy.Repository;

import com.ac.yy.Entity.SubmitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmitRepository extends JpaRepository<SubmitEntity, Integer> {
}
