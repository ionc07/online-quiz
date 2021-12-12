package com.online.quiz.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender emailSender;

  @Value("${spring.mail.username}")
  private String mailFrom;

  @Async
  public void sendMessage(String to, String subject, String text) {

    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(mailFrom);
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    emailSender.send(message);
  }
}