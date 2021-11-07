package mm.example.Block7.repository;

import mm.example.Block7.AbstractBaseTest;
import mm.example.Block7.exception.LibraryException;
import mm.example.Block7.model.Library;
import mm.example.Block7.utils.DatabaseManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LibraryRepositoryTest extends AbstractBaseTest {

    DatabaseManager databaseManager;

    @Before
    public void setUp() throws Exception {
        databaseManager = new DatabaseManager();
        createTables(databaseManager.getConnection());
    }

    public void createTestData() throws Exception {
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());

        Library library1 = new Library();
        library1.setName("LIBRARY_A");
        library1.setAddress("ADDRESS_A");

        Library library2 = new Library();
        library2.setName("LIBRARY_B");
        library2.setAddress("ADDRESS_B");

        try {
            libraryRepository.create(library1.getName(), library1.getAddress());
            libraryRepository.create(library2.getName(), library2.getAddress());
        } catch (Exception e) {
            throw new Exception("It was not possible to create library.", e);
        }
    }

    public void removeTestData() {
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        try {
            libraryRepository.remove(1);
            libraryRepository.remove(2);
        } catch (LibraryException e) {
            e.getMessage();
        }
    }

    @Test
    public void shouldCreateLibrary() throws Exception {
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        Assert.assertEquals(1, libraryRepository.create("LIBRARY_C", "ADDRESS_C"));
        Assert.assertEquals("LIBRARY_C", libraryRepository.find(1).getName());
        Assert.assertEquals("ADDRESS_C", libraryRepository.find(1).getAddress());
        Assert.assertEquals(1, libraryRepository.find(1).getId());
    }

    @Test
    public void shouldRemoveLibrary() throws Exception {
        createTestData();
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        Assert.assertEquals(1, libraryRepository.remove(1));
        removeTestData();
    }

    @Test
    public void shouldUpdateLibrary() throws Exception {
        LibraryRepository libraryRepository = new LibraryRepository(databaseManager.getConnection());
        createTestData();
        Assert.assertEquals("LIBRARY_A", libraryRepository.find(1).getName());
        Assert.assertEquals(1, libraryRepository.update(1, "LIBRARY_UPDATE", "ADDRESS_UPDATE"));
        Assert.assertEquals("LIBRARY_UPDATE", libraryRepository.find(1).getName());
    }

    @After
    public void tearDown() {
        databaseManager.closeConnection();
    }
}