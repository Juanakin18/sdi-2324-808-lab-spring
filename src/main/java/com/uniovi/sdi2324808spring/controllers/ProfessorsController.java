package com.uniovi.sdi2324808spring.controllers;

import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.entities.Professor;
import com.uniovi.sdi2324808spring.services.ProfessorsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
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
        return "professor/add";
    }
    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(@ModelAttribute Professor professor) {
        professorsService.addProfessor(professor);
        return "redirect:/professor/list";
    }
    @RequestMapping("/professor/delete/{id}")
    public String deleteProfessor(@PathVariable Long id){
        professorsService.deleteProfessor(id);
        return "redirect:/professor/list";
    }
    @RequestMapping("/professor/list")
    public String listProfessor(Model model){
       model.addAttribute("professorsList", professorsService.getProfessorList());
       return "professor/list";
    }
    @RequestMapping("/professor/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("professor", professorsService.findProfessor(id));
        return "professor/details";
    }
    @RequestMapping(value = "/professor/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("professor", professorsService.findProfessor(id));
        return "professor/edit";
    }

    @RequestMapping(value="/professor/edit/{id}", method=RequestMethod.POST)
    public String setEditProfessor(@ModelAttribute Professor professor, @PathVariable Long id){
        professorsService.setProfessor(id, professor);
        return "redirect: /professor/details/"+id;
    }

}
