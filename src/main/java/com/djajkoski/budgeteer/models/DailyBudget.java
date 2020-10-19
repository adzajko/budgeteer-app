package com.djajkoski.budgeteer.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Data
public class DailyBudget {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @NotBlank
  private Integer allocatedBudget;

  @NotBlank
  private Integer targetGoal;

  public Integer getBalance() {
    return allocatedBudget - targetGoal;
  }
}
