package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BookLibraryRepositoryTest extends AbstractBaseTest{
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
    public void addBookToLibrary() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(2);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        DatabaseManager databaseManager = new DatabaseManager();

        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getName(), library1.getAddress());
            int result = bookLibraryRepository.addBookToLibrary(1, 1, 60, 22);
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn5AfterUpdatingNumberOfAvailableCopies() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");book1.setAuthor("'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(2);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        DatabaseManager databaseManager = new DatabaseManager();

        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getName(), library1.getAddress());
            bookLibraryRepository.addBookToLibrary(1, 2, 99, 99);
            bookLibraryRepository.updateAvailableBooksById(1, library1, 5);
            Assert.assertEquals(5, bookLibraryRepository.showAvailableBooksById(1,2));
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturnListOfAvailableBooksInLibrary() throws Exception {
        addManyBooksToManyLibraries();

        DatabaseManager databaseManager = new DatabaseManager();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        Book book1 = new Book();
        book1.setId(1);
        book1.setAuthor("J.K. Rowling");
        book1.setName("Harry Potter and the Philosophers Stone");

        Book book2 = new Book();
        book2.setId(2);
        book2.setAuthor("J.K. Rowling");
        book2.setName("Harry Potter and the Chamber of Secrets");

        List<Book> er = new ArrayList<>();
        er.add(book1);
        er.add(book2);

        Assert.assertEquals(er, bookLibraryRepository.showAllAvailableBooksInLibrary(1));
    }

    @Test
    public void shouldReturn22AvailableBooksForBookIdAndLibraryId1() throws Exception {
        Book book1 = new Book();
        book1.setId(1);
        book1.setName("Harry Potter and the Philosophers Stone");
        book1.setAuthor("'J.K. Rowling");

        Library library1 = new Library();
        library1.setId(1);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        DatabaseManager databaseManager = new DatabaseManager();

        BookRepository bookRepository = new BookRepository(databaseManager.getConnection());
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getName(), library1.getAddress());
            bookLibraryRepository.addBookToLibrary(1, 1, 60, 22);
            int result = bookLibraryRepository.showAvailableBooksById(1, 1);
            Assert.assertEquals(22, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturnSuccessfulMessageAfterRentingOneBook() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        Assert.assertEquals("Book id 1 was successfully rented.", bookLibraryRepository.rentBookById(1,1));
    }

    @Test
    public void shouldReturn280RentedBooks() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        Assert.assertEquals(280, bookLibraryRepository.showTotalOfRentedBooks());
    }

    @Test
    public void shouldReturnSuccessfulMessageAfterBookRefund() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();
        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        Assert.assertEquals("Book id 1 was successfully refunded.", bookLibraryRepository.refundBookById(1,1));
    }

//    @Test
//    public void shouldReturnStringWithBooksAvailableAfterBookRefund() throws Exception {
//        addManyBooksToManyLibraries();
//
//        DatabaseManager databaseManager = new DatabaseManager();
//        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("Book ID: 1 Library ID: 1 Available Copies: 66");
//        stringBuilder.append("\n");
//        stringBuilder.append("Book ID: 2 Library ID: 1 Available Copies: 55");
//        stringBuilder.append("\n");
//
//        String er = stringBuilder.toString();
//
//        Assert.assertEquals("Book id 1 was successfully refunded.", bookLibraryRepository.refundBookById(1,1));
//        Assert.assertEquals(er, bookLibraryRepository.showAllAvailableBooksInLibrary(1));
//    }

    @Test
    public void shouldReturn0AvailableBooksAfterRentingAllBooks() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();

        Library library1 = new Library();
        library1.setId(1);
        library1.setName("Shanghai Library");
        library1.setAddress("1555 Huaihai Road, Xuhui District, Shanghai, China");

        BookLibraryRepository bookLibraryRepository = new BookLibraryRepository(databaseManager.getConnection());
        bookLibraryRepository.updateAvailableBooksById(1,library1,0);
        bookLibraryRepository.updateAvailableBooksById(2,library1,0);

        Assert.assertEquals(0, bookLibraryRepository.ShowNumberOfAllAvailableBooks());
    }



}