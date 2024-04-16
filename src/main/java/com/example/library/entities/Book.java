package com.example.library.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private int year;

    private String genre;

    private String location;

    public static final String FORMAT = " %-5s | %-30s | %-30s | %-4s | %-20s | %-30s ";
    @Override
    public String toString() {
        return String.format(FORMAT,getId(), getTitle(),getAuthor(), getYear(), getGenre(), getLocation());
    }
}
