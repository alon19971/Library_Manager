# Library Manager

## Project Description
The Library Manager is a digital library management system designed to manage books, members, and book loans. It features an easy-to-use interface and is implemented using Java and JavaFX. The project also demonstrates the use of various design patterns, such as Factory Method, Observer, and Singleton.

## Features
- Add and delete books
- Add and delete members
- Borrow and return books
- Change book status (available/borrowed)
- Display library summary (available books, borrowed books, active members, loans)
- Duplicate books

## Design Patterns Used
- **Singleton:** Ensures only one instance of the Library class exists.
- **Factory Method:** Used to create instances of books and members.
- **Observer:** Tracks changes in book inventory and updates users.

## Installation Instructions
1. **Clone the repository:**
    ```bash
    git clone https://github.com/yourusername/Library_Manager.git
    ```
2. **Navigate to the project directory:**
    ```bash
    cd Library_Manager
    ```
3. **Open the project in your preferred Java IDE (e.g., IntelliJ IDEA).**
4. **Ensure you have Java and JavaFX installed.**

## Running the Application
1. **Build the project using your IDE.**
2. **Run the `Main` class located in `src/ui/Main.java`.**

## Usage
- **Adding a Book:** Click the "Add Book" button and enter the book details in the format `title,author,year`.
- **Deleting a Book:** Click the "Delete Book" button and enter the book title.
- **Adding a Member:** Click the "Add Member" button and enter the member details in the format `name,id`.
- **Deleting a Member:** Click the "Delete Member" button and enter the member id.
- **Borrowing a Book:** Click the "Borrow Book" button and enter the details in the format `id,title`.
- **Returning a Book:** Click the "Return Book" button and enter the book title.
- **Changing Book Status:** Click the "Change Book Status" button and enter the details in the format `title,available/borrowed`.
- **Displaying Library Summary:** Click the "Display Summary" button to view the current library status.
- **Duplicating a Book:** Click the "Duplicate Book" button and enter the book title.

## Project Structure
