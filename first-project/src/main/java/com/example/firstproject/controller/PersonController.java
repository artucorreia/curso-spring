package com.example.firstproject.controller;

import com.example.firstproject.data.DTO.v1.PersonDTO;
import com.example.firstproject.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/people/v1")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return personService.findById(id);
    }

    @GetMapping(
            value = "/find",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> findByFirstName(
            @RequestParam(name = "firstName", value = "firstName", defaultValue = "") String firstName
    ) {
        return personService.findByFirstName(firstName);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<PersonDTO> findAll() {
        return personService.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return personService.create(person);
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return personService.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }
}