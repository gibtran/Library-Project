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
        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("          LIBRARY SYSTEM          ");
            System.out.println("-----------------------------------");
            System.out.println("Select an option:");
            System.out.println("    1. View all books");
            System.out.println("    2. Checkout a book");
            System.out.println("    3. View your books");
            System.out.println("    4. Return a print book");
            System.out.println("    5. Exit");
            System.out.println("-----------------------------------");
            int choice = In.nextInt();
            if (choice == 1) {
                viewAllBooks();
            } else if (choice == 2) {
                checkoutBook();
            } else if (choice == 3) {
                viewBooks();
            } else if (choice == 4) {
                returnPrintBook();
            } else if (choice == 5) {
                System.out.println("-----------------------------------");
                System.out.println("Exiting...");
                System.out.println("-----------------------------------");
                System.out.println("Thank you for using the app!");
                System.out.println("-----------------------------------");
                break;
            } else {
                System.out.println("-----------------------------------");
                System.out.println("Pick an option from 1 - 5!");
            }
        }
    }

    void viewAllBooks() {
        System.out.println("-----------------------------------");
        System.out.println("Books in the library: ");
        System.out.println(library + "-----------------------------------");

        while (true) {
            System.out.println("Select an option: ");
            System.out.println("    1. Sort");
            System.out.println("    2. Filter");
            System.out.println("    3. Exit");
            System.out.println("-----------------------------------");
            int choice = In.nextInt();

            if (choice == 1) {
                System.out.println("-----------------------------------");
                System.out.println("You can sort library by:  ");
                System.out.println("    1. Title");
                System.out.println("    2. Author");
                System.out.println("    3. Exit");
                System.out.println("-----------------------------------");

                int choice1 = In.nextInt();
                System.out.println("-----------------------------------");

                if (choice1 == 1) {
                    library.sortByTitle();
                    System.out.println("Sorting by Title... ");
                } else if (choice1 == 2) {
                    library.sortByAuthor();
                    System.out.println("Sorting by Author... ");
                } else if (choice1 == 3) {
                    System.out.println("Exiting...");
                    return;
                } else {
                    System.out.println("Pick an option from 1-3!");
                    System.out.println("Exiting...");
                    return;
                }

                System.out.println("-----------------------------------");
                System.out.println("New order: ");
                System.out.println("-----------------------------------");
                System.out.println(library + "-----------------------------------");
            } else if (choice == 2) {
                System.out.println("-----------------------------------");
                System.out.println("You can filter library by: ");
                System.out.println("    1. Digital book");
                System.out.println("    2. Print book");
                System.out.println("    3. Filter by genre");
                System.out.println("    4. Filter by author");
                System.out.println("    5. Exit");
                System.out.println("-----------------------------------");
                int choice2 = In.nextInt();

                if (choice2 == 1) {
                    Library digitalBooks = library.filterDigitalBook();
                    System.out.println("-----------------------------------");
                    System.out.println("Filtering digital books... ");
                    System.out.println("-----------------------------------");
                    System.out.println(digitalBooks + "-----------------------------------");
                } else if (choice2 == 2) {
                    Library printBooks = library.filterPrintBook();
                    System.out.println("\nFiltering print books... ");
                    System.out.println("-----------------------------------");
                    System.out.println(printBooks + "-----------------------------------");
                } else if (choice2 == 3) {
                    System.out.println("Filtering book by genre...");

                    System.out.println("----------Fiction----------");
                    Library fictionBooks = library.filterBooks(Genre.FICTION);
                    System.out.println(fictionBooks);

                    System.out.println("----------Mystery----------");
                    Library mysteryBooks = library.filterBooks(Genre.MYSTERY);
                    System.out.println(mysteryBooks);

                    System.out.println("----------Non-Fiction----------");
                    Library nonFictionBooks = library.filterBooks(Genre.NON_FICTION);
                    System.out.println(nonFictionBooks);

                    System.out.println("----------Fantasy----------");
                    Library fantasyBooks = library.filterBooks(Genre.FANTASY);
                    System.out.println(fantasyBooks);

                    System.out.println("----------Science Fiction----------");
                    Library scienceFictionBooks = library.filterBooks(Genre.SCIENCE_FICTION);
                    System.out.println(scienceFictionBooks);

                    System.out.println("----------Romance----------");
                    Library romanceBooks = library.filterBooks(Genre.ROMANCE);
                    System.out.println(romanceBooks);

                    System.out.println("----------Poetry----------");
                    Library poetryBooks = library.filterBooks(Genre.POETRY);
                    System.out.println(poetryBooks);

                    System.out.println("-----------------------------------");
                } else if (choice2 == 4) {
                    System.out.println("-----------------------------------");
                    System.out.println("What is the name of the author you want to find? (Case-sensitive)");
                    System.out.println("-----------------------------------");
                    String ans = In.nextLine();
                    Library authorBooks = library.filterBooks(ans);
                    System.out.println("-----------------------------------");

                    if (authorBooks.getBooks().isEmpty()) {
                        System.out.println("No books found for author: " + ans);
                    } else {
                        System.out.println("Books by " + ans + ":\n");
                        for (Book book : authorBooks.getBooks()) {
                            book.displayInfo();
                            System.out.println();
                        }
                    }

                    System.out.println("-----------------------------------");
                } else if (choice2 == 5) {
                    System.out.println("-----------------------------------");
                    System.out.println("Exiting...");
                    return;
                }
            } else if (choice == 3) {
                System.out.println("-----------------------------------");
                System.out.println("Exiting...");
                return;
            } else {
                System.out.println("-----------------------------------");
                System.out.println("Pick an option from 1-3!");
                System.out.println("-----------------------------------");
            }
        }

    }

    void checkoutBook() {
        while (true) {
            System.out.println("-----------------------------------");
            System.out.println("Enter the title of the book (Case-sensitive): ");
            System.out.println("-----------------------------------");
            String title = In.nextLine();
            Book foundedBook = library.getBook(title);

            if (foundedBook != null) {
                System.out.println("-----------------------------------");
                System.out.println("Book found: ");
                System.out.println("-----------------------------------");
                foundedBook.displayInfo();

                System.out.println("-----------------------------------");
                System.out.println("Would you like to check out this book? (y/n) ");
                String choice = In.nextLine();

                if (choice.equals("y")) {
                    if (foundedBook instanceof DigitalBook d) {
                        if (d.canDownload()) {
                            System.out.println("This is a digital book, would you like to download it? (y/n)");
                            String choice2 = In.nextLine();
                            if (choice2.equals("y")) {
                                System.out.println("This book has been downloaded.");
                                user.checkoutBook(foundedBook);
                            }
                        } else {
                            System.out.println("Sorry this book cannot be downloaded.");
                        }
                    } else if (foundedBook instanceof PrintBook p) {
                        if (p.getAvailable()) {
                            System.out.println("This print book is available, would you like to borrow it? (y/n)");
                            String choice2 = In.nextLine();
                            if (choice2.equals("y")) {
                                if (user.getBorrowedBooks().size() < 3) {
                                    System.out.println("-----------------------------------");
                                    System.out.println("You have borrowed: " + foundedBook.getFormattedTitle());
                                    user.checkoutBook(foundedBook);
                                } else {
                                    System.out.println("You have exceeded the borrowing limit");
                                    System.out.println(
                                            "Each Account can only borrow maximum 3 books, please return to keep using this service!");
                                }
                            }
                        } else {
                            System.out.println("Sorry this book is not available!");
                        }
                    }
                } else {
                    System.out.println("Cancelled checkout process.");
                }
            } else {
                System.out.println("-----------------------------------");
                System.out.println("Sorry, can't find a book with that title!");
            }
            while (true) {
                System.out.println("-----------------------------------");
                System.out.println("Select an option: ");
                System.out.println("    1. Choose another book");
                System.out.println("    2. Exit");
                System.out.println("-----------------------------------");
                int choice3 = In.nextInt();

                if (choice3 == 1) {
                    break;
                } else if (choice3 == 2) {
                    System.out.println("-----------------------------------");
                    System.out.println("Exiting...");
                    return;
                } else {
                    System.out.println("-----------------------------------");
                    System.out.println("Pick an option from 1-2!");
                }
            }
        }

    }

    void viewBooks() {
        System.out.println("-----------------------------------");
        System.out.println("These are your books: ");
        System.out.println("- Borrowed books: ");
        user.listBorrowedBooks();
        System.out.println("- Downloaded books: ");
        user.listDownloadedBooks();
    }

    void returnPrintBook() {
        if (user.getBorrowedBooks().isEmpty()) {
            System.out.println("-----------------------------------");
            System.out.println("You have no borrowed books!");
            return;
        }

        System.out.println("-----------------------------------");
        System.out.println("This is your borrowed book list:");
        user.listBorrowedBooks();
        System.out.println("What book would you like to return? (Title, case-sensitive)");
        System.out.println("-----------------------------------");
        String title = In.nextLine();
        System.out.println("-----------------------------------");
        Book bookToReturn = library.getBook(title);

        if (bookToReturn != null && library.hasBook(bookToReturn)) {
            System.out.println("You have returned: " + bookToReturn.getFormattedTitle());
            user.returnBook(bookToReturn);
            System.out.println("-----------------------------------");
            System.out.println("Your updated borrowed books list:");
            user.listBorrowedBooks();
        } else {
            System.out.println("You dont have " + title + " in your borrowed book list!");
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
            new DigitalBook("Dune", "Frank Herbert", Genre.SCIENCE_FICTION, 412, Format.AZW),
            new PrintBook("The Idiot", "Fyodor Dostoevsky", Genre.POETRY, 650, CoverType.HARDCOVER)
    };
}