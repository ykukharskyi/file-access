package com.kpi.fileaccess.services;

import com.kpi.fileaccess.domain.User;
import com.kpi.fileaccess.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {

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
    public List<User> readAll() {
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(result::add);
        return result;
    }
}
