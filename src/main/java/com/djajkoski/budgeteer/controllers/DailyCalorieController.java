package com.djajkoski.budgeteer.controllers;

import com.djajkoski.budgeteer.ApiPrefixController;
import com.djajkoski.budgeteer.models.DailyCalorie;
import com.djajkoski.budgeteer.models.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ApiPrefixController
@RestController
public class DailyCalorieController {
  @GetMapping("/calories")
  public ResponseEntity<DailyCalorie> getCaloricBalance() {
    DailyCalorie dailyCalorie = new DailyCalorie();
    dailyCalorie.setCurrentConsumedAmount(0);
    return ResponseEntity.status(HttpStatus.OK).body(dailyCalorie);
  }

  @PostMapping("/calories")
  public ResponseEntity<GenericResponse> setCaloricBalance(@RequestBody Integer currentConsumed) {
    GenericResponse genericResponse = new GenericResponse();
    if (currentConsumed == null || currentConsumed < 0) {
      genericResponse.setResponseMessage("Bad Parameters. Consumed calories can NOT be null or smaller than 0.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    } else {
      DailyCalorie dailyCalorie = new DailyCalorie();
      genericResponse.setResponseMessage("Try and lose some weight fat-ass.");
      dailyCalorie.setCurrentConsumedAmount(currentConsumed);
      return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
  }
}
