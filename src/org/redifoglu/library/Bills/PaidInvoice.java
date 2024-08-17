package org.redifoglu.library.Bills;

import org.redifoglu.library.Book;
import org.redifoglu.person.Reader;

public class PaidInvoice extends Invoice{
    public PaidInvoice(Reader reader, Book book) {
        super(reader, book);
    }

    @Override
    public String toString() {
        return "Paid Invoice " + super.toString();
    }
}
