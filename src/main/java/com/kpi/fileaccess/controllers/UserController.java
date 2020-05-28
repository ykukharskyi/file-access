package com.kpi.fileaccess.controllers;

import com.kpi.fileaccess.domain.User;
import com.kpi.fileaccess.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String renderUsersList(Model model) {
        model.addAttribute("users", userService.readAll());
        return "users_list";
    }

    @GetMapping("/user")
    public String renderNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @GetMapping("/user/{id}")
    public String renderEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.read(id));
        return "user_form";
    }

    @GetMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @PostMapping("/user-edit/{id}")
    public String createUser(@PathVariable Long id, User user) {
        userService.edit(id, user);
        return "redirect:/users";
    }
}
