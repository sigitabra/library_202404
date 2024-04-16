package com.example.library;

import com.example.library.services.BookService;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.Scanner;

@AllArgsConstructor
@SpringBootApplication
public class LibraryApplication {
    private BookService bookService;

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStart() {
        this.bookService.addTestLaptops();
        boolean isRunning = true;
        Scanner sc = new Scanner(System.in);
        while (isRunning) {
            System.out.println("Select action: \n A - add new books \n D - delete books by ID \n U - update book location \n LG - list books by selected genre \n CG - get number of books by selected genre published this year \n");
            switch (sc.nextLine().toUpperCase()) {
                case "A" -> this.bookService.addNewBooksToDb(sc);
                case "D" -> this.bookService.deleteBookFromDb(sc);
                case "U" -> this.bookService.updateBookLocation(sc);
                case "LG" -> this.bookService.printAllBooksByGenre(sc);
                case "CG" -> this.bookService.printNumberOfBooksByCurrentYearAndGenre(sc);
                default -> isRunning = false;
            }
        }
        sc.close();
    }
}
