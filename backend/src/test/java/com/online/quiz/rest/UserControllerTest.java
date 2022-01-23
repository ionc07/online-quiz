package com.online.quiz.rest;

import com.online.quiz.projection.UserDetails;
import com.online.quiz.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static io.restassured.RestAssured.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  @MockBean
  private UserService userService;

  @Autowired
  private MockMvc mockMvc;

  private UserDetails user;

  @BeforeEach
  void setUp() {
    user = new UserDetails() {
      @Override
      public Long getId() {
        return 1000L;
      }

      @Override
      public String getEmail() {
        return "ionc@gmail.com";
      }

      @Override
      public String getFirstName() {
        return "Ion";
      }

      @Override
      public String getLastName() {
        return "Ciolacu";
      }

      @Override
      public Date getCreatedAt() {
        return new Date();
      }

      @Override
      public String getRole() {
        return "ADMIN";
      }
    };
  }

  @Test
  @WithMockUser(username = "test", roles = "ADMIN")
  void getCurrentUser_whenInvoked_return200() throws Exception {
    mockMvc.perform(get("/api/v1/current")).andExpect(status().isOk());
  }
}
