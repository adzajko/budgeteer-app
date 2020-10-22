package com.djajkoski.budgeteer.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Data
@Entity
public class EmailEntity {

  @Id
  private long id;

  private String name;
  private String message;
  private String email;

  private Timestamp timestamp;
}
