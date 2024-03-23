package com.example.demo.persistence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface uzivatelRepository extends CrudRepository<uzivatelEntity,Long>{
}
