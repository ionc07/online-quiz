package com.online.quiz.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

  private static final DateTimeFormatter MEDIUM_DATE_TIME = DateTimeFormatter.ofPattern("MMM d, yyyy 'at' h:mm");
  private static final DateTimeFormatter SHORT_DATE_TIME = DateTimeFormatter.ofPattern("d/M/yyyy h:mm");
  private static final DateTimeFormatter DEFAULT_DATE_TIME = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  public static String formatMediumDateTime(LocalDateTime dateTime) {
    return dateTime.format(MEDIUM_DATE_TIME);
  }

  public static String formatShortDateTime(LocalDateTime dateTime) {
    return dateTime.format(SHORT_DATE_TIME);
  }

  public static String formatDefaultDateTime(LocalDateTime dateTime) {
    return dateTime.format(DEFAULT_DATE_TIME);
  }

  public static LocalDateTime parseDefaultDateTime(String defaultDateTime) {
    return LocalDateTime.parse(defaultDateTime, DEFAULT_DATE_TIME);
  }

}