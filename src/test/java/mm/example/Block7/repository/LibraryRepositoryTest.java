package mm.example.Block7.repository;

import mm.example.Block7.model.Library;
import mm.example.Block7.utils.ConnectionManager;
import org.junit.Assert;
import org.junit.Test;

public class LibraryRepositoryTest {

    @Test
    public void shouldReturn1ForLibrarySuccessfullyAdded() throws Exception {
        ConnectionManager connectionManager = new ConnectionManager();
        LibraryRepository libraryRepository = new LibraryRepository(connectionManager.getConnection());

        try {
            libraryRepository.deleteAll();
            int result = libraryRepository.add(1, "NEW YORK PUBLIC LIBRARY", "476 5th Ave, New York, NY 10018, United States");
            Library library = libraryRepository.findById(1);
            Assert.assertEquals(1, result);
            Assert.assertEquals("NEW YORK PUBLIC LIBRARY", library.getName());
            Assert.assertEquals("476 5th Ave, New York, NY 10018, United States", library.getAddress());
            Assert.assertEquals(1, library.getId());
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForLibrarySuccessfullyRemoved() throws Exception {
        ConnectionManager connectionManager = new ConnectionManager();
        LibraryRepository libraryRepository = new LibraryRepository(connectionManager.getConnection());
        try {
            libraryRepository.deleteAll();
            libraryRepository.add(2, "Shanghai Library", "1555 Huaihai Road, Xuhui District, Shanghai, China");
            int result = libraryRepository.remove(2);
            Assert.assertEquals(1, result);
        } finally {
            connectionManager.closeConnection();
        }
    }

    @Test
    public void shouldReturn1ForLibrarySuccessfullyUpdated() throws Exception {
        ConnectionManager connectionManager = new ConnectionManager();
        LibraryRepository libraryRepository = new LibraryRepository(connectionManager.getConnection());
        libraryRepository.deleteAll();
        Library library;
        try {
            libraryRepository.add(3, "Library and Archives Canada", "395 Wellington St, Ottawa, ON K1A 0N4, Canada");
            library = libraryRepository.findById(3);
            int result = libraryRepository.update(3, "Library and Archives Canada", "385 Wellington St, Ottawa, ON K1A 0N4, Canada");
            Assert.assertEquals(1, result);
            Assert.assertEquals(3, library.getId());
        } finally {
            connectionManager.closeConnection();
        }
    }
}