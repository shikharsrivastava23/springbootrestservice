package com.example.springbootrestservice.service;

import java.util.Optional;

import com.example.springbootrestservice.entity.Library;
import com.example.springbootrestservice.repository.LibraryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibraryService {
    @Autowired
    LibraryRepository repository;

    public String buildId(String isbn, String aisle) {
        return isbn + aisle;
    }

    public boolean checkBookAlreadyExist(String id) {

        Optional<Library> lib = repository.findById(id);
        if (lib.isPresent())
            return true;
        else
            return false;
    }

    public Library getBookById(String id) {
        return repository.findById(id).get();
    }
}
