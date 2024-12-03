package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.model.db.TBUser;
import com.example.demo.repository.IUserRepository;
import com.example.demo.util.IObjectMapper;

@Service
public class UserService {

    @Autowired
    private IUserRepository iUserRepository;

    @Autowired
    private IObjectMapper objectMapper;

    public User getUserById(Integer id) throws Exception {
        TBUser tbUser = iUserRepository.findById(id)
                .orElseThrow(() -> new Exception("Provided id cannot be found!"));

        User user = objectMapper.convToApi(tbUser);

        return user;
    }

    public void deleteUserById(Integer id) throws Exception {
        if (!iUserRepository.existsById(id)) {
            throw new Exception("Provided user with id not found!");
        }

        iUserRepository.deleteById(id);
    }

    public User createUser(User user) {
        TBUser tbUser = objectMapper.convToTb(user);

        tbUser = iUserRepository.save(tbUser);

        User createdUser = objectMapper.convToApi(tbUser);
        return createdUser;
    }

    public List<User> getUsers() {
        List<TBUser> tbUsers = iUserRepository.findAll();

        List<User> users = tbUsers.stream().map(objectMapper::convToApi).toList();
        return users;
    }
}
