package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User edit(Long id, User user);
    User delete(Long id);
    User read(Long id);
    List<User> readAll();
}
