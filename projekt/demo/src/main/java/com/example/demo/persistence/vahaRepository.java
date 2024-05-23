package com.example.demo.persistence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface vahaRepository extends CrudRepository<vahaEntity,Long> {
}
