package org.redifoglu.library;

import org.redifoglu.person.Author;
import org.redifoglu.category.Category;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private int bookID;
    private Author author;
    private String name;
    private Category category;
    private double price;
    private int quantity;
    private String edition;
    private LocalDate dateOfPurchase;

    public Book(int bookID, Author author, String name, Category category, int quantity, String edition, LocalDate dateOfPurchase) {
        this.bookID = bookID;
        this.author = author;
        this.name = name;
        this.category = category;
        this.price = category.getPrice();
        this.quantity = quantity;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;
    }


    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getBookID() {
        return bookID;
    }

    public Category getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public String getEdition() {
        return edition;
    }

    public LocalDate getDateOfPurchase() {
        return dateOfPurchase;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected void setAuthor(Author author) {
        this.author = author;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected void setCategory(Category category) {
        this.category = category;
    }

    protected void setPrice(double price) {
        this.price = price;
    }

    protected void setEdition(String edition) {
        this.edition = edition;
    }

    protected void setDateOfPurchase(LocalDate dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    @Override
    public String toString() {
        if (Library.getInstance().getBooks().containsValue(this)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Book ID: ").append(bookID).append("\n");
            sb.append("Author: ").append(author.getName()).append("\n");
            sb.append("Title: ").append(name).append("\n");
            sb.append("Category: ").append(category.getName()).append("\n");
            sb.append("Price: ₺").append(String.format("%.2f", price)).append("\n");
            sb.append("Available Copies: ").append(quantity).append("\n");
            sb.append("Edition: ").append(edition).append("\n");
            sb.append("Date of Purchase: ").append(dateOfPurchase).append("\n");
            return sb.toString();
        } else {
            return "Bu kitap kütüphanemizde bulunmamakta.";
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name) && Objects.equals(edition, book.edition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, edition);
    }
}
