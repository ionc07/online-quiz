package com.online.quiz.service.impl;


import com.online.quiz.dto.TestGroupDTO;
import com.online.quiz.model.Test;
import com.online.quiz.model.TestGroup;
import com.online.quiz.model.User;
import com.online.quiz.model.mapper.Mapper;
import com.online.quiz.repository.TestGroupRepository;
import com.online.quiz.repository.TestRepository;
import com.online.quiz.service.TestGroupService;
import com.online.quiz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestGroupServiceImpl implements TestGroupService {
  private final TestGroupRepository testGroupRepository;
  private final UserService userService;
  private final TestRepository testRepository;

  private final Mapper<TestGroup, TestGroupDTO> testGroupDTOMapper;

  @Override
  public TestGroup createTestGroup(TestGroup testGroup) {
    User currentUser = userService.getCurrentUser();
    testGroup.setUser(currentUser);
    return testGroupRepository.save(testGroup);
  }

  @Override
  public List<TestGroupDTO> getAllTestGroups() {
    User currentUser = userService.getCurrentUser();
    return testGroupDTOMapper.mapList(testGroupRepository.findAllByUserId(currentUser.getId()));
  }

  @Override
  public void deleteTestGroup(Long id) {
    testGroupRepository.deleteById(id);
  }

  @Override
  public void updateTestGroup(TestGroup testGroup) {
    TestGroup currentTestGroup = testGroupRepository.findById(testGroup.getId()).get();
    currentTestGroup.setName(testGroup.getName());
    currentTestGroup.setTests(testGroup.getTests());
    testGroupRepository.save(currentTestGroup);
  }

  @Override
  public void moveTestsToGroup(Long testGroupId, List<Long> testIds) {
    TestGroup testGroup = testGroupRepository.findById(testGroupId).get();
    List<Test> tests = testRepository.findAllById(testIds);
    tests.forEach(test -> {
      test.setTestGroup(testGroup);
      testRepository.save(test);
    });

  }
}
