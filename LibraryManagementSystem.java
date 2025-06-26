import java.util.ArrayList;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private boolean isIssued;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public void issueBook() {
        isIssued = true;
    }

    public void returnBook() {
        isIssued = false;
    }

    @Override
    public String toString() {
        return title + " by " + author + (isIssued ? " (Issued)" : " (Available)");
    }
}

public class LibraryManagementSystem {
    private static ArrayList<Book> library = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static void addBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine().trim();

        library.add(new Book(title, author));
        System.out.println("Book added successfully!");
    }

    private static void viewBooks() {
        if (library.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }

        System.out.println("\nList of books:");
        for (int i = 0; i < library.size(); i++) {
            System.out.println((i + 1) + ". " + library.get(i));
        }
    }

    private static void issueBook() {
        viewBooks();
        System.out.print("Enter the number of the book to issue: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > library.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Book book = library.get(choice - 1);
        if (book.isIssued()) {
            System.out.println("Sorry, this book is already issued.");
        } else {
            book.issueBook();
            System.out.println("Book issued successfully.");
        }
    }

    private static void returnBook() {
        viewBooks();
        System.out.print("Enter the number of the book to return: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice < 1 || choice > library.size()) {
            System.out.println("Invalid choice.");
            return;
        }
        Book book = library.get(choice - 1);
        if (!book.isIssued()) {
            System.out.println("This book is already available.");
        } else {
            book.returnBook();
            System.out.println("Book returned successfully.");
        }
    }

    public static void main(String[] args) {
        int option;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addBook();
                    break;
                case 2:
                    viewBooks();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        } while (option != 5);
    }
}