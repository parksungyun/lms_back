package com.ac.yy.Repository;

import com.ac.yy.Entity.AdmissionQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionQuestionRepository extends JpaRepository<AdmissionQuestionEntity, Integer> {
}
