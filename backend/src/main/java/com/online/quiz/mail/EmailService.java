package com.online.quiz.mail;

public interface EmailService {
  void sendMessage(String to, String subject, String text);
}
