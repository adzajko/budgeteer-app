package com.djajkoski.budgeteer.repository;

import com.djajkoski.budgeteer.models.DailyCalorie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CaloricRepository extends CrudRepository<DailyCalorie, Integer> {
}
