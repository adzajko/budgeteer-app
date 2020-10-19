package com.djajkoski.budgeteer.repository;

import com.djajkoski.budgeteer.models.DailyBudget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface BudgetRepository extends CrudRepository<DailyBudget, Integer> {
}
