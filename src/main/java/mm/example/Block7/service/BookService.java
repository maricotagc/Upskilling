package mm.example.Block7.service;

import mm.example.Block7.exception.BookException;
import mm.example.Block7.model.Book;
import mm.example.Block7.repository.BookRepository;

public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createBook(String bookName, String author) throws BookException  {
        Book book = new Book();
        book.setName(bookName);
        book.setAuthor(author);

        try {
            bookRepository.add(book);
        } catch (BookException e) {
            throw new BookException("It was not possible to create new book.");
        }
    }
}
