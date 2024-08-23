package org.redifoglu.person;

import org.redifoglu.interfaces.Observer;
import org.redifoglu.library.Book;
import org.redifoglu.library.Library;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Author extends Person implements Observer {
    private int authorID;
    Set<Book> books = new HashSet<>();

    public Author(int authorID, String name) {
        super(name);
        this.authorID = authorID;
        Library.getInstance().addObservers(this);
    }

    public int getAuthorID() {
        return authorID;
    }

    public void update() {
        books.clear();
        for (Book book : Library.getInstance().getBooks().values()) {
            if (book.getAuthor().equals(this)) {
                books.add(book);
            }
        }
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorID == author.authorID;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(authorID);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" tarafından yazılmış ").append(books.size()).append(" adet kitap mevcut.");
        if (!books.isEmpty()) {
            sb.append(" Bu kitaplar: ");
            sb.append(books.stream()
                    .map(Book::getName)
                    .collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }

}
