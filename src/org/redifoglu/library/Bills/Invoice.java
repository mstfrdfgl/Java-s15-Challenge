package org.redifoglu.library.Bills;

import org.redifoglu.library.Book;
import org.redifoglu.person.Reader;

import java.time.LocalDate;

public abstract class Invoice {
    private Reader reader;
    private Book book;
    private double amount;
    private LocalDate date;

    public Invoice(Reader reader, Book book) {
        this.reader = reader;
        this.book = book;
        this.amount = book.getPrice();
        this.date = LocalDate.now();
    }

    public Reader getReader() {
        return reader;
    }

    public Book getBook() {
        return book;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("reader=").append(reader.getName()).append(", ");
        sb.append("book=").append(book.getName()).append(", ");
        sb.append("amount=").append(String.format("%.2f", amount)).append(", ");
        sb.append("date=").append(date).append("\n");
        return sb.toString();
    }

}
