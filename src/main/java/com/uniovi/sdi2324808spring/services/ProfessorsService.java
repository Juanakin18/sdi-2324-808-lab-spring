package com.uniovi.sdi2324808spring.services;

import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.entities.Professor;
import com.uniovi.sdi2324808spring.repositories.MarksRepository;
import com.uniovi.sdi2324808spring.repositories.ProfessorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    @Autowired
    private  ProfessorsRepository professorsRepository;



    public List<Professor> getProfessorList(){
        List<Professor> professors = new ArrayList<Professor>();
        professorsRepository.findAll().forEach(professors::add);
        return professors;
    }

    public void addProfessor(Professor professor){
       professorsRepository.save(professor);
    }
    public void deleteProfessor(Long id){
        professorsRepository.deleteById(id);
    }
    public Professor findProfessor(Long id){
        return professorsRepository.findById(id).get();
    }


}
