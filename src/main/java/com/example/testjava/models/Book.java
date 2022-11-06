package com.example.testjava.models;

import com.sun.istack.NotNull;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Arrays;

@Entity
@Table(name = "Book")
public class Book {
    @Id
    @Column(name = "id")
    @NotNull
    private int id;

    @Column(name = "title")
    @NotNull
    private String title;

    @Column(name = "author")
    @NotNull
    private String author;

    @Column(name = "description")
    private String description;

    public long getCountChar(char str){
        return title.toLowerCase().chars().filter(c -> c == Character.toLowerCase(str)).count();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
