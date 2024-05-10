package com.online.quiz.service.impl;

import com.online.quiz.dto.*;
import com.online.quiz.dto.pagination.PaginationDTO;
import com.online.quiz.exception.UserNotFoundException;
import com.online.quiz.exception.WrongResetCodeException;
import com.online.quiz.mail.EmailService;
import com.online.quiz.model.Test;
import com.online.quiz.model.User;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.repository.UserGroupRepository;
import com.online.quiz.repository.UserRepository;
import com.online.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserGroupRepository userGroupRepository;

  private final PasswordEncoder passwordEncoder;

  private final EmailService emailService;

  private final Mapper<User, UserDTO> userToDtoMapper;

  private final Mapper<User, UserShortDetailsDTO> userShortDetailsDTOMapper;

  @Override
  @Transactional
  public void verify(String email) {
    String generatedCode = getRandomString();

    User userToRecover = findUserByEmail(email);

    userToRecover.setResetCode(passwordEncoder.encode(generatedCode));
    userRepository.save(userToRecover);
    emailService.sendMessage(userToRecover.getEmail(), "Reset password", "Code: " + generatedCode);

  }

  @Override
  @Transactional
  public void resetPassword(UserResetPasswordDTO userResetPasswordDTO) {
    User userToUpdate = findUserByEmail(userResetPasswordDTO.getEmail());

    if (passwordEncoder.matches(userResetPasswordDTO.getResetCode(), userToUpdate.getResetCode())) {
      userToUpdate.setPassword(passwordEncoder.encode(userResetPasswordDTO.getNewPassword()));
      userToUpdate.setResetCode(null);
      userRepository.save(userToUpdate);
    } else {
      throw new WrongResetCodeException("Wrong reset code was provided!");
    }
  }

  @Override
  @Transactional
  public void update(UserUpdateDTO userUpdateDTO) {
    User user = findUserByEmail(userUpdateDTO.getEmail());

    user.setFirstName(userUpdateDTO.getFirstName());
    user.setLastName(userUpdateDTO.getLastName());
    user.setPassword(passwordEncoder.encode(userUpdateDTO.getPassword()));
  }

  @Override
  public void shareTestsWithUsers(TestsSharedWIthUsersDTO testsSharedWIthUsersDTO) {
    List<User> users = userRepository.findAllById(testsSharedWIthUsersDTO.getUserIds());

    for (User user : users) {
      for (Long testId : testsSharedWIthUsersDTO.getTestIds()) {
        Test test = new Test();
        test.setId(testId);
        if (!user.getTests().contains(test)) {
          user.addTest(test);
        }
      }
    }
  }

  @Transactional(readOnly = true)
  @Override
  public User findUserByEmail(String email) {
    return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Such user with given email doesn't exist!"));
  }

  @Override
  @Transactional(readOnly = true)
  public User getCurrentUser() {
    return userRepository.findUserByEmail(getCurrentUserEmail());
  }

  @Override
  public UserShortDetailsDTO getUserByEmail(String email) {
    return userShortDetailsDTOMapper.map(userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException("Such user with given email doesn't exist!")));
  }

  @Override
  public List<UserShortDetailsDTO> getAllUsersByUserGroup(Long userGroupId) {
    return userShortDetailsDTOMapper.mapList(userGroupRepository.findById(userGroupId).get().getUsers());
  }

  @Override
  @Transactional(readOnly = true)
  public PaginationDTO<UserDTO> getAll(Pageable pageable) {

    Page<User> usersPage = userRepository.findAllBy(pageable);
    PaginationDTO<UserDTO> paginationDTO = new PaginationDTO<>(userToDtoMapper.mapList(usersPage.getContent()), usersPage);

    return paginationDTO;
  }

  @Override
  @Transactional
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  @Transactional
  @Override
  public String getCurrentUserEmail() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    return ((User) auth.getPrincipal()).getEmail();
  }

  public String getRandomString() {
    byte[] array = new byte[7]; // length is bounded by 7
    new Random().nextBytes(array);
    String generatedCode = RandomStringUtils.randomAlphabetic(6).toUpperCase();
    return generatedCode;
  }
}
