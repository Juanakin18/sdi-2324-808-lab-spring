package com.uniovi.sdi2324808spring.repositories;

import com.uniovi.sdi2324808spring.entities.Professor;
import org.springframework.data.repository.CrudRepository;


public interface ProfessorsRepository extends CrudRepository<Professor,Long> {

}
