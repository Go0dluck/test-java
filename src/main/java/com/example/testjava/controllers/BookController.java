package com.example.testjava.controllers;

import com.example.testjava.models.Book;
import com.example.testjava.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.function.BiFunction;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/title-sorted-in-reverse")
    public List<Book> getBooksTitleSortedInReverse() {
        return bookService.findAllByOrderByTitleDesc();
    }

    @GetMapping("/author-sorted")
    public List<Book> getBooksAuthorSorted() {
        return bookService.findAllByOrderByAuthorAsc();
    }

    @PostMapping("")
    public ResponseEntity<HttpStatus> addBook(@RequestBody Book book){
        bookService.save(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/search-char-in-title")
    public LinkedHashMap<String, Long> searchCharInTitle(@RequestParam("char") char title){
        BiFunction<Long, Long, Long> bFunc = (oldValue, newValue)-> oldValue + newValue;
        List<Book> books = bookService.findAllByTitleIgnoreCaseContaining(String.valueOf(title));
        Map<String, Long> unSortedMap = new HashMap<>();
        LinkedHashMap<String, Long> reverseSortedMap = new LinkedHashMap<>();

        books.forEach(
                x -> unSortedMap.merge(x.getAuthor(), x.getCountChar(title), bFunc));
        unSortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));
        return reverseSortedMap;
    }
}
