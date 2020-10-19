package com.djajkoski.budgeteer;

import com.djajkoski.budgeteer.repository.CaloricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = BudgeteerApplication.class)
public class BudgeteerApplication {

  public static void main(String[] args) {
    SpringApplication.run(BudgeteerApplication.class, args);
  }

  @Autowired
  CaloricRepository caloricRepository;
}
