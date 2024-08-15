package org.example.library;

import org.example.person.Person;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Reader extends Person {
    Set<Book> books = new LinkedHashSet<>();

    public Reader(String name, Set<Book> books) {
        super(name);
        this.books = books;
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public void whoYouAre() {
        super.toString();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
