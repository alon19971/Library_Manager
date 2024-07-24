package library;

import java.util.List;

/**
 * Interface for observer in the Observer pattern.
 */
public interface Observer {
    void update(List<Book> books);
}
