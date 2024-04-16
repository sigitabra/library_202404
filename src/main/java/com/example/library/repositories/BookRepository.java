package com.example.library.repositories;

import com.example.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBooksByGenre(String genre);
    int countBooksByYearAndGenre(int year, String genre);

}
