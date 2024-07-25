package ui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import library.Book;
import library.Library;
import library.Member;

/**
 * User interface for library management.
 */
public class LibraryUI {

    /**
     * Displays the main menu of the library system.
     *
     * @param library The library instance containing books and members.
     * @param summaryLabel The label used to display the summary of the library's status.
     */
    public static void displayMenu(Library library, Label summaryLabel) {
        Stage window = new Stage();
        window.setTitle("Library Menu");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Add or delete a book
        Button addBookButton = new Button("Add Book");
        Button deleteBookButton = new Button("Delete Book");
        GridPane.setConstraints(addBookButton, 0, 0);
        GridPane.setConstraints(deleteBookButton, 1, 0);

        addBookButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Book");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter book details (title,author,year):");
            dialog.showAndWait().ifPresent(details -> {
                String[] parts = details.split(",");
                if (parts.length == 3) {
                    try {
                        String title = parts[0].trim();
                        String author = parts[1].trim();
                        int year = Integer.parseInt(parts[2].trim());
                        Book book = new Book(title, author, year);
                        library.addBook(book);
                        System.out.println("Book added: " + book);
                        updateSummary(summaryLabel, library);
                    } catch (NumberFormatException ex) {
                        showAlert("Invalid year format. Please enter a valid number for the year.");
                    }
                } else {
                    showAlert("Invalid input format. Please enter details in the format: title,author,year");
                }
            });
        });

        deleteBookButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Book");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter book title to delete:");
            dialog.showAndWait().ifPresent(title -> {
                Book book = library.getBooks().stream().filter(b -> b.getTitle().equals(title.trim())).findFirst().orElse(null);
                if (book != null) {
                    library.removeBook(book);
                    System.out.println("Book deleted: " + book);
                    updateSummary(summaryLabel, library);
                } else {
                    showAlert("Book not found");
                }
            });
        });

        // Add or delete a member
        Button addMemberButton = new Button("Add Member");
        Button deleteMemberButton = new Button("Delete Member");
        GridPane.setConstraints(addMemberButton, 0, 1);
        GridPane.setConstraints(deleteMemberButton, 1, 1);

        addMemberButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Add Member");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter member details (name,id):");
            dialog.showAndWait().ifPresent(details -> {
                String[] parts = details.split(",");
                if (parts.length == 2) {
                    try {
                        String name = parts[0].trim();
                        int id = Integer.parseInt(parts[1].trim());
                        Member member = new Member(name, id);
                        library.registerMember(member);
                        System.out.println("Member added: " + member);
                        updateSummary(summaryLabel, library);
                    } catch (NumberFormatException ex) {
                        showAlert("Invalid ID format. Please enter a valid number for the ID.");
                    }
                } else {
                    showAlert("Invalid input format. Please enter details in the format: name,id");
                }
            });
        });

        deleteMemberButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Delete Member");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter member id to delete:");
            dialog.showAndWait().ifPresent(id -> {
                try {
                    int memberId = Integer.parseInt(id.trim());
                    Member member = library.getMembers().stream().filter(m -> m.getId() == memberId).findFirst().orElse(null);
                    if (member != null) {
                        library.unregisterMember(member);
                        System.out.println("Member deleted: " + member);
                        updateSummary(summaryLabel, library);
                    } else {
                        showAlert("Member not found");
                    }
                } catch (NumberFormatException ex) {
                    showAlert("Invalid ID format. Please enter a valid number for the ID.");
                }
            });
        });

        // Borrow or return a book
        Button borrowBookButton = new Button("Borrow Book");
        Button returnBookButton = new Button("Return Book");
        GridPane.setConstraints(borrowBookButton, 0, 2);
        GridPane.setConstraints(returnBookButton, 1, 2);

        borrowBookButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Borrow Book");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter member id and book title (id,title):");
            dialog.showAndWait().ifPresent(details -> {
                String[] parts = details.split(",");
                if (parts.length == 2) {
                    try {
                        int memberId = Integer.parseInt(parts[0].trim());
                        String title = parts[1].trim();
                        System.out.println("Borrow attempt - Member ID: " + memberId + ", Book Title: " + title);
                        Member member = library.getMembers().stream().filter(m -> m.getId() == memberId).findFirst().orElse(null);
                        Book book = library.getBooks().stream().filter(b -> b.getTitle().equals(title)).findFirst().orElse(null);
                        if (member != null && book != null) {
                            library.borrowBook(member, book);
                            System.out.println("Book borrowed: " + book + " by member: " + member);
                            updateSummary(summaryLabel, library);
                        } else {
                            System.out.println("Member or book not found - Member: " + member + ", Book: " + book);
                            showAlert("Member or book not found");
                        }
                    } catch (NumberFormatException ex) {
                        showAlert("Invalid ID format. Please enter a valid number for the member ID.");
                    }
                } else {
                    showAlert("Invalid input format. Please enter details in the format: id,title");
                }
            });
        });

        returnBookButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Return Book");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter book title to return:");
            dialog.showAndWait().ifPresent(title -> {
                Book book = library.getBooks().stream().filter(b -> b.getTitle().equals(title.trim())).findFirst().orElse(null);
                if (book != null) {
                    library.returnBook(book);
                    System.out.println("Book returned: " + book);
                    updateSummary(summaryLabel, library);
                } else {
                    showAlert("Book not found");
                }
            });
        });

        // Change book status
        Button changeStatusButton = new Button("Change Book Status");
        GridPane.setConstraints(changeStatusButton, 0, 3);

        changeStatusButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Change Book Status");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter book title and status (title,available/borrowed):");
            dialog.showAndWait().ifPresent(details -> {
                String[] parts = details.split(",");
                if (parts.length == 2) {
                    String title = parts[0].trim();
                    String status = parts[1].trim();
                    Book book = library.getBooks().stream().filter(b -> b.getTitle().equals(title)).findFirst().orElse(null);
                    if (book != null) {
                        boolean isAvailable = status.equalsIgnoreCase("available");
                        library.changeBookStatus(book, isAvailable);
                        System.out.println("Book status changed: " + book + " to " + (isAvailable ? "available" : "borrowed"));
                        updateSummary(summaryLabel, library);
                    } else {
                        showAlert("Book not found");
                    }
                } else {
                    showAlert("Invalid input format. Please enter details in the format: title,available/borrowed");
                }
            });
        });

        // Display library summary
        Button displaySummaryButton = new Button("Display Summary");
        GridPane.setConstraints(displaySummaryButton, 1, 3);

        displaySummaryButton.setOnAction(e -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Library Summary");
            alert.setHeaderText("Library Status Summary");

            int availableBooks = (int) library.getBooks().stream().filter(Book::isAvailable).count();
            int borrowedBooks = (int) library.getBooks().stream().filter(book -> !book.isAvailable()).count();
            int activeMembers = library.getMembers().size();
            int loans = library.getLoans().size();

            String summary = String.format(
                    "Available Books: %d\nBorrowed Books: %d\nActive Members: %d\nLoans: %d",
                    availableBooks, borrowedBooks, activeMembers, loans);

            alert.setContentText(summary);
            alert.showAndWait();
        });

        // Adding a mechanism for duplicating a book
        Button duplicateBookButton = new Button("Duplicate Book");
        GridPane.setConstraints(duplicateBookButton, 0, 4);

        duplicateBookButton.setOnAction(e -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Duplicate Book");
            dialog.setHeaderText(null);
            dialog.setContentText("Enter book title to duplicate:");
            dialog.showAndWait().ifPresent(title -> {
                Book book = library.getBooks().stream().filter(b -> b.getTitle().equals(title.trim())).findFirst().orElse(null);
                if (book != null) {
                    Book duplicatedBook = library.duplicateBook(book);
                    library.addBook(duplicatedBook);
                    System.out.println("Book duplicated: " + duplicatedBook);
                    updateSummary(summaryLabel, library);
                } else {
                    showAlert("Book not found");
                }
            });
        });

        grid.getChildren().addAll(addBookButton, deleteBookButton, addMemberButton, deleteMemberButton, borrowBookButton, returnBookButton, changeStatusButton, displaySummaryButton, duplicateBookButton);

        Scene scene = new Scene(grid, 400, 300);
        window.setScene(scene);
        window.show();
    }

    /**
     * Updates the library summary label with the current status of the library.
     *
     * @param summaryLabel The label to be updated.
     * @param library      The library instance containing books and members.
     */
    private static void updateSummary(Label summaryLabel, Library library) {
        int availableBooks = (int) library.getBooks().stream().filter(Book::isAvailable).count();
        int borrowedBooks = (int) library.getBooks().stream().filter(book -> !book.isAvailable()).count();
        int activeMembers = library.getMembers().size();
        int loans = library.getLoans().size();
        summaryLabel.setText(String.format("Available Books: %d\nBorrowed Books: %d\nActive Members: %d\nLoans: %d",
                availableBooks, borrowedBooks, activeMembers, loans));
    }

    /**
     * Displays an alert with the given message.
     *
     * @param message The message to be displayed in the alert.
     */
    private static void showAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
