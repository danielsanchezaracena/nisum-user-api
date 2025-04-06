package com.nisum.userapi.controller;

import com.nisum.userapi.dto.UserRequestDTO;
import com.nisum.userapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@RestController
@RequestMapping(
        value="/api",
        consumes = "application/json",
        produces = "application/json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService=userService;
    }

    @PostMapping("/user")
    public ResponseEntity<?> save(@RequestBody @Valid UserRequestDTO userRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userRequest));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleResponseStatusException(ResponseStatusException ex) {
        return ResponseEntity.status(ex.getStatusCode()).body(Map.of("message", ex.getReason()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error saving user. Check the logs."));
    }

}
