package com.online.quiz.service.impl;

import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.dto.UserUpdateDTO;
import com.online.quiz.exception.UserNotFoundException;
import com.online.quiz.exception.WrongResetCodeException;
import com.online.quiz.mail.EmailService;
import com.online.quiz.model.User;
import com.online.quiz.projection.UserDetails;
import com.online.quiz.repository.RoleRepository;
import com.online.quiz.repository.UserRepository;
import com.online.quiz.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private RoleRepository roleRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private EmailService emailService;

  @Override
  public void save(User user) {

    user.setRole(roleRepository.findByName("ROLE_user"));
    user.setCreatedAt(new Date());
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
    emailService.sendMessage(user.getEmail(), "Welcome", "Hello and welcome on board! :)");
  }

  @Override
  public void verify(String email) {
    byte[] array = new byte[7]; // length is bounded by 7
    new Random().nextBytes(array);
    String generatedCode = RandomStringUtils.randomAlphabetic(6).toUpperCase();

    User userToRecover = userRepository.findByEmail(email);
    if (userToRecover != null) {
      userToRecover.setResetCode(passwordEncoder.encode(generatedCode));
      userRepository.save(userToRecover);
      emailService.sendMessage(userToRecover.getEmail(), "Reset password", "Code: " + generatedCode);
    } else {
      throw new UserNotFoundException("Such user with given email doesn't exist!");
    }
  }

  @Override
  public void resetPassword(UserResetPasswordDTO userResetPasswordDTO) {
    User userToUpdate = userRepository.findByEmail(userResetPasswordDTO.getEmail());

    if (userToUpdate != null) {
      if (passwordEncoder.matches(userResetPasswordDTO.getResetCode(), userToUpdate.getResetCode())) {
        userToUpdate.setPassword(passwordEncoder.encode(userResetPasswordDTO.getNewPassword()));
        userToUpdate.setResetCode(null);
        userRepository.save(userToUpdate);
      } else {
        throw new WrongResetCodeException("Wrong reset code was provided!");
      }
    } else {
      throw new UserNotFoundException("Such user with given email doesn't exist!");
    }
  }

  @Override
  public void update(UserUpdateDTO userUpdateDTO) {
    User user = userRepository.findByEmail(userUpdateDTO.getEmail());

    if (user == null) {
      throw new UserNotFoundException("Such user doesn't exist!");
    } else {
      user.setFirstName(userUpdateDTO.getFirstName());
      user.setLastName(userUpdateDTO.getLastName());
      user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
    }
  }

  @Override
  public UserDetails getCurrentUser() {
    return userRepository.findUserByEmail(getCurrentUserEmail());
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public String getCurrentUserEmail() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    return auth.getPrincipal().toString();
  }
}
