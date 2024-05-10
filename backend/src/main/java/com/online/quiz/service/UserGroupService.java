package com.online.quiz.service;

import com.online.quiz.dto.UserGroupDTO;
import com.online.quiz.model.UserGroup;

import java.util.List;

public interface UserGroupService {
  UserGroup createUserGroup(UserGroup userGroup);

  List<UserGroupDTO> getAllUserGroups();

  void updateUserGroup(UserGroup userGroup);

  void deleteUserGroup(Long id);

  void moveUsersToGroup(Long userGroupId, List<Long> userIds);
}
