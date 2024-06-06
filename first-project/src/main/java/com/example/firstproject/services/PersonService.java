package com.example.firstproject.services;

import com.example.firstproject.data.DTO.v1.OccupationDTO;
import com.example.firstproject.data.DTO.v1.PersonDTO;
import com.example.firstproject.exceptions.ResourceNotFoundException;
import com.example.firstproject.mapper.Mapper;
import com.example.firstproject.model.Occupation;
import com.example.firstproject.model.Person;
import com.example.firstproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonService {
    private final Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    OccupationService occupationService;

    public PersonDTO findById(Long id) {
        logger.info("Finding one person");

        return Mapper.parseObject(
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("No records found for this id")
                ),
                PersonDTO.class
        );
    }

    public List<PersonDTO> findByFirstName(String firstName) {
        logger.info("Finding one person");

        return Mapper.parseListObjects(repository.findByFirstName(firstName), PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        logger.info("Finding all persons");

        return Mapper.parseListObjects(repository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one person");
        OccupationDTO occupationDTO = occupationService.findById(person.getOccupation().getId());
        person.setOccupation(occupationDTO);

        Person entity = Mapper.parseObject(person, Person.class);
//        logger.info(entity.toString());
//        logger.info(occupationDTO.toString());

        return Mapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one person");

        Person entity = Mapper.parseObject(findById(person.getId()), Person.class);

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return Mapper.parseObject(repository.save(entity), PersonDTO.class);
    }

    public void delete(Long id) {
        logger.info("Deleting one person");

        Person entity = Mapper.parseObject(findById(id), Person.class);

        repository.delete(entity);
    }
}