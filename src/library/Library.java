package library;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class representing the Library.
 * Manages books and members, and implements the Observer pattern to notify changes.
 */
public class Library implements Subject {
    private static Library instance;
    private List<Book> books;
    private List<Member> members;
    private List<Loan> loans;
    private List<Observer> observers;

    /**
     * Private constructor to prevent instantiation.
     */
    private Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
        loans = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the Library.
     *
     * @return the single instance of Library
     */
    public static synchronized Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(books);
        }
    }

    public void addBook(Book book) {
        books.add(book);
        notifyObservers();
    }

    public void removeBook(Book book) {
        books.remove(book);
        notifyObservers();
    }

    public void registerMember(Member member) {
        members.add(member);
    }

    public void unregisterMember(Member member) {
        members.remove(member);
    }

    public void borrowBook(Member member, Book book) {
        if (book.isAvailable()) {
            book.setAvailable(false);
            Loan loan = new Loan(book, member);
            loans.add(loan);
            notifyObservers();
        } else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book) {
        for (Loan loan : loans) {
            if (loan.getBook().equals(book)) {
                loan.getBook().setAvailable(true);
                loans.remove(loan);
                notifyObservers();
                break;
            }
        }
    }

    public void changeBookStatus(Book book, boolean isAvailable) {
        book.setAvailable(isAvailable);
        notifyObservers();
    }

    public void displaySummary() {
        int availableBooks = (int) books.stream().filter(Book::isAvailable).count();
        int borrowedBooks = (int) books.stream().filter(book -> !book.isAvailable()).count();
        int activeMembers = members.size();
        int loansCount = loans.size();
        System.out.println("Library Summary:");
        System.out.println("Available Books: " + availableBooks);
        System.out.println("Borrowed Books: " + borrowedBooks);
        System.out.println("Active Members: " + activeMembers);
        System.out.println("Loans: " + loansCount);
    }

    public Book duplicateBook(Book book) {
        return book.clone();
    }

    // Getters for private variables (only if necessary)
    public List<Book> getBooks() {
        return books;
    }

    public List<Member> getMembers() {
        return members;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public List<Observer> getObservers() {
        return observers;
    }
}
