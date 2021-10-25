package mm.example.Block7.service;

import mm.example.Block7.exception.BookException;
import mm.example.Block7.model.Book;
import mm.example.Block7.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(Book book) throws BookException  {

        try {
            if (!bookRepository.findBookById(book.getId())) {
                bookRepository.create(book);
            }
        } catch (BookException e) {
            throw new BookException("It was not possible to create new book.");
        }
    }

    public void createBook(int bookId) throws BookException  {
        Book book = new Book();
        book.setId(bookId);

        createBook(book);
    }

    public void removeBook(Book book) throws BookException  {

        try {
            if (bookRepository.findBookById(book.getId())) {
                bookRepository.remove(book.getId());
            }
        } catch (BookException e) {
            throw new BookException("It was not possible to remove book " + book.getName());
        }
    }

    public void removeBook(int bookId) throws BookException  {
        Book book = new Book();
        book.setId(bookId);

        try {
            if (bookRepository.findBookById(book.getId())) {
                bookRepository.remove(book.getId());
            }
        } catch (BookException e) {
            throw new BookException("It was not possible to remove book " + book.getName());
        }
    }
}
