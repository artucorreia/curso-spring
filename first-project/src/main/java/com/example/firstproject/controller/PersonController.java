package com.example.firstproject.controller;

import com.example.firstproject.data.DTO.v1.PersonDTO;
import com.example.firstproject.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
@Tag(name = "People", description = "Endpoints for manager people")
public class PersonController {

    @Autowired
    private PersonService service;

    @Operation(summary = "Realiza busca de uma pessoa pelo Id", method = "GET")
    @ApiResponses(
        value = {
            @ApiResponse(responseCode = "200", description = "Busca de pessoa realizada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca por uma pessoa")
        }
    )
    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @Operation(summary = "Realiza busca de pessoas pelo nome", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Busca de pessoas realizada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao realizar busca por pessoas")
            }
    )
    @GetMapping(
            value = "/find",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public List<PersonDTO> findByFirstName(
            @RequestParam(name = "firstName", value = "firstName", defaultValue = "") String firstName
    ) {
        return service.findByFirstName(firstName);
    }

    @Operation(summary = "Realiza busca de todas as pessoas do sistema", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Busca de pessoas realizada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de todas as pessoas")
            }
    )
    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public List<PersonDTO> findAll() {
        return service.findAll();
    }

    @Operation(summary = "Realiza o cadastro de uma pessoa", method = "POST")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Pessoa cadastrada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao realizar cadastro da pessoa")
            }
    )
    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

    @Operation(summary = "Realiza a edição dos dados de uma pessoa", method = "PUT")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Edição dos dados de uma pessoa realizada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao realizar edição dos dados de pessoa")
            }
    )
    @PutMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @Operation(summary = "Realiza a exclusão de uma pessoa pelo Id", method = "DELETE")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Exclusão de pessoa realizada com sucesso"),
                    @ApiResponse(responseCode = "500", description = "Erro ao excluir uma pessoa")
            }
    )
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}