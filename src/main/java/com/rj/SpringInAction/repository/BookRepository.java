package com.rj.SpringInAction.repository;

import java.util.List;
import java.util.Optional;

import com.rj.SpringInAction.models.Book;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(int id);
    Optional<Book> delete(int id);
    List<Book> findAll();
    Optional<Book> updateBook(int id, Book book);
    List<Book> findBookByTitle(String title);
}
