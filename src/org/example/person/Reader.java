package org.example.person;

import org.example.library.Bills.ActiveInvoice;
import org.example.library.Bills.PaidInvoice;
import org.example.library.Book;
import org.example.library.BorrowedBook;

import java.util.*;

public class Reader extends Person {
    private Set<BorrowedBook> books = new LinkedHashSet<>();
    private double balance;
    private List<ActiveInvoice> activeInvoices=new LinkedList<>();
    private List<PaidInvoice> paidInvoices=new LinkedList<>();

    public Reader(String name, double balance) {
        super(name);
        this.balance = balance;
    }

    public Set<BorrowedBook> getBooks() {
        return books;
    }

    public double getBalance() {
        return balance;
    }

    public List<ActiveInvoice> getActiveInvoices() {
        return activeInvoices;
    }

    public List<PaidInvoice> getPaidInvoices() {
        return paidInvoices;
    }

    @Override
    public void whoYouAre() {
        super.toString();
    }

    public void borrowBook(Book book) {
        if (books.size() >= 5) {
            System.out.println("Aynı anda en fazla 5 adet kitap ödünç alabilirsiniz. Daha fazla kitap almak için lütfen elinizdeki kitapları tekrar kütüphanemize getirin.");
        } else if (books.contains(new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition()))) {
            System.out.println(book.getName() + " kitabını zaten ödünç almışsınız. Bir kez daha alamazsınız.");
        } else if (book.getQuantity() == 0) {
            System.out.println(getName() + "'nın ödünç almak istediği kitap kütüphanemizde kalmadı.");
        } else if (book.getQuantity() > 0) {
            if (balance >= book.getPrice()) {
                books.add(new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition()));
                int newCount = book.getQuantity() - 1;
                book.setQuantity(newCount);
                balance-=book.getPrice();
                ActiveInvoice activeInvoice=new ActiveInvoice(this,book);
                this.activeInvoices.add(activeInvoice);
                System.out.println(getName() + ", " + book.getName() + " Kitabını " + book.getPrice() + " karşılığında ödünç aldı. Yeni bakiye " + String.format("%.2f",balance));
            }else{
                System.out.println("Yetersiz bakiye. " + String.format("%.2f",book.getPrice()-balance) + " tutarında eksiğiniz var.");
            }
        }
    }

    public void returnBook(Book book) {
        BorrowedBook borrowedBook = new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition());
        if (books.contains(borrowedBook)) {
            books.remove(borrowedBook);
            int newCount = book.getQuantity() + 1;
            book.setQuantity(newCount);
            balance+=book.getPrice();
            activeInvoices.removeIf(invoice ->invoice.getBook().equals(book));
            PaidInvoice paidInvoice=new PaidInvoice(this,book);
            this.paidInvoices.add(paidInvoice);
            System.out.println(getName() + ", " + book.getName() + " kitabını iade etti. Teşekkür ederiz.");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
