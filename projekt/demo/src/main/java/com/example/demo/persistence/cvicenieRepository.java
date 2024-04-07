package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface cvicenieRepository extends CrudRepository<cvicenieEntity,Long> {
}
