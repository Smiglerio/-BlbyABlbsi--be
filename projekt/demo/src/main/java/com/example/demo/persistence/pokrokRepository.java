package com.example.demo.persistence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface pokrokRepository  extends CrudRepository<pokrokEntity,Long> {
    Optional<pokrokEntity> findByUzivatelEntityAndCvicenieEntityAndTreningovePlanyEntity(uzivatelEntity user, cvicenieEntity cvicenie, treningovePlanyEntity plan);

    List<pokrokEntity> findByTreningovePlanyEntity(treningovePlanyEntity plan);

    Iterable<? extends pokrokEntity> findByCvicenieEntity(cvicenieEntity cvicenie);

    Iterable<? extends pokrokEntity> findByTreningovePlanyEntityAndUzivatelEntity(treningovePlanyEntity plan, uzivatelEntity user);
}
