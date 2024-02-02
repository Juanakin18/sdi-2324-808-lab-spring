package com.uniovi.sdi2324808spring.controllers;


import com.uniovi.sdi2324808spring.entities.Mark;
import com.uniovi.sdi2324808spring.services.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MarksController {

    @Autowired
    private MarksService marksService;
    @RequestMapping("/mark/list")
    public String getList() {
        return marksService.getMarks().toString();
    }
    @RequestMapping(value = "/mark/add", method = RequestMethod.POST)
    public String setMark(@ModelAttribute Mark mark) {
        marksService.addMark(mark);
        return "Ok";
    }
    /*
    @RequestMapping("/mark/details")
    public String getDetail(@RequestParam Long id) {
        return "Getting Details =>"+id;
    }
    */

    @RequestMapping("/mark/details/{id}")
    public String getDetail(@PathVariable Long id) {
        return marksService.getMark(id).toString();
    }

    @RequestMapping("/mark/delete/{id}")
    public String deleteMark(@PathVariable Long id){
        marksService.deleteMark(id);
        return "Ok";
    }

}
