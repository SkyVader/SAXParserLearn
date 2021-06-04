package org.example.sax;

import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Library;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @Author Apteev Artem
 * @Data 04.06.2021
 */
public class LibraryLauncherTest {

    @Test
    public void library_is_launch() throws IOException {
        LibraryLauncher libraryLauncher = new LibraryLauncher("/library.xml");
        Library library = libraryLauncher.launch();
        Assertions.assertNotNull(library);
    }

    @Test
    public void checkSetAuthors() throws IOException {
        Set<Author> expectedAuthors = Stream.of(
                    "Erich Gamma",
                    "Richard Helm",
                    "Ralph Johnson",
                    "John Vlissides",
                    "Martin Fowler")
                .map(Author::new)
                .collect(Collectors.toSet());
        LibraryLauncher libraryLauncher = new LibraryLauncher("/library.xml");
        Library library = libraryLauncher.launch();

        Set<Author> authors = library.getAuthors();

        Assertions.assertEquals(expectedAuthors, authors);
    }

    @Test
    public void library_has_book_Patterns_of_Enterprise_Application_Architecture_book_by_Martin_Fowler() throws IOException {
        Book patternsOfEAA = new Book(
                "Patterns of Enterprise Application Architecture",
                Collections.singleton(new Author("Martin Fowler"))
        );
        LibraryLauncher libraryLauncher = new LibraryLauncher("/library.xml");
        Library library = libraryLauncher.launch();

        Set<Book> books = library.getBooks();

        Assertions.assertTrue(books.contains(patternsOfEAA));

    }
}