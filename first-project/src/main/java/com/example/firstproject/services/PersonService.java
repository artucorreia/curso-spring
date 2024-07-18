package com.example.firstproject.services;

import com.example.firstproject.controller.PersonController;
import com.example.firstproject.data.DTO.v1.PersonDTO;
import com.example.firstproject.exceptions.ResourceNotFoundException;
import com.example.firstproject.mapper.Mapper;
import com.example.firstproject.model.Person;
import com.example.firstproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PagedResourcesAssembler<PersonDTO> assembler;

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
        return person;
    }

    public PagedModel<EntityModel<PersonDTO>> findPeopleByFirstName(String firstName, Pageable pageable) {
        logger.info("Finding one person");

        Page<Person> entities = repository.findPeopleByFirstName(firstName, pageable);

        Page<PersonDTO> people = entities.map(
                person -> Mapper.parseObject(person, PersonDTO.class)
        );

        // HATEOAS
        people.map(
                person -> person.add(
                        linkTo(
                                methodOn(PersonController.class).findById(person.getId())
                        ).withSelfRel()
                )
        );

        return assembler.toModel(
                people,
                linkTo(methodOn(PersonController.class)
                        .findAll(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                "asc"
                        )).withSelfRel()
        );
    }

    public PagedModel<EntityModel<PersonDTO>> findAll(Pageable pageable) {
        logger.info("Finding all persons");

        Page<Person> entities = repository.findAll(pageable);

        Page<PersonDTO> peopleDTO = entities.map(person -> Mapper.parseObject(person, PersonDTO.class));

        // HATEOAS
        peopleDTO.map(
                personDTO -> personDTO.add(
                        linkTo(
                                methodOn(PersonController.class).findById(personDTO.getId())
                        ).withSelfRel()
                )
        );

        return assembler.toModel(
                peopleDTO,
                linkTo(methodOn(PersonController.class)
                        .findAll(
                                pageable.getPageNumber(),
                                pageable.getPageSize(),
                                pageable.getSort().toString()
                        )).withSelfRel()
        );
    }

    public PersonDTO create(PersonDTO personDTO) {
        logger.info("Creating one person");

        Person entity = Mapper.parseObject(personDTO, Person.class);

        PersonDTO person = Mapper.parseObject(repository.save(entity), PersonDTO.class);

        // HATEOAS
        person.add(linkTo(methodOn(PersonController.class).findById(person.getId())).withSelfRel());
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
        return person;
    }

    public PersonDTO partiallyUpdate(Long id, PersonDTO personDTO) {
        Person entity = Mapper.parseObject(findById(id), Person.class);

        if (
            personDTO.getFirstName() != null &&
            !Objects.equals(personDTO.getFirstName(), entity.getFirstName())
        ) {
            entity.setFirstName(personDTO.getFirstName());
        }

        if (
            personDTO.getLastName() != null &&
            !Objects.equals(personDTO.getLastName(), entity.getLastName())
        ) {
            entity.setLastName(personDTO.getLastName());
        }

        if (
            personDTO.getGender() != null &&
            !Objects.equals(personDTO.getGender(), entity.getGender())
        ) {
            entity.setGender(personDTO.getGender());
        }

        if (
            personDTO.getEnabled() != null &&
            !Objects.equals(personDTO.getEnabled(), entity.getEnabled())
        ) {
            entity.setEnabled(personDTO.getEnabled());
        }

        if (
            personDTO.getAddress() != null &&
            !Objects.equals(personDTO.getAddress(), entity.getAddress())
        ) {
            entity.setAddress(personDTO.getAddress());
        }

        return Mapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        Person entity = Mapper.parseObject(findById(id), Person.class);

        repository.delete(entity);
    }
}