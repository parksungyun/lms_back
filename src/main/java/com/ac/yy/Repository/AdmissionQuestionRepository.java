package com.ac.yy.Repository;

import com.ac.yy.Entity.AdmissionQuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdmissionQuestionRepository extends JpaRepository<AdmissionQuestionEntity, Integer> {
    List<AdmissionQuestionEntity> findAllByOrderByRegDateDesc();
    List<AdmissionQuestionEntity> findByTitleContainingOrderByRegDateDesc(String title);
    List<AdmissionQuestionEntity> findByWriterNameContainingOrderByRegDateDesc(String writerName);
}
