package com.online.quiz.controller;

import com.online.quiz.dto.AuthDTO;
import com.online.quiz.dto.CreateUserDTO;
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
import java.util.Map;

@RestController
@RequestMapping(path = "auth/")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("sign-in")
  public ResponseEntity<?> login(@Valid @RequestBody AuthDTO authDTO) {
    Map<String, String> tokenAndRole = authService.authenticate(authDTO);

    return ResponseEntity.ok()
            .header(HttpHeaders.AUTHORIZATION, tokenAndRole.remove("token")).body(tokenAndRole);
  }

  @PostMapping("sign-up")
  public ResponseEntity<?> register(@Valid @RequestBody CreateUserDTO request) {
    authService.register(request);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
