package org.example.sax;

import org.example.model.Author;
import org.example.model.Book;
import org.example.model.Library;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Apteev Artem
 * @Data 04.06.21
 */
public class LibraryBuilderHandler extends DefaultHandler {

    private LibraryBuilder builder;
    private boolean isCurrentElementTitle;

    private final String bookElement = "book";
    private final String titleElement = "title";

    @Override
    public void startDocument() {
        builder = new LibraryBuilder();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        final String authorElement = "author";
        switch (qName) {
            case bookElement:
                builder.createBook();
                break;
            case authorElement:
                builder.addAuthor(readAuthor(attributes));
                break;
            case titleElement:
                titleToggleOn();
                break;
            default:
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case bookElement:
                builder.addBookToList();
            case titleElement:
                titleToggleOff();
        }
    }

    private Author readAuthor(Attributes attributes) {
        return new Author(attributes.getValue("name"));
    }

    private void titleToggleOn() {
        isCurrentElementTitle = true;
    }

    private void titleToggleOff() {
        isCurrentElementTitle = false;
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (isCurrentElementTitle) {
            String title = String.copyValueOf(ch, start, length);
            builder.addTitle(title);
        }
    }

    public Library getLibrary() {
        return builder.build();
    }

    private static class LibraryBuilder {
        private final Set<Author> authorList = new HashSet<>();
        private final Set<Book> books = new HashSet<>();

        private Book.Builder bookBuilder;


        public void createBook() {
            bookBuilder = new Book.Builder();
        }

        public void addAuthor(Author author) {
            this.authorList.add(author);
            if (bookBuilder != null) {
                bookBuilder.addAuthor(author);
            }
        }

        public void addBookToList() {
            books.add(bookBuilder.build());
            bookBuilder = null;
        }

        public Library build() {
            Library library = new Library();
            library.setAuthors(authorList);
            library.setBooks(books);
            return library;
        }

        public void addTitle(String title) {
            if (bookBuilder != null) {
                bookBuilder.title(title);
            }
        }
    }
}
