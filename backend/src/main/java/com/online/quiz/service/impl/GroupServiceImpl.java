package com.online.quiz.service.impl;

import com.online.quiz.dto.GroupDTO;
import com.online.quiz.model.Group;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.repository.GroupRepository;
import com.online.quiz.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
  private final GroupRepository groupRepository;
  private final Mapper<Group, GroupDTO> groupToDTOMapper;

  @Override
  public List<GroupDTO> getAll() {
    return groupToDTOMapper.mapList(groupRepository.findAll());
  }
}
