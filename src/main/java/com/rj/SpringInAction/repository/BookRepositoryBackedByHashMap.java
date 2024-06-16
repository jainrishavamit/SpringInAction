package com.rj.SpringInAction.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.rj.SpringInAction.models.Book;

@Repository
public class BookRepositoryBackedByHashMap implements BookRepository{

    private HashMap<Integer, Book> library = new HashMap<>();

    @Override
    public Book save(Book book) {
        if(library.containsKey(book.getId())) {
            return null;
        }
        library.put(book.getId(), book);
        return book;
    }

    @Override
    public Optional<Book> findById(int id) {
        if(library.containsKey(id)){
            return Optional.of(library.get(id));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Book> delete(int id) {
        if(library.containsKey(id)) {
            Book bookToBeDeleted = library.get(id);
            library.remove(id);
            return Optional.of(bookToBeDeleted);
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        return library.values().stream().toList();
    }

    @Override
    public Optional<Book> updateBook(int id, Book book) {
        if(library.containsKey(id)){
            library.put(id, book);
            return Optional.of(library.get(id));
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findBookByTitle(String title) {
        return library.values().stream()
            .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
            .toList();
    }

}
