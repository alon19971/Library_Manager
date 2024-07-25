package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import library.Book;
import library.Library;

/**
 * Main window for the digital library management system.
 */
public class MainWindow extends Application {

    private Library library;

    /**
     * Starts the JavaFX application and initializes the main window.
     *
     * @param primaryStage The primary stage for this application.
     */
    @Override
    public void start(Stage primaryStage) {
        library = Library.getInstance();

        primaryStage.setTitle("Digital Library Management System");

        Label titleLabel = new Label("Digital Library");
        Label summaryLabel = new Label();
        updateSummary(summaryLabel);

        Button menuButton = new Button("Open Menu");
        menuButton.setOnAction(event -> LibraryUI.displayMenu(library, summaryLabel));

        VBox vbox = new VBox(10, titleLabel, summaryLabel, menuButton);
        vbox.setStyle("-fx-padding: 20; -fx-alignment: center; -fx-spacing: 10;");

        Scene scene = new Scene(vbox, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Updates the summary label with the current status of the library.
     *
     * @param summaryLabel The label to be updated.
     */
    private void updateSummary(Label summaryLabel) {
        int availableBooks = (int) library.getBooks().stream().filter(Book::isAvailable).count();
        int borrowedBooks = (int) library.getBooks().stream().filter(book -> !book.isAvailable()).count();
        int activeMembers = library.getMembers().size();
        int loans = library.getLoans().size();
        summaryLabel.setText(String.format("Available Books: %d\nBorrowed Books: %d\nActive Members: %d\nLoans: %d",
                availableBooks, borrowedBooks, activeMembers, loans));
    }

    /**
     * The main method to launch the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}
