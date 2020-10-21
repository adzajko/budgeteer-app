package com.djajkoski.budgeteer.controllers;

import com.djajkoski.budgeteer.ApiPrefixController;
import com.djajkoski.budgeteer.models.DailyBudget;
import com.djajkoski.budgeteer.models.DailyBudgetRequest;
import com.djajkoski.budgeteer.models.GenericResponse;
import com.djajkoski.budgeteer.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@ApiPrefixController
@RestController
public class DailyBudgetController {
  GenericResponse genericResponse = new GenericResponse();

  @Autowired
  BudgetRepository budgetRepository;

  @GetMapping("/budget/getAll")
  public ResponseEntity<Iterable<DailyBudget>> getCurrentTarget() {
    Iterable<DailyBudget> dailyBudget = budgetRepository.findAll();
    return ResponseEntity.status(HttpStatus.OK).body(dailyBudget);
  }

  @GetMapping("/budget/{id}")
  public ResponseEntity<Optional<DailyBudget>> getDailyBudgetById(@PathVariable long id) {
    Optional<DailyBudget> dailyBudget = Optional.ofNullable(budgetRepository.findById(id)
        .orElseThrow(ResourceNotFoundException::new));
    return ResponseEntity.status(HttpStatus.OK).body(dailyBudget);
  }

  @PostMapping("/budget/newEntry")
  public ResponseEntity<GenericResponse> postNewDailyBudgetEntry(@RequestBody DailyBudgetRequest dailyBudget) {
    try {
      DailyBudget dbudget = new DailyBudget();
      dbudget.setAllocatedBudget(dailyBudget.getAllocatedBudget());
      dbudget.setTargetGoal(dailyBudget.getTargetGoal());
      dbudget.setDate(dailyBudget.getDate());
      budgetRepository.save(dbudget);
      genericResponse.setResponseMessage("Entry successfully added.");
      return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
    } catch (Exception e) {
      genericResponse.setResponseMessage(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    }
  }

  @PutMapping("/budget/{id}")
  public ResponseEntity<GenericResponse> updateEntry(@PathVariable long id, @RequestBody DailyBudgetRequest dailyBudget) {
    if ((dailyBudget.getAllocatedBudget() != null) && (dailyBudget.getTargetGoal() != null)) {
      try {
        Optional<DailyBudget> getBudget = Optional.ofNullable(budgetRepository
            .findById(id).orElseThrow(() -> new ResourceNotFoundException("No such entry exists!")));
        DailyBudget budgetToUpdate = getBudget.get();
        budgetToUpdate.setDate(dailyBudget.getDate());
        budgetToUpdate.setTargetGoal(dailyBudget.getTargetGoal());
        budgetToUpdate.setAllocatedBudget(dailyBudget.getAllocatedBudget());
        budgetRepository.save(budgetToUpdate);
        genericResponse.setResponseMessage("Entry updated!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericResponse);
      } catch (Exception e) {
        genericResponse.setResponseMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
      }
    } else {
      genericResponse.setResponseMessage("Missing parameters!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    }
  }

  @DeleteMapping("/budget/{id}")
  public ResponseEntity<GenericResponse> deleteEntryById(@PathVariable long id) {
    try {
      budgetRepository.deleteById(id);
      genericResponse.setResponseMessage("Entry deleted.");
      return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    } catch (Exception e) {
      genericResponse.setResponseMessage(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    }
  }
}
