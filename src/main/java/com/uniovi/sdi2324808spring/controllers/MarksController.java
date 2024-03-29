package com.uniovi.sdi2324808spring.controllers;


import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.entities.User;
import com.uniovi.sdi2324808spring.services.MarksService;
import com.uniovi.sdi2324808spring.services.UsersService;
import com.uniovi.sdi2324808spring.validators.AddMarkValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class MarksController {

    private final MarksService marksService;
    private final UsersService usersService;
    private AddMarkValidator validator;
    private final HttpSession httpSession;
    public MarksController(MarksService marksService, UsersService usersService, AddMarkValidator
            markAddValidator, HttpSession httpSession) {
        this.marksService = marksService;
        this.usersService = usersService;
        this.validator = markAddValidator;
        this.httpSession = httpSession;
    }


    @RequestMapping("/mark/list")
    public String getList(Model model, Pageable pageable, Principal principal, @RequestParam(value="", required = false )String searchText) {
        String dni = principal.getName();
        User user= usersService.getUserByDni((dni));
        Page<Mark> marks;
        if(searchText !=null && !searchText.isEmpty()){
            marks = marksService.searchMarksByDescriptionAndNameForUser(pageable, searchText, user);
        }else{
            marks = marksService.getMarksForUser(pageable,user);
        }

        model.addAttribute("marksList", marks.getContent());
        model.addAttribute("page", marks);
        return "mark/list";
    }
    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(Model model, @ModelAttribute Mark mark, BindingResult result, Pageable pageable) {
        validator.validate(mark,result);
        if(result.hasErrors()){
            model.addAttribute("mark", mark);
            model.addAttribute("usersList", usersService.getUsers(pageable));
            return "mark/add";
        }
        marksService.addMark(mark);
        return "redirect:/mark/list";
    }
    /*
    @RequestMapping("/mark/details")
    public String getDetail(@RequestParam Long id) {
        return "Getting Details =>"+id;
    }
    */

    @RequestMapping("/mark/details/{id}")
    public String getDetail(Model model, @PathVariable Long id) {
        model.addAttribute("mark", marksService.getMark(id));
        return "mark/details";
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        marksService.deleteMark(id);
        return "redirect:/mark/list";
    }

    @RequestMapping(value="/mark/add")
    public String getMark(Model model, Pageable pageable){
        model.addAttribute("mark", new  Mark());
        model.addAttribute("usersList", usersService.getUsers(pageable));
        return "mark/add";
    }
    @RequestMapping(value = "/mark/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id, Pageable pageable) {
        model.addAttribute("mark", marksService.getMark(id));
        model.addAttribute("usersList", usersService.getUsers(pageable));
        return "mark/edit";
    }

    @RequestMapping(value = "/mark/edit/{id}", method = RequestMethod.POST)
    public String setEdit(@ModelAttribute Mark mark, @PathVariable Long id) {


        Mark originalMark = marksService.getMark(id);
// modificar solo score y description
        originalMark.setScore(mark.getScore());
        originalMark.setDescription(mark.getDescription());
        marksService.addMark(originalMark);
        return "redirect:/mark/details/" + id;
    }
    @RequestMapping("/mark/list/update")
    public String updateList(Model model, Pageable pageable, Principal principal) {
        String dni = principal.getName(); // DNI es el name de la autenticación
        User user = usersService.getUserByDni(dni);
        Page<Mark> marks = marksService.getMarksForUser(pageable,user);
        model.addAttribute("marksList",marks.getContent());
        return "mark/list :: marksTables";
    }
    @RequestMapping(value = "/mark/{id}/resend", method = RequestMethod.GET)
    public String setResendTrue(@PathVariable Long id) {
        marksService.setMarkResend(true, id);
        return "redirect:/mark/list";
    }
    @RequestMapping(value = "/mark/{id}/noresend", method = RequestMethod.GET)
    public String setResendFalse(@PathVariable Long id) {
        marksService.setMarkResend(false, id);
        return "redirect:/mark/list";
    }




}
