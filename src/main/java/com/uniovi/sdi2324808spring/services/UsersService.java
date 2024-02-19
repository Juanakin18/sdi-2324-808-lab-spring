package com.uniovi.sdi2324808spring.services;

import java.util.*;
import javax.annotation.PostConstruct;

import com.uniovi.sdi2324808spring.entities.User;
import com.uniovi.sdi2324808spring.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<User> getUsers(Pageable pageable) {
        Page<User> users = usersRepository.findAll(pageable);
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

    public void editUser(Long id, User user) {
        User lastUser = usersRepository.findById(id).get();
        lastUser.setDni(user.getDni());
        lastUser.setName(user.getName());
        lastUser.setLastName(user.getLastName());
        usersRepository.save(lastUser);

    }

    public Page<User> findUsersContainingNameAndSurname(Pageable pageable, String searchTextNombre, String searchTextApellido) {
        searchTextNombre = "%"+searchTextNombre+"%";
        searchTextApellido = "%"+searchTextApellido+"%";

        return usersRepository.findUsersContainingNameAndSurname(pageable, searchTextNombre, searchTextApellido);
    }

    public Page<User> findUsersContainingName(Pageable pageable, String searchTextNombre) {
        searchTextNombre = "%"+searchTextNombre+"%";
        return usersRepository.findUsersContainingName(pageable, searchTextNombre);
    }

    public Page<User> findUsersContainingSurname(Pageable pageable, String searchTextApellido) {
        searchTextApellido = "%"+searchTextApellido+"%";
        return usersRepository.findUsersContainingSurname(pageable, searchTextApellido);
    }
}