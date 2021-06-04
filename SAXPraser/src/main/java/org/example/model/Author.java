package org.example.model;

import java.util.Objects;

public class Author {

    public Author(String name) {
        this.name = Objects.requireNonNull(name);
    }

    private final String name;

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        if (name == null && author.name == null) return true;

        return name != null && name.equals(author.name);
    }

    @Override
    public int hashCode() {
        return name == null ? 0 : name.hashCode();
    }
}

