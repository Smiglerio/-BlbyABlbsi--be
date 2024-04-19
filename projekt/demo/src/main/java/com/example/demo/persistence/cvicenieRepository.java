package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface cvicenieRepository extends CrudRepository<cvicenieEntity,Long> {
    /*List<cvicenieEntity> findAllById(Long id);*/
}
