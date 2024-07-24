package test;

import library.Book;
import library.ConcreteLibraryFactory;
import library.LibraryFactory;
import library.Member;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ConcreteLibraryFactoryTest {

    @Test
    public void testCreateBook() {
        LibraryFactory factory = new ConcreteLibraryFactory();
        Book book = factory.createBook("1984", "George Orwell", 1949);

        assertNotNull(book);
        assertEquals("1984", book.getTitle());
        assertEquals("George Orwell", book.getAuthor());
        assertEquals(1949, book.getPublicationYear());
    }

    @Test
    public void testCreateMember() {
        LibraryFactory factory = new ConcreteLibraryFactory();
        Member member = factory.createMember("Alice", 1);

        assertNotNull(member);
        assertEquals("Alice", member.getName());
        assertEquals(1, member.getId());
    }
}
