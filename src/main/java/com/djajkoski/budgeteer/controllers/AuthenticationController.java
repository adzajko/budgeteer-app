package com.djajkoski.budgeteer.controllers;

import com.djajkoski.budgeteer.ApiPrefixController;
import com.djajkoski.budgeteer.models.GenericResponse;
import com.djajkoski.budgeteer.models.LoginForm;
import com.djajkoski.budgeteer.models.User;
import com.djajkoski.budgeteer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@ApiPrefixController
@RestController
public class AuthenticationController {
  @Autowired
  UserRepository userRepository;

  @PostMapping("/login")
  public ResponseEntity<GenericResponse> login(@RequestBody LoginForm loginForm) {
    GenericResponse requestResponse = new GenericResponse();
    if (loginForm.getUserName().isEmpty() || loginForm.getPassCode().isEmpty()) {
      requestResponse.setResponseMessage("Missing credentials!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(requestResponse);
    } else {
      if (userRepository.existsByUsername(loginForm.getUserName())) {
        User foundUser = userRepository.findByUsername(loginForm.getUserName());
        if (foundUser.getUsername().equals(loginForm.getUserName()) && foundUser.getPassword().equals(loginForm.getPassCode())) {
          requestResponse.setResponseMessage("Authenticated.");
          return ResponseEntity.status(HttpStatus.ACCEPTED).body(requestResponse);
        } else {
          requestResponse.setResponseMessage("Wrong username/password.");
          return ResponseEntity.status(HttpStatus.FORBIDDEN).body(requestResponse);
        }
      } else {
        requestResponse.setResponseMessage("No such user exists!");
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(requestResponse);
      }
    }
  }
}
