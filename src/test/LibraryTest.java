package test;

import library.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {
    private Library library;

    @BeforeEach
    public void setUp() {
        library = Library.getInstance();
        library.getBooks().clear();
        library.getMembers().clear();
        library.getLoans().clear();
    }

    @Test
    public void testAddAndRemoveBook() {
        Book book = new Book("1984", "George Orwell", 1949);
        library.addBook(book);
        assertTrue(library.getBooks().contains(book));

        library.removeBook(book);
        assertFalse(library.getBooks().contains(book));
    }

    @Test
    public void testRegisterAndUnregisterMember() {
        Member member = new Member("Alice", 1);
        library.registerMember(member);
        assertTrue(library.getMembers().contains(member));

        library.unregisterMember(member);
        assertFalse(library.getMembers().contains(member));
    }

    @Test
    public void testBorrowAndReturnBook() {
        Member member = new Member("Alice", 1);
        Book book = new Book("1984", "George Orwell", 1949);
        library.registerMember(member);
        library.addBook(book);

        library.borrowBook(member, book);
        assertFalse(book.isAvailable());
        assertTrue(library.getLoans().stream().anyMatch(loan -> loan.getBook().equals(book) && loan.getMember().equals(member)));

        library.returnBook(book);
        assertTrue(book.isAvailable());
        assertFalse(library.getLoans().stream().anyMatch(loan -> loan.getBook().equals(book)));
    }

    @Test
    public void testChangeBookStatus() {
        Book book = new Book("1984", "George Orwell", 1949);
        library.addBook(book);

        library.changeBookStatus(book, false);
        assertFalse(book.isAvailable());

        library.changeBookStatus(book, true);
        assertTrue(book.isAvailable());
    }

    @Test
    public void testDuplicateBook() {
        Book book = new Book("1984", "George Orwell", 1949);
        library.addBook(book);

        Book duplicatedBook = library.duplicateBook(book);
        library.addBook(duplicatedBook);

        assertEquals(book.getTitle(), duplicatedBook.getTitle());
        assertEquals(book.getAuthor(), duplicatedBook.getAuthor());
        assertEquals(book.getPublicationYear(), duplicatedBook.getPublicationYear());
    }
}
