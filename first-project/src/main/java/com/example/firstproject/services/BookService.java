package com.example.firstproject.services;

import com.example.firstproject.controller.BookController;
import com.example.firstproject.data.DTO.v1.BookDTO;
import com.example.firstproject.exceptions.ResourceNotFoundException;
import com.example.firstproject.mapper.Mapper;
import com.example.firstproject.model.Book;
import com.example.firstproject.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookService {
    private final Logger logger = Logger.getLogger(BookService.class.getName());

    @Autowired
    private BookRepository repository;

    @Autowired
    private PagedResourcesAssembler<BookDTO> assembler;

    public BookDTO findById(Integer id) {
        logger.info("Finding one book");

        BookDTO book = Mapper.parseObject(
                repository.findById(id)
                .orElseThrow(
                    () -> new ResourceNotFoundException("No records found for this id")
                ),
                BookDTO.class
        );

        book.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());

        return book;
    }

    public PagedModel<EntityModel<BookDTO>> findAll(Pageable pageable) {
        logger.info("Finding all books");

        Page<Book> entities = repository.findAll(pageable);

        Page<BookDTO> books = entities.map(entity -> Mapper.parseObject(entity, BookDTO.class));

        // HATEOAS dos objetos
        books.map(
                book -> book.add(
                        linkTo(
                                methodOn(BookController.class)
                        ).withSelfRel()
                )
        );

        // HATEOAS da page
        return assembler.toModel(
                books,
                linkTo(methodOn(BookController.class)).withSelfRel()
        );
    }

    public BookDTO create(BookDTO newBook) {
        logger.info("Creating one book");

        Book entity = Mapper.parseObject(newBook, Book.class);
        BookDTO bookDTO = Mapper.parseObject(repository.save(entity), BookDTO.class);

        bookDTO.add(linkTo(methodOn(BookController.class).findById(bookDTO.getId())).withSelfRel());

        return bookDTO;
    }

    public BookDTO update(BookDTO bookDTO) {
        logger.info("Updating one book");

        Book entity = Mapper.parseObject(findById(bookDTO.getId()), Book.class);

        entity.setAuthor(bookDTO.getAuthor());
        entity.setLaunchDate(bookDTO.getLaunchDate());
        entity.setPrice(bookDTO.getPrice());
        entity.setPrice(bookDTO.getPrice());
        entity.setTitle(bookDTO.getTitle());

        BookDTO book = Mapper.parseObject(repository.save(entity), BookDTO.class);

        book.add(linkTo(methodOn(BookController.class).findById(book.getId())).withSelfRel());

        return book;
    }

    public void delete(Integer id) {
        logger.info("Deleting one book");

        repository.delete(
                Mapper.parseObject(findById(id), Book.class)
        );
    }
}
