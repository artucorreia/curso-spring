package com.example.firstproject.services;

import com.example.firstproject.controller.PersonController;
import com.example.firstproject.data.DTO.v1.PersonDTO;
import com.example.firstproject.exceptions.ResourceNotFoundException;
import com.example.firstproject.mapper.Mapper;
import com.example.firstproject.model.Person;
import com.example.firstproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");

        PersonDTO person = Mapper.parseObject(
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("No records found for this id")
                ),
                PersonDTO.class
        );

        // HATEOAS
        person.add(
                linkTo(
                        methodOn(PersonController.class).findById(person.getId())
                ).withSelfRel()
        );
        person.add(
                linkTo(
                        methodOn(PersonController.class).findAll()
                ).withRel("people")
        );
        return person;
    }

    public List<PersonDTO> findByFirstName(String firstName) {
        logger.info("Finding one person");

        List<PersonDTO> people = Mapper.parseListObjects(repository.findByFirstName(firstName), PersonDTO.class);

        // HATEOAS
        return people.stream().map(
                person -> person.add(
                        linkTo(
                                methodOn(PersonController.class).findById(person.getId())
                        ).withSelfRel()
                )
        ).toList();
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons");

        List<PersonDTO> people = Mapper.parseListObjects(repository.findAll(), PersonDTO.class);

        // HATEOAS
        return people.stream().map(
                person -> person.add(
                        linkTo(
                                methodOn(PersonController.class).findById(person.getId())
                        ).withSelfRel()
                )
        ).toList();
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating one person");

        Person entity = Mapper.parseObject(personDTO, Person.class);

        PersonDTO person = Mapper.parseObject(repository.save(entity), PersonDTO.class);

        // HATEOAS
        person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        person.add(linkTo(methodOn(PersonController.class).findAll()).withRel("people"));
        return person;
    }

    public PersonDTO update(PersonDTO personDTO) {
        logger.info("Updating one person");

        Person entity = Mapper.parseObject(findById(personDTO.getId()), Person.class);

        entity.setFirstName(personDTO.getFirstName());
        entity.setLastName(personDTO.getLastName());
        entity.setAddress(personDTO.getAddress());
        entity.setGender(personDTO.getGender());

        PersonDTO person = Mapper.parseObject(repository.save(entity), PersonDTO.class);

        // HATEOAS
        person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
        person.add(linkTo(methodOn(PersonController.class).findAll()).withRel("people"));
        return person;
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        Person entity = Mapper.parseObject(findById(id), Person.class);

        repository.delete(entity);
    }
}