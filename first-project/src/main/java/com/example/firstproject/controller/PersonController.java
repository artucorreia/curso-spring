package com.example.firstproject.controller;

import com.example.firstproject.model.Person;
import com.example.firstproject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @GetMapping
    public Person findById(@PathVariable(value = "id") String id) {
        return personService.findById(id);
    }

    @RequestMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @GetMapping
    public List<Person> findAll() {
        return personService.findAll();
    }

    @RequestMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PostMapping
    public Person create(@RequestBody Person person) {
        return personService.create(person);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
//    @PutMapping
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }

    @RequestMapping(value = "/{id}")
    @DeleteMapping
    public void delete(@PathVariable(value = "id") String id) {
        personService.delete(id);
    }
}