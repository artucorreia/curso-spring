package com.example.firstproject.services;

import com.example.firstproject.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final AtomicLong counter = new AtomicLong();
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("Finding one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Arthur");
        person.setLastName("Correia");
        person.setAddress("Brazil - Maceió");
        person.setGender("Male");
        return person;
    }

    public List<Person> findAll() {
        logger.info("Finding all persons");

        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            Person person = mockPerson(String.valueOf(i));
            persons.add(person);
        }

        return persons;
    }

    public Person create(Person person) {
        logger.info("Creating one person");

        return person;
    }

    public Person update(Person person) {
        logger.info("Updating one person");

        return person;
    }

    public void delete(String id) {
        logger.info("Deleting one person");
    }

    public Person mockPerson(String id) {
        Person person = new Person();

        person.setId(counter.incrementAndGet());
        person.setFirstName("Arthur " + id);
        person.setLastName("Correia " + id);
        person.setAddress("Brazil - Maceió " + id);
        person.setGender("Male");

        return person;
    }
}
