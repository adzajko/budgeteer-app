package com.djajkoski.budgeteer.controllers;

import com.djajkoski.budgeteer.entities.EmailEntity;
import com.djajkoski.budgeteer.models.EmailRequest;
import com.djajkoski.budgeteer.models.GenericResponse;
import com.djajkoski.budgeteer.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {
  GenericResponse genericResponse = new GenericResponse();

  @Autowired
  private EmailRepository emailRepository;

  @PostMapping("/contact")
  public ResponseEntity<GenericResponse> receiveContactForm(@RequestBody EmailRequest emailRequest) {
    if (emailRequest.getEmail().isEmpty() || emailRequest.getMessage().isEmpty()) {
      genericResponse.setResponseMessage("Missing parameters!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    } else {
      try {
        EmailEntity emailEntity = new EmailEntity();
        emailEntity.setEmail(emailRequest.getEmail());
        emailEntity.setMessage(emailRequest.getMessage());
        emailEntity.setName(emailRequest.getName());
        emailRepository.save(emailEntity);
        genericResponse.setResponseMessage("Contact form received!");
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericResponse);
      } catch (Exception exception) {
        genericResponse.setResponseMessage(exception.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
      }
    }
  }
}
