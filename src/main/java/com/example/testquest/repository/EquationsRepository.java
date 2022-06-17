package com.example.testquest.repository;

import com.example.testquest.entity.EquationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquationsRepository extends JpaRepository<EquationsEntity, Long> {

    Optional<EquationsEntity> findById(long id);

}
