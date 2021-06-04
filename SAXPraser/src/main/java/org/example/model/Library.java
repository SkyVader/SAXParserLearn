package org.example.model;

import java.util.HashSet;
import java.util.Set;

public class Library {
    private Set<Author> authors = new HashSet<>();
    private Set<Book> books = new HashSet<>();

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
