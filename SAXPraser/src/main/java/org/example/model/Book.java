package org.example.model;

import java.util.HashSet;
import java.util.Set;

public class Book {

    private final String title;
    private final Set<Author> authors;

    public Book(String title, Set<Author> authors) {
        this.title = title;
        this.authors = authors;
    }

    public static class Builder {
        private String title;
        private Set<Author> authors = new HashSet<>();

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder addAuthor(Author author) {
            this.authors.add(author);
            return this;
        }

        public Book build() {
            return new Book(title, authors);
        }
    }

    public String getTitle() {
        return title;
    }

    public Set<Author> getAuthors() {
        return new HashSet<>(authors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!title.equals(book.title)) return false;
        return authors.equals(book.authors);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + authors.hashCode();
        return result;
    }
}
