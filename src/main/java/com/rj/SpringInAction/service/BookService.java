package com.rj.SpringInAction.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rj.SpringInAction.exceptions.BookAlreadyExistsException;
import com.rj.SpringInAction.exceptions.BookNotFoundException;
import com.rj.SpringInAction.models.Book;
import com.rj.SpringInAction.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepositoryBackedByHashMap;

    public ResponseEntity<Object> saveBook(Book book) {
        Book savedBook = bookRepositoryBackedByHashMap.save(book);
        if (Objects.isNull(savedBook)) {
            // return ResponseEntity.badRequest().body("Book with Id : "+book.getId()+" already exists"); Using the exception handler instead
            throw new BookAlreadyExistsException("Book with Id : "+book.getId()+" already exists");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    public List<Book> getAllBooks() {
        return bookRepositoryBackedByHashMap.findAll();
    }

    public ResponseEntity<Book> findById(int id) {
        Optional<Book> returnedBook = bookRepositoryBackedByHashMap.findById(id);
        if(returnedBook.isPresent()) {
            return ResponseEntity.ok().body(returnedBook.get());
        }
        // return ResponseEntity.notFound().build(); Using exceptionHandler instead
        throw new BookNotFoundException("Book not found with id : "+id);

    }

    public ResponseEntity<Object> findBookByTitle(String title) {
        List<Book> foundBooks = bookRepositoryBackedByHashMap.findBookByTitle(title);
        if(!foundBooks.isEmpty()) {
            return ResponseEntity.ok().body(foundBooks);
        }
        // return ResponseEntity.notFound().build(); Using exceptionHandler instead
        throw new BookNotFoundException("No Books found with title : \""+title+"\"");
    }

    //Using <Object> in order to have flexibility of returning Book or String for error reason [not using exception handler]
    public ResponseEntity<Object> updateBook(int id, Book book) {
        Optional<Book> updatedBook = bookRepositoryBackedByHashMap.updateBook(id,book);
        if(updatedBook.isPresent())
            return ResponseEntity.ok().body(updatedBook.get());
        return ResponseEntity.badRequest().body("Book with Id : "+id+" doesn't exists");
    }

    public ResponseEntity<Object> deleteBookById(int id) {
        Optional<Book> deletedBook = bookRepositoryBackedByHashMap.delete(id);
        if(deletedBook.isPresent())
            return ResponseEntity.ok().body(deletedBook.get());
        return ResponseEntity.badRequest().body("Book with Id : "+id+" doesn't exists");
    }

}
