package com.djajkoski.budgeteer.models;

import lombok.Data;

import java.util.Date;

@Data
public class DailyBudgetRequest {
  private Integer targetGoal;
  private Integer allocatedBudget;
  private Date date;
}
