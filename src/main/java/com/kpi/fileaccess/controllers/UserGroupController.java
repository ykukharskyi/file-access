package com.kpi.fileaccess.controllers;

import com.kpi.fileaccess.domain.UserGroup;
import com.kpi.fileaccess.services.UserGroupService;
import com.kpi.fileaccess.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user-groups")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private UserService userService;

    @GetMapping
    public String renderUserGroupsList(Model model) {
        model.addAttribute("userGroups", userGroupService.readAll());
        return "user_groups_list";
    }

    @GetMapping("/create")
    public String renderNewUserGroupForm(Model model) {
        model.addAttribute("users", userService.readAll());
        model.addAttribute("userGroup", new UserGroup());
        return "user_group_form";
    }

    @GetMapping("/edit/{id}")
    public String renderEditUserGroupForm(@PathVariable Long id, Model model) {
        model.addAttribute("users", userService.readAll());
        model.addAttribute("userGroup", userGroupService.read(id));
        return "user_group_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserGroup(@PathVariable Long id) {
        userGroupService.delete(id);
        return "redirect:/user-groups";
    }

    @PostMapping("/create")
    public String createUserGroup(UserGroup userGroup) {
        userGroupService.create(userGroup);
        return "redirect:/user-groups";
    }

    @PostMapping("/edit/{id}")
    public String createUserGroup(@PathVariable Long id, UserGroup userGroup) {
        userGroupService.edit(id, userGroup);
        return "redirect:/user-groups";
    }
}
