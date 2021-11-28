package com.online.quiz.security;

public class JwtProperties {
  public static final String SECRET = "QuizAppIon";
  public static final int EXPIRATION_TIME = 259_200_000; // 3 days
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final int REFRESH_BEFORE_EXPIRING_DAYS = 2;
}
