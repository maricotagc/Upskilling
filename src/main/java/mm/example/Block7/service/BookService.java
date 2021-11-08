//layer for user to take actions.

package mm.example.Block7.service;

import mm.example.Block7.exception.BookException;
import mm.example.Block7.model.Book;
import mm.example.Block7.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public boolean createBook(Book book) throws BookException  {
        try {
            Book book1 = bookRepository.findByName(book.getName());
            if (book1 == null) {
                return bookRepository.create(book) > 0;
            }
        } catch (BookException e) {
            throw new BookException("It was not possible to create new book.");
        }
        return false;
    }

    public boolean removeBook(Book book) throws BookException  {
        try {
            if (bookRepository.existsBook(book.getId())) {
                return bookRepository.remove(book.getId()) > 0;
            }
        } catch (BookException e) {
            throw new BookException("It was not possible to remove book " + book.getName());
        }
        return false;
    }

    public void removeBook(int bookId) throws BookException  {
        Book book = new Book();
        book.setId(bookId);

        try {
            if (bookRepository.existsBook(book.getId())) {
                bookRepository.remove(book.getId());
            }
        } catch (BookException e) {
            throw new BookException("It was not possible to remove book " + book.getName());
        }
    }

    public Book findById(int bookId) throws BookException {
        try {
            return bookRepository.findById(bookId);
        } catch (BookException e) {
            throw new BookException("It was not possible to find book with id " + bookId);
        }
    }

    public Book findByName(String bookName) throws BookException {
        try {
            return bookRepository.findByName(bookName);
        } catch (BookException e) {
            throw new BookException("It was not possible to find book by name = " + bookName);
        }
    }
}
