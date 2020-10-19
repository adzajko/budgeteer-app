package com.djajkoski.budgeteer.controllers;

import com.djajkoski.budgeteer.ApiPrefixController;
import com.djajkoski.budgeteer.models.DailyBudget;
import com.djajkoski.budgeteer.models.GenericResponse;
import com.djajkoski.budgeteer.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ApiPrefixController
@RestController
public class DailyBudgetController {

  @Autowired
  BudgetRepository budgetRepository;

  @GetMapping("/budget/goal")
  public ResponseEntity<Iterable<DailyBudget>> getCurrentTarget() {
    Iterable<DailyBudget> dailyBudget = budgetRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(dailyBudget);
  }

  @PutMapping("/budget/goal")
  public ResponseEntity<GenericResponse> setCurrentTarget(@RequestBody DailyBudget dailyBudget) {
    GenericResponse genericResponse = new GenericResponse();
    if ((dailyBudget.getAllocatedBudget() != null) && (dailyBudget.getTargetGoal() != null)) {
      genericResponse.setResponseMessage("OK FAT");
      return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    } else {
      genericResponse.setResponseMessage("LOL NO MANI");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    }
  }
}
