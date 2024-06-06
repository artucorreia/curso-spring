package com.example.firstproject.controller;

import com.example.firstproject.data.DTO.v1.OccupationDTO;
import com.example.firstproject.services.OccupationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/occupations")
public class OccupationController {

    @Autowired
    private OccupationService service;

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OccupationDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<OccupationDTO> findAll() {
        return service.findAll();
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OccupationDTO create(@RequestBody OccupationDTO occupation) {
        return service.create(occupation);
    }
}
