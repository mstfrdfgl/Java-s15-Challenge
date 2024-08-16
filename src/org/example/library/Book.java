package org.example.library;

import org.example.author.Author;
import org.example.category.Category;
import org.example.enums.Cat;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Book {
    private int bookID;
    private Author author;
    private String name;
    private Category category;
    private double price;
//    private Map<Boolean, Integer> status = new HashMap<>();
    private int quantity;
    private String edition;
    private LocalDate dateOfPurchase;
    private LocalDate dateOfBorrowing;

    public Book(int bookID, Author author, String name, Category category,int quantity, String edition, LocalDate dateOfPurchase) {
        this.bookID = bookID;
        this.author = author;
        this.name = name;
        this.category = category;
        this.price = category.getPrice();
        this.quantity=quantity;
        this.edition = edition;
        this.dateOfPurchase = dateOfPurchase;

//        this.author.getBooks().add(this);//kitap nesnesi oluşturulduğunda otomatik olarak seçilen yazarın kitaplarına eklenir.
        Library library = Library.getInstance();
        library.addBook(this);//Aynı şekilde kitap nesnesi oluşturulduğunda otomatik olarak kitap kütüphaneye eklenir.
//        category.addBook(this);

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

    public LocalDate getDateOfBorrowing() {
        return dateOfBorrowing;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book ID: ").append(bookID).append("\n");
        sb.append("Author: ").append(author.getName()).append("\n");
        sb.append("Title: ").append(name).append("\n");
        sb.append("Category: ").append(category.getName()).append("\n");
        sb.append("Price: $").append(String.format("%.2f", price)).append("\n");
        sb.append("Available Copies: ").append(quantity).append("\n");
        sb.append("Edition: ").append(edition).append("\n");
        sb.append("Date of Purchase: ").append(dateOfPurchase).append("\n");
        return sb.toString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
