package org.example.category;

import org.example.interfaces.Observer;
import org.example.library.Book;
import org.example.library.Library;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Category implements Observer {
    private final String name;
    private final double price;
    private Set<Book> books = new LinkedHashSet<>();

    public Category(double price, String name) {
        this.price = price;
        this.name = name;
        Library.getInstance().addObservers(this);
    }

    public void update() {
        books.clear();
        for (Book book : Library.getInstance().getBooks().keySet()) {
            if (book.getCategory().equals(this)) {
                books.add(book);
            }
        }
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void removeBook(Book book) {
        books.remove(book);
    }

    public void addBook(Book book) {

        for (Book existingBook : books) {
            if (existingBook.getName().equalsIgnoreCase(book.getName())) {
                return;
            }
            if (existingBook.getBookID() == book.getBookID()) {
                return;
            }
        }
        int count = book.getQuantity();
        if (count <= 0) {
            return;
        }
        books.add(book);
    }

    @Override
    public String toString() {
        return name + " Kategorisinde " + books.size() + " adet kitap mevcut. Bu kitaplar " + books.stream()
                .map(Book::getName)
                .collect(Collectors.joining(", "));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(books, category.books);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(books);
    }
}
