package com.online.quiz.service;

import com.online.quiz.dto.TestGroupDTO;
import com.online.quiz.model.TestGroup;

import java.util.List;

public interface TestGroupService {
  TestGroup createTestGroup(TestGroup testGroup);

  List<TestGroupDTO> getAllTestGroups();

  void deleteTestGroup(Long id);

  void updateTestGroup(TestGroup testGroup);

  void moveTestsToGroup(Long testGroupId, List<Long> testIds);
}
