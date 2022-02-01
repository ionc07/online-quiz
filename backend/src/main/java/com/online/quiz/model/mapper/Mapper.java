package com.online.quiz.model.mapper;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Mapper<E, D> {

  public DateTimeFormatter formatter;

  public Mapper() {
    formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
  }

  public abstract D map(E entity);

  public List<D> mapList(List<E> entities) {
    return entities.stream()
            .map(this::map)
            .collect(Collectors.toList());
  }

  public Set<D> mapList(Set<E> entities) {
    return entities.stream()
            .map(this::map)
            .collect(Collectors.toSet());
  }
}