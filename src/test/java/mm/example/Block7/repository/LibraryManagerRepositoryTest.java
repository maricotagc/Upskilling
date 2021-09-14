package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.ConnectionManager;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LibraryManagerRepositoryTest {

    @Test
    public void addBookToLibrary() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(2);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        ConnectionManager connectionManager = new ConnectionManager();

        BookRepository bookRepository = new BookRepository(connectionManager.getConnection());
        bookRepository.deleteAll();
        LibraryRepository libraryRepository = new LibraryRepository(connectionManager.getConnection());
        libraryRepository.deleteAll();
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(connectionManager.getConnection());
        libraryManagerRepository.deleteAll();

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getId(), library1.getName(), library1.getAddress());
            int result = libraryManagerRepository.addBookToLibrary(book1, library1, 60, 22);
            Assert.assertEquals(1, result);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Test
    public void updateAvailableBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(2);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        ConnectionManager connectionManager = new ConnectionManager();

        BookRepository bookRepository = new BookRepository(connectionManager.getConnection());
        bookRepository.deleteAll();
        LibraryRepository libraryRepository = new LibraryRepository(connectionManager.getConnection());
        libraryRepository.deleteAll();
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(connectionManager.getConnection());
        libraryManagerRepository.deleteAll();

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getId(), library1.getName(), library1.getAddress());
            libraryManagerRepository.addBookToLibrary(book1, library1, 99, 99);
            int result = libraryManagerRepository.updateAvailableBooks(book1, library1, 3);
            Assert.assertEquals(1, result);
        } finally {
            connectionManager.closeConnection();
        }
    }
}