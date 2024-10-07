package org.example.controller;

import org.example.model.Book;
import org.example.view.BookView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BookController {
    private List<Book> books;
    private BookView view;

    public BookController(BookView view) {
        this.view = view;
        this.books = new ArrayList<>();
        generateBooks(); // Ініціалізація випадковими даними
    }

    private void generateBooks() {
        String[] titles = {"Book1", "Book2", "Book3", "Book4", "Book5", "Book6", "Book7", "Book8", "Book9", "Book10"};
        String[] authors = {"Author1", "Author2", "Author3", "Author4", "Author5"};
        String[] publishers = {"Publisher1", "Publisher2", "Publisher3", "Publisher4"};

        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            books.add(new Book(
                    titles[rand.nextInt(titles.length)],
                    authors[rand.nextInt(authors.length)],
                    publishers[rand.nextInt(publishers.length)],
                    1990 + rand.nextInt(30),
                    100 + rand.nextInt(500),
                    100.0 + rand.nextDouble() * 50
            ));
        }
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Вивести всі книги");
            System.out.println("2. Пошук книг за автором");
            System.out.println("3. Пошук книг за видавництвом");
            System.out.println("4. Пошук книг, виданих після певного року");
            System.out.println("5. Сортувати книги за видавництвом");
            System.out.println("0. Вийти");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Очистка після вводу числа

            switch (choice) {
                case 1:
                    view.displayBooks(books);
                    break;
                case 2:
                    System.out.print("Введіть автора: ");
                    String author = scanner.nextLine();
                    searchByAuthor(author);
                    break;
                case 3:
                    System.out.print("Введіть видавництво: ");
                    String publisher = scanner.nextLine();
                    searchByPublisher(publisher);
                    break;
                case 4:
                    System.out.print("Введіть рік: ");
                    int year = scanner.nextInt();
                    searchByYear(year);
                    break;
                case 5:
                    sortByPublisher();
                    break;
                case 0:
                    System.out.println("Вихід.");
                    return;
                default:
                    System.out.println("Невірний вибір, спробуйте знову.");
            }
        }
    }

    public void searchByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().equalsIgnoreCase(author)) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            view.showMessage("Книг цього автора не знайдено.");
        } else {
            view.displayBooks(result);
        }
    }

    public void searchByPublisher(String publisher) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getPublisher().equalsIgnoreCase(publisher)) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            view.showMessage("Книг цього видавництва не знайдено.");
        } else {
            view.displayBooks(result);
        }
    }

    public void searchByYear(int year) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getYearOfPublication() > year) {
                result.add(book);
            }
        }
        if (result.isEmpty()) {
            view.showMessage("Книг, виданих після цього року, не знайдено.");
        } else {
            view.displayBooks(result);
        }
    }

    public void sortByPublisher() {
        books.sort(Comparator.comparing(Book::getPublisher));
        view.displayBooks(books);
    }
}

