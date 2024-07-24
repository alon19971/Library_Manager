package library;

/**
 * Interface for subject in the Observer pattern.
 */
public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
