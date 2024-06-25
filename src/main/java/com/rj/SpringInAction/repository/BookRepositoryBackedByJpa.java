package com.rj.SpringInAction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rj.SpringInAction.models.Book;

@Repository
public interface BookRepositoryBackedByJpa extends JpaRepository<Book,Integer>{

} 
