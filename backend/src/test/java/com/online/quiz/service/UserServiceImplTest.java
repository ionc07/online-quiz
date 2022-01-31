package com.online.quiz.service;

import com.online.quiz.dto.UserDTO;
import com.online.quiz.dto.UserResetPasswordDTO;
import com.online.quiz.mail.EmailService;
import com.online.quiz.model.User;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.repository.UserRepository;
import com.online.quiz.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
  @Mock
  private UserRepository userRepository;

  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private EmailService emailService;

  private UserServiceImpl userService;

  private UserResetPasswordDTO userResetPasswordDTO;

  private Mapper<User, UserDTO> userUserDTOMapper;

  @BeforeEach
  public void setUp() {
    userService = new UserServiceImpl(userRepository, passwordEncoder, emailService, userUserDTOMapper);

    userResetPasswordDTO = UserResetPasswordDTO.builder()
            .email("ion07@gmail.com")
            .resetCode("ABCDEF")
            .newPassword("myNewPassword").build();
  }

  @Test
  void verify_whenUserNotFoundByEmail_throwUserNotFoundExceptionWithMessage() {
    String email = "ionc@gmail.com";
    when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
    Exception exception = assertThrows(RuntimeException.class, () -> {
      userService.verify(email);
    });

    String expectedMessage = "Such user with given email doesn't exist!";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage.contains(expectedMessage));
  }

  @Test
  void resetPassword_whenUserNotFoundByEmail_throwUserNotFoundExceptionWithMessage() {
    when(userRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());
    Exception exception = assertThrows(RuntimeException.class, () -> {
      userService.resetPassword(userResetPasswordDTO);
    });

    String expectedMessage = "Such user with given email doesn't exist!";
    String actualMessage = exception.getMessage();

    assertThat(actualMessage.contains(expectedMessage));
  }

  @Test
  void getRandomString_whenGeneratedRandomString_returnSixCharStringInUpperCase() {
    String randomCode = userService.getRandomString();
    String randomCodeInUpperCase = randomCode.toUpperCase();

    assertThat(randomCode.length() == 6);
    assertThat(randomCode.equals(randomCodeInUpperCase));
  }
}
