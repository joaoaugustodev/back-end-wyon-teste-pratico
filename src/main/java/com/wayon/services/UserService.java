package com.wayon.services;

import com.wayon.domain.user.User;
import com.wayon.dtos.UserDto;
import com.wayon.exceptions.UserNotFoundException;
import com.wayon.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public User createUser(UserDto userDto) {
        User user = new User(userDto);
        return this.repository.save(user);
    }

    public List<User> allUsers() {
        return this.repository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User getUserByAccount(String account) {
        User user = this.repository.getAllUsersByAccount(account);

        if (user == null) {
            throw new UserNotFoundException();
        }

        return user;
    }

    public void updateUser(User user) {
        this.repository.save(user);
    }
}
