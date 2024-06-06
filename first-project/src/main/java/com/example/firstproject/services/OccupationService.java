package com.example.firstproject.services;

import com.example.firstproject.data.DTO.v1.OccupationDTO;
import com.example.firstproject.exceptions.ResourceNotFoundException;
import com.example.firstproject.mapper.Mapper;
import com.example.firstproject.model.Occupation;
import com.example.firstproject.repositories.OccupationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class OccupationService {
    private Logger logger = Logger.getLogger(OccupationService.class.getName());

    @Autowired
    private OccupationRepository repository;


    public OccupationDTO findById(Long id) {
        return Mapper.parseObject(
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("No records found for this ID")
                ),
                OccupationDTO.class
        );
    }

    public List<OccupationDTO> findAll() {
        return Mapper.parseListObjects(repository.findAll(), OccupationDTO.class);
    }

    public OccupationDTO create(OccupationDTO occupation) {
        Occupation entity = Mapper.parseObject(occupation, Occupation.class);

        return Mapper.parseObject(repository.save(entity), OccupationDTO.class);
    }

    public OccupationDTO update(OccupationDTO occupation) {
        Occupation entity = Mapper.parseObject(findById(occupation.getId()), Occupation.class);

        entity.setPosition(occupation.getPosition());
        entity.setWorkload(occupation.getWorkload());

        return Mapper.parseObject(repository.save(entity), OccupationDTO.class);
    }

    public void delete(Long id) {
        Occupation entity = Mapper.parseObject(findById(id), Occupation.class);

        repository.delete(entity);
    }
}
