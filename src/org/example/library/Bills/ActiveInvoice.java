package org.example.library.Bills;

import org.example.library.Book;
import org.example.person.Reader;

import java.time.LocalDate;

public class ActiveInvoice extends Invoice{
    public ActiveInvoice(Reader reader, Book book) {
        super(reader, book);
    }

    @Override
    public String toString() {
        return "Active Invoice " + super.toString();
    }
}
