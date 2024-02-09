package com.uniovi.sdi2324808spring.repositories;

import com.uniovi.sdi2324808spring.entities.*;
import org.springframework.data.repository.CrudRepository;
public interface UsersRepository extends CrudRepository<User, Long>{
}