package com.online.quiz.dto.pagination;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.util.List;

@Getter
@Setter
public class PaginationDTO<T> {

  private List<T> content;

  private Integer totalPages;
  private Long totalElements;
  private Integer numberOfElements;
  private Integer pageNumber;
  private Integer size;

  private Sort sort;

  public PaginationDTO(List<T> elements, Page page) {
    this.content = elements;
    this.pageNumber = page.getNumber();
    this.size = page.getSize();
    this.sort = page.getSort();
    this.numberOfElements = page.getNumberOfElements();
    this.totalElements = page.getTotalElements();
    this.totalPages = page.getTotalPages();
  }
}
