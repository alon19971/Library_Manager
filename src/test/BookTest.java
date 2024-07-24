package test;

import library.Book;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {

    @Test
    public void testClone() {
        Book book = new Book("1984", "George Orwell", 1949);
        Book clonedBook = book.clone();

        assertNotNull(clonedBook);
        assertEquals(book.getTitle(), clonedBook.getTitle());
        assertEquals(book.getAuthor(), clonedBook.getAuthor());
        assertEquals(book.getPublicationYear(), clonedBook.getPublicationYear());
    }
}
