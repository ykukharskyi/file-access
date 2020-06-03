package com.kpi.fileaccess.controllers;

import com.kpi.fileaccess.domain.User;
import com.kpi.fileaccess.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String renderUsersList(Model model) {
        model.addAttribute("users", userService.readAll());
        return "users_list";
    }

    @GetMapping("/create")
    public String renderNewUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user_form";
    }

    @GetMapping("/edit/{id}")
    public String renderEditUserForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.read(id));
        return "user_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/create")
    public String createUser(User user) {
        userService.create(user);
        return "redirect:/users";
    }

    @PostMapping("/edit/{id}")
    public String createUser(@PathVariable Long id, User user) {
        userService.edit(id, user);
        return "redirect:/users";
    }

    @GetMapping("/activate")
    public String renderActivateUserForm() {
        return "user_activate_email";
    }

    @GetMapping("/token/send")
    public String sendToken() {
        userService.generateToken(getCurrentUser());
        return "redirect:/users/activate";
    }

    @GetMapping("/token/activate")
    public String activateToken(@RequestParam String token) {
        userService.activateToken(token, getCurrentUser());
        return "redirect:/";
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        return userService.readByEmail(userEmail);
    }
}
