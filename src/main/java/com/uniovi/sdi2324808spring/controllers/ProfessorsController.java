package com.uniovi.sdi2324808spring.controllers;

import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.entities.Professor;
import com.uniovi.sdi2324808spring.services.ProfessorsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProfessorsController {

    //Inyeccion de dependencias
    //Autowired -> NO RECOMENDADO
    //Constructor -> SEGURO
    //Setter -> SEGURO
    private final ProfessorsService professorsService;

    public ProfessorsController(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }

    @RequestMapping(value = "/professor/add")
    public String getAddProfessor() {
        return "Get Professor added";
    }
    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return "Professor added";
    }
    @RequestMapping("/professor/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        professorsService.deleteProfessor(id);
        return "Borrando el profesor con id "+ id;
    }
    @RequestMapping("/professor/list")
    public String listProfessor(){
       return professorsService.getProfessorList().toString();
    }
    @RequestMapping("/professor/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return professorsService.findProfessor(id).toString();
    }
    @RequestMapping(value = "/professor/edit/{id}")
    public String getEdit(@PathVariable Long id) {
        return "Editando el profesor con id "+id;
    }

    @RequestMapping(value="/professor/edit/{id}", method=RequestMethod.POST)
    public String setEditProfessor(@ModelAttribute Professor professor, @PathVariable Long id){
        professorsService.setProfessor(id, professor);
        return "Se ha editado el profesor de id "+id;
    }

}
