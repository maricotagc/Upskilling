package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BookRepositoryTest extends AbstractBaseTest{

    @Before
    public void setUp() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        try {
            createTables(databaseManager.getConnection());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void addNewBook() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        Book book1 = new Book();
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");

        try {
            bookRepository.add(book1);
            Book actualBook = bookRepository.findById(1);
            Assert.assertEquals("Harry Potter and the Philosophers Stone", actualBook.getName());
            Assert.assertEquals("J.K. Rowling", actualBook.getAuthor());
            Assert.assertEquals(1, actualBook.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void removeBookByID() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        Book book1 = new Book();
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("J.K. Rowling");
        try {
            bookRepository.add(book1);
            Assert.assertEquals("Harry Potter and the Philosophers Stone", bookRepository.findById(1).getName());
            Assert.assertEquals("Book was successfully removed from book table.", bookRepository.remove(1));
            Assert.assertEquals(null, bookRepository.findById(1).getName());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void ShowAllBooksInShop() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("Harry Potter and the Philosophers Stone");
            book1.setAuthor("J.K. Rowling");
            bookRepository.add(book1);

            Book book2 = new Book();
            book2.setId(2);
            book2.setName("Harry Potter and the Chamber of Secrets");
            book2.setAuthor("J.K. Rowling");
            bookRepository.add(book2);

            List<Book> expected = new ArrayList<>();
            expected.add(book1);
            expected.add(book2);

            List<Book> actual = bookRepository.findAll();
            assertThat(expected).hasSameElementsAs(actual);

        } finally {
            databaseManager.closeConnection();
        }
    }

//    @Test
//    public void ShowAllAvailableBooksInShop() throws Exception {
//        DatabaseManager databaseManager = new DatabaseManager();
//        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
//        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());
//        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
//
//        try {
//            Library NycLibrary = new Library();
//            libraryRepository.add("NYC Public Library", "Park Avenue, 5. New York City. USA");
//
//            Book book1 = new Book();
//            book1.setId(1);
//            book1.setName("Harry Potter and the Philosophers Stone");
//            book1.setAuthor("J.K. Rowling");
//            bookRepository.add(book1);
//            libraryManagerRepository.addBookToLibrary(book1, NycLibrary, 25, 25);
//            libraryManagerRepository.updateAvailableBooks(book1, NycLibrary, 10);
//
//            Book book2 = new Book();
//            book2.setId(2);
//            book2.setName("Harry Potter and the Chamber of Secrets");
//            book2.setAuthor("J.K. Rowling");
//            bookRepository.add(book2);
//            libraryManagerRepository.addBookToLibrary(book2, NycLibrary, 60, 60);
//            libraryManagerRepository.updateAvailableBooks(book2, NycLibrary, 15);
//
//            Book book3 = new Book();
//            book3.setId(3);
//            book3.setName("Harry Potter and the Prisoner of Azkaban");
//            book3.setAuthor("J.K. Rowling");
//            bookRepository.add(book3);
//            libraryManagerRepository.addBookToLibrary(book3, NycLibrary, 150, 150);
//            libraryManagerRepository.updateAvailableBooks(book3, NycLibrary, 78);
//
//            List<Book> expected = new ArrayList<>();
//            expected.add(book1);
//            expected.add(book2);
//            expected.add(book3);
//
//            List<Book> actual = libraryManagerRepository
//            assertThat(expected).hasSameElementsAs(actual);
//
//        } finally {
//            databaseManager.closeConnection();
//        }
//    }

    @Test
    public void findBookById() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());

        try {
            Book book1 = new Book();
            book1.setId(1);
            book1.setName("Harry Potter and the Philosophers Stone");
            book1.setAuthor("J.K. Rowling");
            bookRepository.add(book1);

            Book book = bookRepository.findById(1);
            Assert.assertEquals(1, book.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

}