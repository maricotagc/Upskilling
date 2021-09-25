package mm.example.Block7.repository;

import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibraryRepositoryTest extends AbstractBaseTest {

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
    public void shouldReturn1ForLibrarySuccessfullyAdded() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());

        try {
            int result = libraryRepository.add("NEW YORK PUBLIC LIBRARY", "476 5th Ave, New York, NY 10018, United States");
            Library library = libraryRepository.findById(1);
            Assert.assertEquals(1, result);
            Assert.assertEquals("NEW YORK PUBLIC LIBRARY", library.getName());
            Assert.assertEquals("476 5th Ave, New York, NY 10018, United States", library.getAddress());
            Assert.assertEquals(1, library.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForLibrarySuccessfullyRemoved() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        LibraryRepository libraryRepository = new LibraryRepository(

                databaseManager.getConnection());
        try {
            libraryRepository.add("Shanghai Library", "1555 Huaihai Road, Xuhui District, Shanghai, China");
            int result = libraryRepository.remove(1);
            Assert.assertEquals(1, result);
        } finally {
            databaseManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForLibrarySuccessfullyUpdated() throws Exception {
        DatabaseManager databaseManager = new DatabaseManager();
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        Library library;
        try {
            libraryRepository.add("Library and Archives Canada", "395 Wellington St, Ottawa, ON K1A 0N4, Canada");
            library = libraryRepository.findById(1);
            int result = libraryRepository.update(1, "Library and Archives Canada", "385 Wellington St, Ottawa, ON K1A 0N4, Canada");
            Assert.assertEquals(1, result);
            Assert.assertEquals(1, library.getId());
        } finally {
            databaseManager.closeConnection();
        }
    }
}