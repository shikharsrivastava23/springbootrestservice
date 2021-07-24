package com.example.springbootrestservice.repository;

import com.example.springbootrestservice.entity.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryRepository extends JpaRepository<Library , String> , LibraryRepositoryCustom {
    
}
