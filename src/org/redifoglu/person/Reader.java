package org.redifoglu.person;

import org.redifoglu.library.Bills.ActiveInvoice;
import org.redifoglu.library.Bills.PaidInvoice;
import org.redifoglu.library.BorrowedBook;

import java.util.*;

public class Reader extends Person {
    private int readerID;
    private double balance;
    private Set<BorrowedBook> borrowedBooks = new LinkedHashSet<>();
    private List<ActiveInvoice> activeInvoices = new LinkedList<>();
    private List<PaidInvoice> paidInvoices = new LinkedList<>();

    public Reader(int readerID, String name, double balance) {
        super(name);
        this.readerID = readerID;
        this.balance = balance;
    }

    public int getReaderID() {
        return readerID;
    }

    public Set<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
    }

    public double getBalance() {

        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<ActiveInvoice> getActiveInvoices() {
        return activeInvoices;
    }

    public List<PaidInvoice> getPaidInvoices() {
        return paidInvoices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName());
        if (borrowedBooks.isEmpty()) {
            sb.append(" henüz kitap ödünç almamış.");
        } else {
            sb.append(" ");
        }
        return sb.toString();
    }
}
