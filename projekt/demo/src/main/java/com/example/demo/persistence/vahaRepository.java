package com.example.demo.persistence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface vahaRepository extends CrudRepository<vahaEntity, Long> {
    List<vahaEntity> findByuserId(uzivatelEntity userId);
}