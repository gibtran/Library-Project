import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
            System.out.println("    1. View all books");
            System.out.println("    2. Search book by title");
            System.out.println("    3. View your Book history");
            System.out.println("    4. Return print book to the library");
            System.out.println("    5. Exit");
            System.out.println("-------------------------------");
            int choice = In.nextInt();
            if (choice == 1) {
                viewAllBooks();
            } else if (choice == 2) {
                searchBookByTitle();
            } else if (choice == 3) {
                viewBookHistory();
            } else if (choice == 4) {
                returnPrintBook();
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
        System.out.println("Current books in the library are: ");
        System.out.println(library);
        System.out.println("--------------------------------");

        while (true) {
            System.out.println("Would you like to: " + " \n1.Sort" + " \n2.Filter" + " \n4.Exist");
            int choice = In.nextInt();
            if (choice == 1) {
                System.out.println("You can sort library by:  " + " \n1.Title" + " \n2.Author" + " \n3.Genre"
                        + " \n4.Exist");
                int choice1 = In.nextInt();
                if (choice1 == 1) {
                    library.sortByTitle();
                    System.out.println("\nSorting by Title... ");
                } else if (choice1 == 2) {
                    library.sortByAuthor();
                    System.out.println("\nSorting by Author... ");
                } else if (choice1 == 3) {
                    library.sortByGenre();
                    System.out.println("\nSorting by Genre... ");
                } else if (choice1 == 4) {
                    System.out.println("Existing...");
                    break;
                }
                System.out.println("\n------------");
                System.out.println("New order: ");
                System.out.println("\n" + library);
            } else if (choice == 2) {
                System.out
                        .println("You can filter library by: " + "\n1.Digital book" + "\n2.Print book" + " \n4.Exist");
                int choice2 = In.nextInt();
                if (choice2 == 1) {
                    List<Book> digitalBooks = library.filterDigitalBook();
                    System.out.println("\nFiltering Digital Book... ");
                    System.out.println("\n------------");
                    System.out.println(new Library(new ArrayList<>(digitalBooks)));
                } else if (choice2 == 2) {
                    List<Book> printBooks = library.filterPrintBook();
                    System.out.println("\nFiltering Print Book... ");
                    System.out.println("\n------------");
                    System.out.println(new Library(new ArrayList<>(printBooks)));
                } else if (choice2 == 4) {
                    System.out.println("Existing...");
                    break;
                }
            } else if (choice == 4) {
                System.out.println("Existing...");
                break;
            }
        }

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
                return;
            }
            System.out.println("Would you like to check out this book? (Yes/No) ");
            String choice = In.nextLine().toLowerCase().trim();

            if (choice.equals("yes")) {
                if (foundedBook instanceof DigitalBook d) {
                    if (d.canDownload()) {
                        System.out.println("This is Digital book, would you like to download it? (Yes/No)");
                        String choice2 = In.nextLine().toLowerCase();
                        if (choice2.equals("yes")) {
                            System.out.println("This book has been downloaded to your account");
                            user.checkoutBook(foundedBook);
                        }
                    } else {
                        System.out.println("Sorry this book can not be downloaded");
                    }
                } else if (foundedBook instanceof PrintBook p) {
                    if (p.getAvailable()) {
                        System.out.println("This Print Book is available, would you like to borrow it? (Yes/No)");
                        String choice2 = In.nextLine().toLowerCase();
                        if (choice2.equals("yes")) {
                            System.out.println("You have successfully borrowed the book");
                            user.checkoutBook(foundedBook);
                        }
                    } else {
                        System.out.println("Sorry this Book is not available ");
                    }
                }

            }
            System.out.println("Do you want to choose other books or exit");
            System.out.println("    1. Choose another book");
            System.out.println("    2. Exit");
            int choice3 = In.nextInt();
            if (choice3 == 1) {
                searchBookByTitle();
            } else if (choice3 == 2) {
                System.out.println("Existing...");
                break;
            } else {
                System.out.println("Please choose option 1 or 2");
            }

        }

    }

    void viewBookHistory() {
        System.out.println("This is your book history: ");
        System.out.println("- Borrowed books: ");
        user.listBorrowedBooks();
        System.out.println("- Downloaded books: ");
        user.listDownloadedBooks();
    }

    void returnPrintBook() {
        System.out.println("This your borrowed book list:");
        user.listBorrowedBooks();
        System.out.println("What book you want to return to the library?");
        String title = In.nextLine().trim();
        Book bookToReturn = library.findBook(title);
        if (bookToReturn != null && library.hasBook(bookToReturn)) {
            user.returnBook(bookToReturn);
            System.out.println("Thank you! You have returned successfully your book to the library");
            System.out.println("Your borrowed book list now:");
            user.listBorrowedBooks();
        } else {
            System.out.println("You dont have " + title + " in your borrowed list");
            return;
        }
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