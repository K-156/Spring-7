package com.example.demo.services;

import com.example.demo.domain.Book;

public interface BookService {

    Iterable<Book> findAll();
}
