package org.redifoglu.library.Bills;

import org.redifoglu.library.Book;
import org.redifoglu.person.Reader;

public class ActiveInvoice extends Invoice{
    public ActiveInvoice(Reader reader, Book book) {
        super(reader, book);
    }

    @Override
    public String toString() {
        return "Active Invoice " + super.toString();
    }
}
