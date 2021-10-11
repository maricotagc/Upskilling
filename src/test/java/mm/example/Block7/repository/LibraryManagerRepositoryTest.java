package mm.example.Block7.repository;

import mm.example.Block7.model.Book;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibraryManagerRepositoryTest extends AbstractBaseTest{
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
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getName(), library1.getAddress());
            int result = libraryManagerRepository.addBookToLibrary(1, 1, 60, 22);
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void updateAvailableBooks() throws Exception {
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
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getName(), library1.getAddress());
            libraryManagerRepository.addBookToLibrary(1, 2, 99, 99);
            libraryManagerRepository.updateAvailableBooksById(1, library1, 5);
            Assert.assertEquals(5, libraryManagerRepository.showAvailableBooksById(1,2));
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void showAllAvailableBooks() throws Exception {
        addManyBooksToManyLibraries();

        DatabaseManager databaseManager = new DatabaseManager();
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Book ID: 1 Library ID: 1 Available Copies: 65");
        stringBuilder.append("\n");
        stringBuilder.append("Book ID: 2 Library ID: 1 Available Copies: 55");
        stringBuilder.append("\n");

        String er = stringBuilder.toString();

        Assert.assertEquals(er, libraryManagerRepository.showAllAvailableBooks());
    }

    @Test
    public void showAvailableBooksById() throws Exception {
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
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        try {
            bookRepository.add(book1);
            libraryRepository.add(library1.getName(), library1.getAddress());
            libraryManagerRepository.addBookToLibrary(1, 1, 60, 22);
            int result = libraryManagerRepository.showAvailableBooksById(1, 1);
            Assert.assertEquals(22, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void rentBookById() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());
        Assert.assertEquals("Book id 1 was successfully rented.", libraryManagerRepository.rentBookById(1,1));
    }

    @Test
    public void showTotalOfRentedBooks() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());
        Assert.assertEquals(280, libraryManagerRepository.showTotalOfRentedBooks());
    }

    @Test
    public void refundBookById() throws Exception {
        addManyBooksToManyLibraries();
        DatabaseManager databaseManager = new DatabaseManager();
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());
        Assert.assertEquals("Book id 1 was successfully refunded.", libraryManagerRepository.refundBookById(1,1));
    }

    @Test
    public void showBooksAfterRefundBook() throws Exception {
        addManyBooksToManyLibraries();

        DatabaseManager databaseManager = new DatabaseManager();
        LibraryManagerRepository libraryManagerRepository = new LibraryManagerRepository(databaseManager.getConnection());

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Book ID: 1 Library ID: 1 Available Copies: 66");
        stringBuilder.append("\n");
        stringBuilder.append("Book ID: 2 Library ID: 1 Available Copies: 55");
        stringBuilder.append("\n");

        String er = stringBuilder.toString();

        Assert.assertEquals("Book id 1 was successfully refunded.", libraryManagerRepository.refundBookById(1,1));
        Assert.assertEquals(er, libraryManagerRepository.showAllAvailableBooks());
    }

}