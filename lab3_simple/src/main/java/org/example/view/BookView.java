package org.example.view;

import org.example.model.Book;

import java.util.List;

public class BookView {
    public void displayBooks(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("Немає книг для відображення.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}

