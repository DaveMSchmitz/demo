package org.daveschmitz.demo.controllers;

import org.daveschmitz.demo.models.PersonModel;
import org.daveschmitz.demo.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class GreetingController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="firstName", required=false, defaultValue="John") String firstName,
                           @RequestParam(name="lastName", required=false, defaultValue="Smith") String lastName,
                           Model model) {
        var person = new PersonModel(firstName,lastName);
        personRepository.save(person);
        model.addAttribute("person", person);

        var personList = new ArrayList<PersonModel>();
        personRepository.findAll().forEach(personList::add);
        model.addAttribute("personList", personList);

        return "greeting";
    }

    @GetMapping("/add")
    public String add(@RequestParam(name="firstName", required=false, defaultValue="John") String firstName,
                           Model model) {
        model.addAttribute("person", new PersonModel());
        model.addAttribute("personList", personRepository.findByFirstName(firstName));

        return "greeting";
    }


}
