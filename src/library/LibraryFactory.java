package library;

/**
 * Abstract factory for creating library objects such as books and members.
 */
public abstract class LibraryFactory {
    public abstract Book createBook(String title, String author, int publicationYear);
    public abstract Member createMember(String name, int id);
}
