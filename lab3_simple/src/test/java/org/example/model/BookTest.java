package org.example.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private Book book1;
    private Book book2;
    private Book book3;

    @BeforeEach
    void setUp() {
        // Створюємо кілька об'єктів книги для порівняння
        book1 = new Book("Title1", "Author1", "Publisher1", 2001, 300, 150.0);
        book2 = new Book("Title1", "Author1", "Publisher1", 2001, 300, 150.0);
        book3 = new Book("Title2", "Author2", "Publisher2", 2005, 400, 200.0);
    }

    @Test
    void testEquals_SameProperties() {
        // Перевіряємо, що дві книги з однаковими властивостями є рівними
        assertEquals(book1, book2, "Books with the same properties should be equal");
    }

    @Test
    void testEquals_DifferentProperties() {
        // Перевіряємо, що книги з різними властивостями не є рівними
        assertNotEquals(book1, book3, "Books with different properties should not be equal");
    }

    @Test
    void testHashCode_SameProperties() {
        // Перевіряємо, що дві книги з однаковими властивостями мають однаковий hashCode
        assertEquals(book1.hashCode(), book2.hashCode(), "Books with the same properties should have the same hash code");
    }

    @Test
    void testHashCode_DifferentProperties() {
        // Перевіряємо, що книги з різними властивостями мають різний hashCode
        assertNotEquals(book1.hashCode(), book3.hashCode(), "Books with different properties should have different hash codes");
    }

    @Test
    void testToString() {
        // Перевіряємо, що метод toString повертає правильний формат
        String expected = "Book{title='Title1', author='Author1', publisher='Publisher1', yearOfPublication=2001, numberOfPages=300, price=150.0}";
        assertEquals(expected, book1.toString(), "toString method should return the correct format");
    }

    @Test
    void testGetters() {
        // Перевіряємо, що гетери повертають правильні значення
        assertEquals("Title1", book1.getTitle());
        assertEquals("Author1", book1.getAuthor());
        assertEquals("Publisher1", book1.getPublisher());
        assertEquals(2001, book1.getYearOfPublication());
        assertEquals(300, book1.getNumberOfPages());
        assertEquals(150.0, book1.getPrice());
    }
}
