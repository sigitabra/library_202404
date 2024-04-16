package com.example.library.services;

import com.example.library.entities.Book;
import com.example.library.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@AllArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    public static final String LINE = "---------------------------------------------------------------------------------------------------------------------------------";

    public void addTestLaptops() {
        List<Book> books = new ArrayList<>();
        for (int i = 1; i < 25; i++) {
            Book book = new Book();

            book.setTitle("Title " + i);
            book.setAuthor("Author " + i);
            book.setYear(2020 + i);
            book.setGenre("Genre " + i);
            book.setLocation("Location " + i);
            books.add(book);
        }
        this.bookRepository.saveAllAndFlush(books);
    }

    public void addNewBooksToDb(Scanner sc) {
        List<Book> books = new ArrayList<>();

        String adding = "Y";

        while (adding.equalsIgnoreCase("Y")) {
            Book book = new Book();

            System.out.print("Enter book title: ");
            book.setTitle(sc.nextLine());

            System.out.print("Enter book author: ");
            book.setAuthor(sc.nextLine());

            System.out.print("Enter book year: ");
            try {
                book.setYear(Integer.parseInt(sc.nextLine()));
            } catch (NumberFormatException e) {
                System.out.print(e.getMessage());
            }

            System.out.print("Enter book genre: ");
            book.setGenre(sc.nextLine());

            System.out.print("Enter current book location: ");
            book.setLocation(sc.nextLine());

            books.add(book);

            System.out.print("Add one more book? Y/N ");
            adding = sc.nextLine();
        }

        this.bookRepository.saveAllAndFlush(books);
    }

    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public void printAllBooks() {
        getAllBooks().forEach(System.out::println);
    }

    public void deleteBookFromDb(Scanner sc) {
        printAllBooks();
        System.out.print("Enter book id, which should be deleted from the database: ");
        this.bookRepository.deleteById(Long.parseLong(sc.nextLine()));
    }

    public void updateBookLocation(Scanner sc) {
        printAllBooks();
        System.out.print("Enter book id, which location should be updated for the database: ");
        Long id = Long.parseLong(sc.nextLine());
        if (this.bookRepository.findById(id).isPresent()) {
            Book tempBook = this.bookRepository.findById(id).get();
            System.out.print("Enter book new location for your selected book: ");
            tempBook.setLocation(sc.nextLine());
            this.bookRepository.saveAndFlush(tempBook);
        }

    }

    public void printAllBooksByGenre(Scanner sc) {
        System.out.print("Enter book genre: ");
        String genre = sc.nextLine();
        List<Book> books = this.bookRepository.findBooksByGenre(genre);
        System.out.println(LINE);
        System.out.printf(Book.FORMAT + "\n", "Id", "Title", "Author", "Year", "Genre", "Location");
        System.out.println(LINE);
        books.forEach(System.out::println);
        System.out.println(LINE);
    }

    public void printNumberOfBooksByCurrentYearAndGenre(Scanner sc) {
        System.out.print("Enter book genre: ");
        int currentYear = LocalDate.now().getYear();
        String genre = sc.nextLine();
        int count = this.bookRepository.countBooksByYearAndGenre(currentYear, genre);
        System.out.printf("We have %d %s book(s) in %s\n", count, genre, currentYear);
    }
}
