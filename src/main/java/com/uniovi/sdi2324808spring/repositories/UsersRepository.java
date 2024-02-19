package com.uniovi.sdi2324808spring.repositories;

import com.uniovi.sdi2324808spring.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UsersRepository extends CrudRepository<User, Long>{
    User findByDni(String dni);
    Page<User> findAll(Pageable pageable);

    @Query("SELECT r FROM User r where (lower(r.name) like lower(?1) or lower (r.lastName) like lower(?1))")
    Page<User> findUsersContainingNameAndSurname(Pageable pageable, String searchTextNombre, String searchTextApellido);
    @Query("SELECT r FROM User r where (lower(r.name) like lower(?1))")
    Page<User> findUsersContainingName(Pageable pageable, String searchTextNombre);
    @Query("SELECT r FROM User r where (lower(r.lastName) like lower(?1))")
    Page<User> findUsersContainingSurname(Pageable pageable, String searchTextApellido);
}