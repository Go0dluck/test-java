package com.example.testjava.repositories;

import com.example.testjava.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByOrderByTitleDesc();
    List<Book> findAllByOrderByAuthorAsc();
    List<Book> findAllByTitleIgnoreCaseContaining(String title);
}
