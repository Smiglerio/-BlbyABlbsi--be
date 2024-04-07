package com.example.demo.persistence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface pokrokRepository  extends CrudRepository<pokrokEntity,Long> {
}
