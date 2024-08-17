package org.redifoglu.library;

import org.redifoglu.person.Author;
import org.redifoglu.category.Category;

import java.util.Objects;

public class BorrowedBook {
    private String name;
    private Author author;
    private Category category;
    private String edition;
    private double price;

    public BorrowedBook(String name, Author author, Category category, String edition) {
        this.name = name;
        this.author = author;
        this.category = category;
        this.edition = edition;
        this.price = category.getPrice();
    }

    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;
    }

    public Category getCategory() {
        return category;
    }

    public String getEdition() {
        return edition;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
//        sb.append("BorrowedBook Details:\n");
        sb.append(name).append("");
//        sb.append("Author: ").append(author.getName()).append("\n");
//        sb.append("Category: ").append(category.getName()).append("\n");
//        sb.append("Edition: ").append(edition).append("\n");
//        sb.append("Price: $").append(String.format("%.2f", price)).append("\n");
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BorrowedBook that = (BorrowedBook) o;
        return Objects.equals(name, that.name) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, author);
    }
}
