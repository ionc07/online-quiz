package com.online.quiz.service.impl;


import com.online.quiz.dto.UserGroupDTO;
import com.online.quiz.model.User;
import com.online.quiz.model.UserGroup;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.repository.UserGroupRepository;
import com.online.quiz.repository.UserRepository;
import com.online.quiz.service.UserGroupService;
import com.online.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserGroupServiceImpl implements UserGroupService {
  private final UserGroupRepository userGroupRepository;
  private final UserService userService;

  private final Mapper<UserGroup, UserGroupDTO> userGroupDTOMapper;
  private final UserRepository userRepository;

  @Override
  public UserGroup createUserGroup(UserGroup userGroup) {
    User currentUser = userService.getCurrentUser();
    userGroup.setUser(currentUser);
    return userGroupRepository.save(userGroup);
  }

  @Override
  public List<UserGroupDTO> getAllUserGroups() {
    User currentUser = userService.getCurrentUser();
    return userGroupDTOMapper.mapList(userGroupRepository.findAllByUserId(currentUser.getId()));
  }

  @Override
  public void updateUserGroup(UserGroup userGroup) {
    UserGroup currentUserGroup = userGroupRepository.findById(userGroup.getId()).get();
    currentUserGroup.setName(userGroup.getName());
    currentUserGroup.setUsers(userGroup.getUsers());
    userGroupRepository.save(currentUserGroup);
  }

  @Override
  public void deleteUserGroup(Long id) {
    userGroupRepository.deleteById(id);
  }

  @Override
  public void moveUsersToGroup(Long userGroupId, List<Long> userIds) {
    UserGroup userGroup = userGroupRepository.findById(userGroupId).get();
    List<User> users = userRepository.findAllById(userIds);
    userGroup.addUsers(users);

    userGroupRepository.save(userGroup);
  }
}
