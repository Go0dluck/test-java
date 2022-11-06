package com.example.testjava.services;

import com.example.testjava.models.Book;
import com.example.testjava.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllByOrderByTitleDesc(){
        return bookRepository.findAllByOrderByTitleDesc();
    }

    public List<Book> findAllByOrderByAuthorAsc() {
        return bookRepository.findAllByOrderByAuthorAsc();
    }

    public List<Book> findAllByTitleIgnoreCaseContaining(String title){
        return bookRepository.findAllByTitleIgnoreCaseContaining(title);
    }

    @Transactional
    public void save(Book book){
        bookRepository.save(book);
    }
}
