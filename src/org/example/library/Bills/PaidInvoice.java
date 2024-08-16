package org.example.library.Bills;

import org.example.library.Book;
import org.example.person.Reader;

public class PaidInvoice extends Invoice{
    public PaidInvoice(Reader reader, Book book) {
        super(reader, book);
    }

    @Override
    public String toString() {
        return "Paid Invoice " + super.toString();
    }
}
