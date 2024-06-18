package com.rj.SpringInAction.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.rj.SpringInAction.models.Book;
import com.rj.SpringInAction.service.BookService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class MyFirstController {
    @Autowired
    BookService bookService;

    @RequestMapping(path="/welcome", method=RequestMethod.GET)
    public String welcome() {
        return "Welcome to the Rest Apis world created by RJ. This is my first REST API endpoint";
    }
   
    @PostMapping("/addBook")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }
    
    @GetMapping("/getBooks")
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/getBook/{id}")
    public ResponseEntity<Book> getMethodName(@PathVariable int id) {
        return bookService.findById(id);
    }
    
    @PutMapping("updateBook/{id}")
    public ResponseEntity<Object> putMethodName(@PathVariable int id, @RequestBody Book book) {
        return bookService.updateBook(id, book);
    }

    @DeleteMapping("deleteBook/{id}")
    public ResponseEntity<Object> deleteBookById(@PathVariable int id) {
        return bookService.deleteBookById(id);
    }

    @GetMapping("findBookByTitle")
    public ResponseEntity<Object> findBookByTitle(@RequestParam String title) {
        return bookService.findBookByTitle(title);
    }
    
    
}
