package com.djajkoski.budgeteer.controllers;

import com.djajkoski.budgeteer.ApiPrefixController;
import com.djajkoski.budgeteer.models.GenericResponse;
import com.djajkoski.budgeteer.models.SignUpRequest;
import com.djajkoski.budgeteer.models.User;
import com.djajkoski.budgeteer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ApiPrefixController
public class UserController {
  GenericResponse genericResponse = new GenericResponse();

  @Autowired
  UserRepository userRepository;

  @GetMapping("/users/getAll")
  public ResponseEntity<List<User>> getAllUsers() {
    return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAll());
  }

  @GetMapping("/users/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
    User foundUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User was not found!"));
    return ResponseEntity.status(HttpStatus.OK).body(foundUser);
  }

  @PostMapping("/users/register")
  public ResponseEntity<GenericResponse> registerNewUser(@RequestBody SignUpRequest signUpRequest) {
    if ((signUpRequest.getUsername().isEmpty()) || (signUpRequest.getPassword().isEmpty())) {
      genericResponse.setResponseMessage("Bad Credentials.");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    } else if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      genericResponse.setResponseMessage("Username Already Taken!");
      return ResponseEntity.status((HttpStatus.FORBIDDEN)).body(genericResponse);
    } else {
      try {
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        userRepository.save(user);
        genericResponse.setResponseMessage("User Created!");
        return ResponseEntity.status(HttpStatus.CREATED).body(genericResponse);
      } catch (Exception e) {
        genericResponse.setResponseMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(genericResponse);
      }
    }
  }

  @PutMapping("/users/{id}")
  public ResponseEntity<GenericResponse> updateUser(@PathVariable Long id, @RequestBody SignUpRequest user) {
    try {
      User existingUser = userRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
      if (!existingUser.getUsername().equals(user.getUsername())) {
        existingUser.setUsername(user.getUsername());
      }

      if (!existingUser.getPassword().equals(user.getPassword())) {
        existingUser.setPassword(user.getPassword());
      }
      userRepository.save(existingUser);
      genericResponse.setResponseMessage("User successfully updated!");
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericResponse);
    } catch (Exception e) {
      genericResponse.setResponseMessage(e.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    }
  }

  @DeleteMapping("/users/{id}")
  public ResponseEntity<GenericResponse> deleteUserById(@PathVariable long id) {
    try {
      userRepository.deleteById(id);
      genericResponse.setResponseMessage("User was deleted.");
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(genericResponse);
    } catch (Exception exception) {
      genericResponse.setResponseMessage(exception.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(genericResponse);
    }

  }
}
