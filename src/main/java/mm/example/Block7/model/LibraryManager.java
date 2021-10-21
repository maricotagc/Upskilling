package mm.example.Block7.model;

import java.util.Objects;

//is this class necessary?

public class LibraryManager {
    private int bookId;
    private int libraryId;
    private int totalCopies;
    private int availableCopies;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(int libraryId) {
        this.libraryId = libraryId;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public void addBookToLibrary(Library library, Book book, int totalCopies, int availableCopies) {
        this.libraryId = library.getId();
        this.bookId = book.getId();
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    public void updateAvailableBooks(Book book, Library library, int availableCopies) {
        this.bookId = bookId;
        this.libraryId = libraryId;
        this.availableCopies = availableCopies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LibraryManager)) return false;
        LibraryManager that = (


                LibraryManager) o;
        return bookId == that.bookId && libraryId == that.libraryId && totalCopies == that.totalCopies && availableCopies == that.availableCopies;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, libraryId, totalCopies, availableCopies);

    }

    @Override
    public String toString() {
        return "LibraryManager{" +
                "bookId=" + bookId + ", libraryId=" + libraryId + ", totalCopies=" + totalCopies + ", availableCopies=" + availableCopies +
                '}';
    }
}
