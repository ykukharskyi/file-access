package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User create(User user);
    User edit(Long id, User user);
    User delete(Long id);
    User read(Long id);
    User readByEmail(String email);
    User generateToken(User user);
    User activateToken(String token, User user);
    List<User> readAll();
}
