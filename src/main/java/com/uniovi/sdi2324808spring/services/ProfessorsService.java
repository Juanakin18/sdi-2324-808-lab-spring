package com.uniovi.sdi2324808spring.services;

import com.uniovi.sdi2324808spring.entities.Professor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorsService {

    private List<Professor> professorList = new ArrayList<>();

    @PostConstruct
    public void init(){
        professorList.add(new Professor(1L, "12356", "Profesor1", "Ramirez", "Profesor Titular"));
        professorList.add(new Professor(2L, "123561", "Profesor2", "Fernandez", "Profesor Ayudante"));
    }
    public List<Professor> getProfessorList(){
        return  professorList;
    }

    public void addProfessor(Professor professor){
        professorList.add(professor);
    }
    public void deleteProfessor(Long id){
        professorList.remove(findProfessor(id));
    }
    public Professor findProfessor(Long id){
        for (Professor p:
             professorList) {
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public void setProfessor(Long id, Professor professor) {
        professor.setId(id);
        for (int i = 0; i < getProfessorList().size(); i++) {
            if(professorList.get(i).getId().equals(id))
                professorList.set(i, professor);
        }

    }
}
