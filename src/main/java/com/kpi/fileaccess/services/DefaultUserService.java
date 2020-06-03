package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.User;
import com.kpi.fileaccess.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DefaultUserService implements UserService {
    private static final String INVALID_USERNAME_MESSAGE = "Invalid username";

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User edit(Long id, User newUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        existingUserOptional.ifPresent(user -> {
            user.setEmail(newUser.getEmail());
            user.setFirstName(newUser.getFirstName());
            user.setLastName(newUser.getLastName());
            user.setPassword(newUser.getPassword());
            user.setToken(newUser.getToken());
            user.setActivated(newUser.isActivated());
            userRepository.save(user);
        });
        return existingUserOptional.orElse(null);
    }

    @Override
    public User delete(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(user -> userRepository.delete(user));
        return userOptional.orElse(null);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User readByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User generateToken(User user) {
        String token = UUID.randomUUID().toString();
        System.out.println("Generated token: " + token);
        user.setToken(token);
        return edit(user.getId(), user);
    }

    @Override
    public User activateToken(String token, User currentUser) {
        User user = read(currentUser.getId());
        if (token.equals(user.getToken())) {
            user.setToken(null);
            user.setActivated(true);
        }
        return edit(user.getId(), user);
    }

    @Override
    public List<User> readAll() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(INVALID_USERNAME_MESSAGE));
    }
}
