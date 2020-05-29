package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.User;
import com.kpi.fileaccess.domain.UserGroup;
import com.kpi.fileaccess.repositories.UserGroupRepository;
import com.kpi.fileaccess.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultUserGroupService implements UserGroupService {

    @Autowired
    private UserGroupRepository userGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserGroup create(UserGroup userGroup) {
        populateUsers(userGroup);
        return userGroupRepository.save(userGroup);
    }

    @Override
    public UserGroup edit(Long id, UserGroup newUserGroup) {
        Optional<UserGroup> existingUserGroupOptional = userGroupRepository.findById(id);
        existingUserGroupOptional.ifPresent(userGroup -> {
            userGroup.setTitle(newUserGroup.getTitle());
            populateUsers(newUserGroup, userGroup);
            userGroupRepository.save(userGroup);
        });
        return existingUserGroupOptional.orElse(null);
    }

    @Override
    public UserGroup delete(Long id) {
        Optional<UserGroup> userGroupOptional = userGroupRepository.findById(id);
        userGroupOptional.ifPresent(user -> userGroupRepository.delete(user));
        return userGroupOptional.orElse(null);
    }

    @Override
    public UserGroup read(Long id) {
        return userGroupRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserGroup> readAll() {
        List<UserGroup> result = new ArrayList<>();
        userGroupRepository.findAll().forEach(result::add);
        return result;
    }

    private void populateUsers(UserGroup userGroup) {
        populateUsers(userGroup, userGroup);
    }

    private void populateUsers(UserGroup source, UserGroup target) {
        List<Long> userIds = source.getUsers().stream()
                .map(User::getId)
                .collect(Collectors.toList());
        List<User> users = new ArrayList<>();
        userRepository.findAllById(userIds).forEach(users::add);
        target.setUsers(users);
    }
}
