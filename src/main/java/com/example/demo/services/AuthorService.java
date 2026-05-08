package com.example.demo.services;

import com.example.demo.domain.Author;

public interface AuthorService {

    Iterable<Author> findAll();
}
