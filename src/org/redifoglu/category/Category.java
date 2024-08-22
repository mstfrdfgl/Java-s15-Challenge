package org.redifoglu.category;

import org.redifoglu.interfaces.Observer;
import org.redifoglu.library.Book;
import org.redifoglu.library.Library;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Category implements Observer {
    private int categoryID;
    private final String name;
    private final double price;
    private Map<String, Set<Book>> books = new LinkedHashMap<>();

    public Category(int categoryID, double price, String name) {
        this.categoryID = categoryID;
        this.price = price;
        this.name = name;
        Library.getInstance().addObservers(this);
        books.put(name, new LinkedHashSet<>());
    }

    public void update() {
        Set<Book> books = this.books.get(name);
        books.clear();
        for (Book book : Library.getInstance().getBooks().values()) {
            if (book.getCategory().equals(this)) {
                books.add(book);
            }
        }
    }

    public int getCategoryID() {
        return categoryID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Set<Book> getBooks() {
        return books.get(name);
    }

    public void removeBook(Book book) {
        books.get(name).remove(book);
    }

    @Override
    public String toString() {
        Set<Book> books = this.books.get(name);
        StringBuilder sb = new StringBuilder();
        sb.append(name).append(" kategorisinde ").append(books.size()).append(" adet kitap mevcut.");
        if (!books.isEmpty()) {
            sb.append(" Bu kitaplar: ");
            sb.append(books.stream()
                    .map(Book::getName)
                    .collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(books, category.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, books);
    }
}
