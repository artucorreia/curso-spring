package com.example.firstproject.services;

import com.example.firstproject.exceptions.ResourceNotFoundException;
import com.example.firstproject.model.Person;
import com.example.firstproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    public Person findById(Long id) {
        logger.info("Finding one person");

        return
            repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
    }

    public List<Person> findAll() {
        logger.info("Finding all persons");

        return repository.findAll();
    }

    public Person create(Person person) {
        logger.info("Creating one person");

        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person");

        Person entity = findById(person.getId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        repository.delete(findById(id));
    }
}
