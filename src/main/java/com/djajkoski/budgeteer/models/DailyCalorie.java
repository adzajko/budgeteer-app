package com.djajkoski.budgeteer.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Entity
public class DailyCalorie {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private Integer dailyCaloricIntake = 1600;

  @NotBlank
  private Integer currentConsumedAmount;

  @NotBlank
  private Date date;


  public Integer getCaloricBalance() {
    return dailyCaloricIntake - currentConsumedAmount;
  }
}
