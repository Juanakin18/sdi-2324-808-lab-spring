package com.uniovi.sdi2324808spring.controllers;

import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.entities.Professor;
import com.uniovi.sdi2324808spring.services.ProfessorsService;
import com.uniovi.sdi2324808spring.validators.AddProfessorValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfessorsController {

    //Inyeccion de dependencias
    //Autowired -> NO RECOMENDADO
    //Constructor -> SEGURO
    //Setter -> SEGURO
    private final ProfessorsService professorsService;
    private AddProfessorValidator validator;

    public ProfessorsController(ProfessorsService professorsService, AddProfessorValidator validator) {
        this.professorsService = professorsService;
        this.validator = validator;
    }

    @RequestMapping(value = "/professor/add")
    public String getAddProfessor(Model model) {
        model.addAttribute("professor", new Professor());
        return "professor/add";
    }
    @RequestMapping(value = "/professor/add", method = RequestMethod.POST)
    public String setProfessor(Model model, @ModelAttribute Professor professor,  BindingResult result) {
        validator.validate(professor,result);
        if(result.hasErrors()){
            model.addAttribute("professor", professor);
            return "professor/add";
        }
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
        professor.setId(id);
        professorsService.addProfessor(professor);
        return "redirect: /professor/details/"+id;
    }

}
