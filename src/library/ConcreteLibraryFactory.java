package library;

/**
 * Concrete factory for creating books and members.
 */
public class ConcreteLibraryFactory extends LibraryFactory {
    @Override
    public Book createBook(String title, String author, int publicationYear) {
        return new Book(title, author, publicationYear);
    }

    @Override
    public Member createMember(String name, int id) {
        return new Member(name, id);
    }
}
