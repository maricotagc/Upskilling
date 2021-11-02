//layer for user to take actions.

package mm.example.Block7.service;

import mm.example.Block7.exception.LibraryException;
import mm.example.Block7.model.Library;
import mm.example.Block7.repository.LibraryRepository;

public class LibraryService {

    private LibraryRepository libraryRepository;

    public LibraryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public void createLibrary(Library library) throws LibraryException {
        try {
            if (!libraryRepository.findById(library.getId())) {
                libraryRepository.create(library.getName(), library.getAddress());
            }
        } catch (LibraryException e) {
            throw new LibraryException("It was not possible to create new library.");
        }
    }

    public void createLibrary(int libraryId) throws LibraryException  {
        Library library = new Library();
        library.setId(libraryId);

        createLibrary(library);
    }

    public void removeLibrary(Library library) throws LibraryException  {

        try {
            if (libraryRepository.findById(library.getId())) {
                libraryRepository.remove(library.getId());
            }
        } catch (LibraryException e) {
            throw new LibraryException("It was not possible to remove library " + library.getName());
        }
    }

    public void removeLibrary(int libraryId) throws LibraryException  {
        Library library = new Library();
        library.setId(libraryId);

        try {
            if (libraryRepository.findById(library.getId())) {
                libraryRepository.remove(library.getId());
            }
        } catch (LibraryException e) {
            throw new LibraryException("It was not possible to remove library " + library.getName());
        }
    }
}
