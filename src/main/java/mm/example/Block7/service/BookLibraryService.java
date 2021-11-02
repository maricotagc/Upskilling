//layer for user to take actions. Contains logic
package mm.example.Block7.service;

import mm.example.Block7.exception.BookLibraryException;
import mm.example.Block7.exception.BookLibraryRepositoryException;
import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.repository.BookLibraryRepository;

public class BookLibraryService {

    private BookLibraryRepository bookLibraryRepository;

    public BookLibraryService(BookLibraryRepository bookLibraryRepository) {
        this.bookLibraryRepository = bookLibraryRepository;
    }

    public void rentBook(Book book, Library library) throws BookLibraryException {
        try {
            int availableCopies = bookLibraryRepository.findAvailableCopies(book.getId(), library.getId());
            if (availableCopies == 0) {
                throw new BookLibraryException("There are no available copies for renting. Book: " + book.getName());
            }
            bookLibraryRepository.updateAvailableCopies(book.getId(), library.getId(), availableCopies - 1);
        } catch (BookLibraryRepositoryException e) {
            throw new BookLibraryException("It was not possible to rent the book " + book.getName() + " from the library " + library.getName());
        }
    }

    public void rentBook(int bookId, int libraryId) throws BookLibraryException {
        Book book = new Book();
        book.setId(bookId);
        Library library = new Library();
        library.setId(libraryId);

        rentBook(book, library);
    }

    public void returnBook(Book book, Library library) throws Exception {
            int availableCopies = bookLibraryRepository.findAvailableCopies(book.getId(), library.getId());
            bookLibraryRepository.updateAvailableCopies(book.getId(), library.getId(), availableCopies + 1);
    }

    public void returnBook(int bookId, int libraryId) throws Exception {
        Book book = new Book();
        book.setId(bookId);

        Library library = new Library();
        library.setId(libraryId);

        returnBook(book, library);
    }
}
