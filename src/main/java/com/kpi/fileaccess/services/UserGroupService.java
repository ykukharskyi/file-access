package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.UserGroup;

import java.util.List;

public interface UserGroupService {
    UserGroup create(UserGroup userGroup);
    UserGroup edit(Long id, UserGroup userGroup);
    UserGroup delete(Long id);
    UserGroup read(Long id);
    List<UserGroup> readAll();
}
