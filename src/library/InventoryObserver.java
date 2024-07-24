package library;

import java.util.List;

/**
 * Concrete observer for inventory updates in the library.
 */
public class InventoryObserver implements Observer {
    @Override
    public void update(List<Book> books) {
        System.out.println("Inventory updated. Total books: " + books.size());
    }
}
