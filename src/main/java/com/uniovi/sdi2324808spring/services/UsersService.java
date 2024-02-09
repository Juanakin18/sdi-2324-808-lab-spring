package com.uniovi.sdi2324808spring.services;

import java.util.*;
import javax.annotation.PostConstruct;

import com.uniovi.sdi2324808spring.entities.User;
import com.uniovi.sdi2324808spring.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class UsersService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UsersRepository usersRepository;
    public UsersService(UsersRepository usersRepository, BCryptPasswordEncoder
            bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    @PostConstruct
    public void init() {
    }
    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        usersRepository.findAll().forEach(users::add);
        return users;
    }
    public User getUser(Long id) {
        return usersRepository.findById(id).get();
    }
    public void addUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        usersRepository.save(user);
    }
    public User getUserByDni(String dni) {
        return usersRepository.findByDni(dni);
    }
    public void deleteUser(Long id) {
        usersRepository.deleteById(id);
    }

    }