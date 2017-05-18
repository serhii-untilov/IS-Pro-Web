package ua.in.usv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import ua.in.usv.entity.Person;
import ua.in.usv.service.PersonService;

@Controller
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping("/person")
    public String personPage(@RequestParam(required = false) Long id, Model model){
        Person person = personService.findById(id);
        model.addAttribute("id", person.getId());
        model.addAttribute("name", person.getName());
        return "person";
    }
}
