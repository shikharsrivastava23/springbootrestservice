package com.example.springbootrestservice.repository;

import java.util.List;

import com.example.springbootrestservice.entity.Library;

public interface LibraryRepositoryCustom {

    List<Library> findAllByAuthor(String authorName);

}
 