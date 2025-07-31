import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface BookFilter {
    // Returns all digital books.
    Library filterDigitalBook();

    // Returns all digital books by author.
    Library filterDigitalBook(String author);

    // Returns all digital books by genre.
    Library filterDigitalBook(Genre genre);

    // Returns all print books.
    Library filterPrintBook();

    // Returns all print books by author.
    Library filterPrintBook(String author);

    // Returns all print books by genre.
    Library filterPrintBook(Genre genre);

    // Returns all books by author.
    Library filterBooks(String author);

    // Returns all books in genre.
    Library filterBooks(Genre genre);
}

interface BookSort {
    void sortByAuthor();

    void sortByTitle();
}

class User {
    private List<Book> borrowedBooks;
    private List<Book> downloadedBooks;
    private Library library;

    public User(Library library) {
        this.borrowedBooks = new ArrayList<>();
        this.downloadedBooks = new ArrayList<>();
        this.library = library;
    }

    public String toString() {
        return "" + borrowedBooks + "\n" + downloadedBooks;
    }

    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            borrowedBooks.remove(book);
            ((PrintBook) book).setAvailable(true);
        } else {
            System.out.println(book.getFormattedTitle() + " is not borrowed by this user.");
        }
    }

    public void checkoutBook(Book book) {
        if (book != null && library.hasBook(book)) {
            if (book instanceof DigitalBook) {
                this.downloadedBooks.add(book);
            } else {
                this.borrowedBooks.add(book);
                ((PrintBook) book).setAvailable(false);
            }
        } else {
            System.out.println("The book does not exist!");
        }
    }

    public List<Book> getDownloadedBooks() {
        return this.downloadedBooks;
    }

    public DigitalBook getDownloadedBook(int index) {
        if (index < this.downloadedBooks.size()) {
            return (DigitalBook) this.downloadedBooks.get(index);
        } else {
            System.out.println("Not in index!");
            return null;
        }
    }

    public List<Book> getBorrowedBooks() {
        return this.borrowedBooks;
    }

    public PrintBook getBorrowedBook(int index) {
        if (index < this.borrowedBooks.size()) {
            return (PrintBook) this.borrowedBooks.get(index);
        } else {
            System.out.println("Not in index!");
            return null;
        }
    }

    public void listDownloadedBooks() {
        String result = "";
        int count = 1;

        for (Book book : this.downloadedBooks) {
            result = result + count + ". " + book.getFormattedTitle() + "\n";
            count++;
        }

        System.out.println(result);
    }

    public void listBorrowedBooks() {
        String result = "";
        int count = 1;

        for (Book book : this.borrowedBooks) {
            result = result + count + ". " + book.getFormattedTitle() + "\n";
            count++;
        }

        System.out.println(result);
    }

    public Library getLibrary() {
        return this.library;
    }
}

public class Library implements BookFilter, BookSort {
    private List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public Library(ArrayList<Book> books) {
        this.books = books;
    }

    public Book getBook(int index) {
        if (index < books.size()) {
            return this.books.get(index);
        } else {
            System.out.println("Not in index!");
            return null;
        }
    }

    public Book getBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean hasBook(Book book) {
        return this.books.contains(book);
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public String toString() {
        String result = "";
        int count = 1;

        for (Book book : this.books) {
            result = result + count + ". " + book.getFormattedTitle() + "\n";
            count++;
        }

        return result;
    }

    // All methods below are for sorting / filtering.
    @Override
    public Library filterDigitalBook() {
        Library digitalBooks = new Library();

        for (Book book : books) {
            if (book instanceof DigitalBook) {
                digitalBooks.addBook(book);
            }
        }

        digitalBooks.sortByTitle();
        return digitalBooks;
    }

    @Override
    public Library filterDigitalBook(String author) {
        Library digitalBooks = new Library();

        for (Book book : this.books) {
            if (book instanceof DigitalBook && book.getAuthor().equals(author)) {
                digitalBooks.addBook(book);
            }
        }

        digitalBooks.sortByAuthor();
        return digitalBooks;
    }

    @Override
    public Library filterDigitalBook(Genre genre) {
        Library digitalBooks = new Library();

        for (Book book : this.books) {
            if (book instanceof DigitalBook && book.getGenre() == genre) {
                digitalBooks.addBook(book);
            }
        }

        digitalBooks.sortByTitle();
        return digitalBooks;
    }

    @Override
    public Library filterPrintBook() {
        Library printBooks = new Library();

        for (Book book : this.books) {
            if (book instanceof PrintBook) {
                printBooks.addBook(book);
            }
        }

        printBooks.sortByTitle();
        return printBooks;
    }

    @Override
    public Library filterPrintBook(String author) {
        Library printBooks = new Library();

        for (Book book : this.books) {
            if (book instanceof PrintBook && book.getAuthor().equals(author)) {
                printBooks.addBook(book);
            }
        }

        printBooks.sortByTitle();
        return printBooks;
    }

    @Override
    public Library filterPrintBook(Genre genre) {
        Library printBooks = new Library();

        for (Book book : this.books) {
            if (book instanceof PrintBook && book.getGenre() == genre) {
                printBooks.addBook(book);
            }
        }

        return printBooks;
    }

    @Override
    public Library filterBooks(String author) {
        Library books = new Library();

        for (Book book : this.books) {
            if (book.getAuthor().equals(author)) {
                books.addBook(book);
            }
        }

        books.sortByTitle();
        return books;
    }

    @Override
    public Library filterBooks(Genre genre) {
        Library books = new Library();

        for (Book book : this.books) {
            if (book.getGenre() == genre) {
                books.addBook(book);
            }
        }

        return books;
    }

    @Override
    public void sortByTitle() {
        Collections.sort(books, Book.byTitle);
    }

    @Override
    public void sortByAuthor() {
        Collections.sort(books, Book.byAuthor);
    }
}

enum Genre {
    FICTION,
    NON_FICTION,
    MYSTERY,
    FANTASY,
    SCIENCE_FICTION,
    ROMANCE,
    POETRY;
}

abstract class Book {
    protected String title;
    protected String author;
    protected Genre genre;
    protected int pageCount;

    public Book(String title, String author, Genre genre, int pageCount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.pageCount = pageCount;
    }

    static final Comparator<Book> byTitle = Comparator.comparing(Book::getTitle);
    static final Comparator<Book> byAuthor = Comparator.comparing(Book::getAuthor);
    static final Comparator<Book> byGenre = Comparator.comparing(Book::getGenre);

    public String toString() {
        return "Title: " + this.title + " By Author: " + this.author + " (Genre: " + this.genre + " )";
    }

    public String getFormattedTitle() {
        return this.title + " by " + this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public Genre getGenre() {
        return this.genre;
    }

    public void displayInfo() {
        System.out.println(
                "Title: " + this.title +
                        "\nAuthor: " + this.author +
                        "\nGenre: " + this.genre +
                        "\nPage Count: " + this.pageCount);
    }
}

enum Format {
    PDF,
    EPUB,
    AZW,
    DJVU,
    TXT,
    HTML,
    DOCX;
}

class DigitalBook extends Book {
    private Format format;
    private boolean canDownload;

    public DigitalBook(String title, String author, Genre genre, int pageCount, Format format) {
        super(title, author, genre, pageCount);
        this.format = format;
        this.canDownload = true;
    }

    public Format getFormat() {
        return this.format;
    }

    public boolean canDownload() {
        return this.canDownload;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(
                "Format: " + this.format +
                        "\nCan download: " + this.canDownload);
    }
}

enum CoverType {
    HARDCOVER,
    PAPERBACK,
    SPIRAL_BOUND,
    LEATHER_BOUND,
    MAGAZINE,
    GRAPHIC_NOVEL;
}

class PrintBook extends Book {
    private CoverType coverType;
    private boolean available;

    PrintBook(String title, String author, Genre genre, int pageCount, CoverType coverType) {
        super(title, author, genre, pageCount);
        this.coverType = coverType;
        this.available = true;
    }

    public CoverType getCoverType() {
        return this.coverType;
    }

    public boolean getAvailable() {
        return this.available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println(
                "Cover Type: " + this.coverType.name());
    };
}
