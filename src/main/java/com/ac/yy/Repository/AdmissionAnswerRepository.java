package com.ac.yy.Repository;

import com.ac.yy.Entity.AdmissionAnswerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionAnswerRepository extends JpaRepository<AdmissionAnswerEntity, Integer> {
}
