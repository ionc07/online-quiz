package com.online.quiz.controller;

import com.online.quiz.dto.AuthRequest;
import com.online.quiz.dto.CreateUserRequest;
import com.online.quiz.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "auth/")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("sign-in")
  public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
    String token = authService.authenticate(authRequest);

    return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, token).build();
  }

  @PostMapping("sign-up")
  public ResponseEntity<?> register(@Valid @RequestBody CreateUserRequest request) {
    authService.register(request);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
