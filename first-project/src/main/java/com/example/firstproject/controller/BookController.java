package com.example.firstproject.controller;

import com.example.firstproject.data.DTO.v1.BookDTO;
import com.example.firstproject.services.BookService;
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
@RequestMapping("/api/v1/books")
@Tag(name = "Books", description = "Endpoints for manager books")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping(
            value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @Operation(
            summary = "Find one Book",
            description = "Find one Book",
            tags = {"Books"},
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
                                    schema = @Schema(implementation = BookDTO.class)
                            ),
                            @Content(
                                    mediaType = "application/xml",
                                    schema = @Schema(implementation = BookDTO.class)
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
    public BookDTO findById(@PathVariable Integer id) {
        return service.findById(id);
    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    @Operation(
            summary = "Find all Books",
            description = "Find all Books",
            tags = {"Books"},
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
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            array = @ArraySchema(schema = @Schema(implementation = BookDTO.class))
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
    public List<BookDTO> findAll() {
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
    @Operation(
            summary = "Adds a new Books",
            description = "Adds a new Books passing in a JSON or XML",
            tags = {"Books"},
            method = "POST"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BookDTO.class)
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            schema = @Schema(implementation = BookDTO.class)
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
                            responseCode = "500", description = "Internal Error", content = @Content
                    )
            }
    )
    public BookDTO create(@RequestBody BookDTO book) {
        return service.create(book);
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
    @Operation(
            summary = "Updates a Book",
            description = "Updates a Book passing in JSON or XML",
            tags = {"Books"},
            method = "PUT"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Success",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = BookDTO.class)
                                    ),
                                    @Content(
                                            mediaType = "application/xml",
                                            schema = @Schema(implementation = BookDTO.class)
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
                    )
            }
    )
    public BookDTO update(@RequestBody BookDTO book) {
        return service.update(book);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(
            summary = "Deletes a Book",
            description = "Deletes a Book",
            tags = {"Books"},
            method = "DELETE"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204", description = "Success", content = @Content
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
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
