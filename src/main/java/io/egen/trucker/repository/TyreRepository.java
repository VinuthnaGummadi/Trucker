package io.egen.trucker.repository;

import io.egen.trucker.entity.Tyre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyreRepository extends CrudRepository<Tyre, Integer> {
}
