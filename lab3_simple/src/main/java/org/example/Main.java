package org.example;

import org.example.controller.BookController;
import org.example.view.BookView;

public class Main {
    public static void main(String[] args) {
        BookView view = new BookView();
        BookController controller = new BookController(view);

        controller.showMenu();
    }}