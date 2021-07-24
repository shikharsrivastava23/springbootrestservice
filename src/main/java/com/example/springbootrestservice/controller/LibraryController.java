package com.example.springbootrestservice.controller;

import java.util.List;

import com.example.springbootrestservice.entity.Library;
import com.example.springbootrestservice.repository.LibraryRepository;
import com.example.springbootrestservice.service.LibraryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LibraryController {

    @Autowired
    LibraryRepository repository;

    @Autowired
    LibraryService libraryService;

    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @PostMapping("/addBook")
    public ResponseEntity<AddResponse> addBookImplementation(@RequestBody Library library) {

        AddResponse res = new AddResponse();

        String id = libraryService.buildId(library.getIsbn(), library.getAisle());

        boolean does_exist = libraryService.checkBookAlreadyExist(id);

        if (!does_exist) {

            logger.info("Book does not exist so creating one");

            library.setId(id);

            repository.save(library);

            res.setMsg("Success: Book has been Added");
            res.setId(library.getId());

            return new ResponseEntity<AddResponse>(res, HttpStatus.CREATED);
        } else {
            logger.info("Book exists, so skipping creation.");

            res.setMsg("Book already exists");
            res.setId(id);
            return new ResponseEntity<AddResponse>(res, HttpStatus.ACCEPTED);
        }

    }

    @GetMapping("/books")
	public List<Library> getBooks()
	{
		return repository.findAll();
	}

    @GetMapping("/book/{id}")
    public Library getBookById(@PathVariable(value = "id") String id) {

        try {
            Library lib = repository.findById(id).get();
            return lib;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/book/author")
    public List<Library> getBookByAuthor(@RequestParam(value = "authorname") String authorname) {
        return repository.findAllByAuthor(authorname);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<Library> updateBook(@PathVariable(value = "id") String id, @RequestBody Library library) {
        Library existingBook = repository.findById(id).get();

        existingBook.setAisle(library.getAisle());
        existingBook.setAuthor(library.getAuthor());
        existingBook.setBook_name(library.getBook_name());
        repository.save(existingBook);

        return new ResponseEntity<Library>(existingBook, HttpStatus.OK);
    }

    @DeleteMapping("/book")
    public ResponseEntity<String> deleteBookById(@RequestBody Library library) {
        Library libdelete = repository.findById(library.getId()).get();
        repository.delete(libdelete);
        logger.info("Book has been deleted");
        return new ResponseEntity<>("Book is deleted", HttpStatus.CREATED);
    }

}
