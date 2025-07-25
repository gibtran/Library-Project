import java.util.ArrayList;
import java.util.Arrays;

public class App {
    User user;
    Library library;

    App() {
        Library library = new Library(new ArrayList<>(Arrays.asList(Booklist.list)));
        this.user = new User(library);
        this.library = user.getLibrary();
    }

    public static void main(String[] args) {
        App app = new App();
        app.runMainMenu();
    }

    void runMainMenu() {
        System.out.println("-----------------------------------");
        System.out.println("LIBRARY SYSTEM");
        System.out.println("-----------------------------------");

        while (true) {
            System.out.println("-------------------------------");
            System.out.println("Select an option:");
            System.out.println("  1. View all books");
            System.out.println("  2. Search book by title");
            System.out.println("  3. Checkout book in details");
            System.out.println("  4. View your Book history");
            System.out.println("  5. Exit");
            System.out.println("-------------------------------");
            int choice = In.nextInt();
            if (choice == 1) {
                viewAllBooks();
            } else if (choice == 2) {
                searchBookByTitle();
            } else if (choice == 3) {
                handleCheckoutBook();
            } else if (choice == 4) {
                viewBookHistory();
            } else if (choice == 5) {
                System.out.println("Existing...");
                System.out.println("Thank you for using the App!");
                break;
            } else {
                System.out.println("Pick an option from 1 - 5!");
            }
        }
    }

    void viewAllBooks() {
        System.out.println("--------------------------------");
        System.out.println("All books from the library are: ");
        System.out.println(library);
        System.out.println("--------------------------------");
    }

    void searchBookByTitle() {
        while (true) {
            System.out.println("Enter the title of the book: ");
            String title = In.nextLine().trim();
            Book foundedBook = library.findBook(title);
            if (foundedBook != null) {
                System.out.println("-------------");
                System.out.println("The book is: ");
                System.out.println("-------------");

                foundedBook.displayInfo();
            } else {
                System.out.println("Sorry, can't find the book with your titles!");
            }
            System.out.println("Would you like to keep searching the book? (Yes/No)");
            String choice = In.nextLine().toLowerCase().trim();

            if (choice.equals("no")) {
                System.out.println("Back to the main menu...");
                break;
            }
        }
    }

    void handleCheckoutBook() {
        System.out.println("Enter the title of the book: ");
        String title = In.nextLine().trim();
        Book foundedBook = library.findBook(title);
        if (foundedBook != null) {
            if (foundedBook instanceof DigitalBook d) {
                if (d.canDownload()) {
                    System.out.println("This is Digital book, would you like to download it? (Yes/No)");
                    String choice = In.nextLine().toLowerCase();
                    if (choice.equals("yes")) {
                        System.out.println("This book has been downloaded to your account");
                        user.checkoutBook(foundedBook);
                    }
                } else {
                    System.out.println("Sorry this book can not be downloaded");
                }
            } else if (foundedBook instanceof PrintBook p) {
                if (p.getAvailable()) {
                    System.out.println("This Print Book is available, would you like to borrow it? (Yes/No)");
                    String choice = In.nextLine().toLowerCase();
                    if (choice.equals("yes")) {
                        System.out.println("You have successfully borrowed the book");
                        user.checkoutBook(foundedBook);
                    }
                } else {
                    System.out.println("Sorry this Book is not available ");
                }
            }
        } else {
            System.out.println("Sorry, the book is not exist!");
        }
    }

    void viewBookHistory() {
        System.out.println("This is your book history: ");
        System.out.println("- Borrowed books: ");
        user.listBorrowedBooks();
        System.out.println("- Downloaded books: ");
        user.listDownloadedBooks();
    }
}

class Booklist {
    static Book[] list = {
            new PrintBook("White Nights", "Fyodor Dostoevsky", Genre.POETRY, 112, CoverType.PAPERBACK),
            new DigitalBook("The Green Mile", "Stephen King", Genre.FICTION, 400, Format.EPUB),
            new PrintBook("The Hobbit", "J.R.R. Tolkien", Genre.FANTASY, 310, CoverType.HARDCOVER),
            new DigitalBook("Neuromancer", "William Gibson", Genre.SCIENCE_FICTION, 271, Format.AZW),
            new PrintBook("Pride and Prejudice", "Jane Austen", Genre.ROMANCE, 279, CoverType.LEATHER_BOUND),
            new DigitalBook("The Martian", "Andy Weir", Genre.SCIENCE_FICTION, 369, Format.DJVU),
            new PrintBook("The Da Vinci Code", "Dan Brown", Genre.MYSTERY, 454, CoverType.GRAPHIC_NOVEL),
            new DigitalBook("Leaves of Grass", "Walt Whitman", Genre.POETRY, 145, Format.TXT),
            new PrintBook("Educated", "Tara Westover", Genre.NON_FICTION, 334, CoverType.SPIRAL_BOUND),
            new DigitalBook("The Silent Patient", "Alex Michaelides", Genre.MYSTERY, 325, Format.HTML),
            new PrintBook("Jane Eyre", "Charlotte Brontë", Genre.ROMANCE, 500, CoverType.MAGAZINE),
            new DigitalBook("The Subtle Art of Not Giving a F*ck", "Mark Manson", Genre.NON_FICTION, 224, Format.DOCX),
            new PrintBook("The Great Gatsby", "F. Scott Fitzgerald", Genre.FICTION, 180, CoverType.HARDCOVER),
            new DigitalBook("A Brief History of Time", "Stephen Hawking", Genre.NON_FICTION, 212, Format.PDF),
            new PrintBook("Dracula", "Bram Stoker", Genre.FANTASY, 418, CoverType.LEATHER_BOUND),
            new DigitalBook("The Alchemist", "Paulo Coelho", Genre.FICTION, 197, Format.EPUB),
            new PrintBook("Wuthering Heights", "Emily Brontë", Genre.ROMANCE, 416, CoverType.GRAPHIC_NOVEL),
            new DigitalBook("The Raven", "Edgar Allan Poe", Genre.POETRY, 64, Format.TXT),
            new PrintBook("The Catcher in the Rye", "J.D. Salinger", Genre.FICTION, 277, CoverType.SPIRAL_BOUND),
            new DigitalBook("Dune", "Frank Herbert", Genre.SCIENCE_FICTION, 412, Format.AZW)
    };
}