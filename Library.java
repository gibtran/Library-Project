import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

interface BookFilter {
    // Returns a list of all digital books.
    List<Book> filterDigitalBook();

    // Returns a list of all digital books by author.
    List<Book> filterDigitalBook(String author);

    // Returns a list of all digital books by genre.
    List<Book> filterDigitalBook(Genre genre);

    // Returns a list of all print books.
    List<Book> filterPrintBook();

    // Returns a list of all print books by author.
    List<Book> filterPrintBook(String author);

    // Returns a list of all print books by genre.
    List<Book> filterPrintBook(Genre genre);
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
        if (library.hasBook(book)) { // should check in borrowedBooks (downloadBooks.contains(book))
            borrowedBooks.remove(book);
            ((PrintBook) book).setAvailable(true);
        } else {
            System.out.println(book.getFormattedTitle() + " is not from this library.");
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
            System.out.println("The book is not exist");
        }
    }

    public List<Book> getDownloadedBook() { // should be added 's' to show this is list
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

    public List<Book> getBorrowedBook() {
        return this.borrowedBooks; // should be added 's' to show this is list
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

public class Library implements BookFilter {
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

    public boolean hasBook(Book book) {
        return this.books.contains(book);
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

    // searching book by tittle
    Book findBook(String title) {
        for (Book book : books) {
            if (book.getTitle().trim().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // All methods below are for sorting.
    public List<Book> filterDigitalBook() {
        ArrayList<Book> digitalBooks = new ArrayList<>();

        for (Book book : books) {
            if (book instanceof DigitalBook) {
                digitalBooks.add(book);
            }
        }

        digitalBooks.sort(Book.comparator);
        return digitalBooks;
    }

    public List<Book> filterDigitalBook(String author) {
        ArrayList<Book> digitalBooks = new ArrayList<>();

        for (Book book : this.books) {
            if (book instanceof DigitalBook && book.getAuthor().equals(author)) {
                digitalBooks.add(book);
            }
        }

        digitalBooks.sort(Book.comparator);
        return digitalBooks;
    }

    public List<Book> filterDigitalBook(Genre genre) {
        ArrayList<Book> digitalBooks = new ArrayList<>();

        for (Book book : this.books) {
            if (book instanceof DigitalBook && book.getGenre() == genre) {
                digitalBooks.add(book);
            }
        }

        digitalBooks.sort(Book.comparator);
        return digitalBooks;
    }

    public List<Book> filterPrintBook() {
        ArrayList<Book> printBooks = new ArrayList<>();

        for (Book book : this.books) {
            if (book instanceof PrintBook) {
                printBooks.add(book);
            }
        }

        printBooks.sort(Book.comparator);
        return printBooks;
    }

    public List<Book> filterPrintBook(String author) {
        ArrayList<Book> printBooks = new ArrayList<>();

        for (Book book : this.books) {
            if (book instanceof PrintBook && book.getAuthor().equals(author)) {
                printBooks.add(book);
            }
        }

        printBooks.sort(Book.comparator);
        return printBooks;
    }

    public List<Book> filterPrintBook(Genre genre) {
        ArrayList<Book> printBooks = new ArrayList<>();

        for (Book book : this.books) {
            if (book instanceof PrintBook && book.getGenre() == genre) {
                printBooks.add(book);
            }
        }

        printBooks.sort(Book.comparator);
        return printBooks;
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

    static final Comparator<Book> comparator = Comparator.comparing(Book::getTitle);

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

    public void changeTitle(String title) {
        this.title = title;
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
