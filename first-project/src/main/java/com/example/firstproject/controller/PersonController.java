package com.example.firstproject.controller;

import com.example.firstproject.data.DTO.v1.PersonDTO;
import com.example.firstproject.services.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @Operation(
            summary = "Finds a Person",
            description = "Finds a Person",
            tags = {"People"},
            method = "GET"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = PersonDTO.class)
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            schema = @Schema(implementation = PersonDTO.class)
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Bad Request", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Not Found", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Error", content = @Content
                    ),
            }
    )
    public PersonDTO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(
            value = "/find",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @Operation(
            summary = "Find People by name",
            description = "Find People by name",
            tags = {"People"},
            method = "GET"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Internal Error", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Internal Error", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Internal Error", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Error", content = @Content
                    ),
            }
    )
    public List<PersonDTO> findByFirstName(
            @RequestParam(name = "firstName", value = "firstName", defaultValue = "") String firstName
    ) {
        return service.findByFirstName(firstName);
    }

    @Operation(summary = "Find all People", description = "Find all People", method = "GET")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class))
                                    )
                            }
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Bad Request", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Not Found", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Error", content = @Content
                    ),
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
    @Operation(summary = "Adds a new Person", description = "Adds a new Person passing in a JSON or XML", method = "POST")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = PersonDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Bad Request", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Error", content = @Content
                    )
            }
    )
    public PersonDTO create(@RequestBody PersonDTO person) {
        return service.create(person);
    }

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
    @Operation(summary = "Updates a Person's information", description = "Updates a Person's information passing in a JSON or XML", method = "PUT")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Update",
                            content = @Content(
                                    schema = @Schema(
                                            implementation = PersonDTO.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Bad Request", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Not Found", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Error", content = @Content
                    )
            }
    )
    public PersonDTO update(@RequestBody PersonDTO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Delete a Person", description = "Delete a Person", method = "DELETE")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204", description = "No Content", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "400", description = "Bad Request", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "401", description = "Unauthorized", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "404", description = "Not Found", content = @Content
                    ),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Error", content = @Content
                    )
            }
    )
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}