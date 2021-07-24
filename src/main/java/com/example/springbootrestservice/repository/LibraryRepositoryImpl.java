package com.example.springbootrestservice.repository;

import java.util.ArrayList;
import java.util.List;

import com.example.springbootrestservice.entity.Library;

import org.springframework.beans.factory.annotation.Autowired;

public class LibraryRepositoryImpl implements LibraryRepositoryCustom {

    @Autowired
    LibraryRepository repository;

    @Override
    public List<Library> findAllByAuthor(String authorName) {
        List<Library> bookswithAuthor = new ArrayList<Library>();
        List<Library> books = repository.findAll();
        for (Library item : books) {
            if (item.getAuthor().equalsIgnoreCase(authorName)) {
                bookswithAuthor.add(item);
            }
        }

        return bookswithAuthor;
    }

}
