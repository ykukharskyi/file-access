package com.kpi.fileaccess.mocks;

import com.kpi.fileaccess.domain.User;
import com.kpi.fileaccess.domain.UserGroup;
import com.kpi.fileaccess.services.UserGroupService;
import com.kpi.fileaccess.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Configuration
public class UsersMock {

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    @PostConstruct
    public void setUpUsersMocks() {
        User user = userService.create(createAdminUser());
        userGroupService.create(createAdminUserGroup(user));
    }

    private User createAdminUser() {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setEmail("admin");
        user.setPassword("admin");
        return user;
    }

    private UserGroup createAdminUserGroup(User... users) {
        UserGroup userGroup = new UserGroup();
        userGroup.setTitle("Admins");
        userGroup.setUsers(Arrays.asList(users));
        return userGroup;
    }

}
