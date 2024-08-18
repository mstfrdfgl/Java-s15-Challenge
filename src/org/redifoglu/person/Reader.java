package org.redifoglu.person;

import org.redifoglu.library.Bills.ActiveInvoice;
import org.redifoglu.library.Bills.PaidInvoice;
import org.redifoglu.library.Book;
import org.redifoglu.library.BorrowedBook;
import org.redifoglu.library.Library;

import java.util.*;

public class Reader extends Person {
    private Set<BorrowedBook> borrowedBooks = new LinkedHashSet<>();
    private double balance;
    private List<ActiveInvoice> activeInvoices = new LinkedList<>();
    private List<PaidInvoice> paidInvoices = new LinkedList<>();

    public Reader(String name, double balance) {
        super(name);
        this.balance = balance;
        Library library = Library.getInstance();
        library.addReader(this);
    }

    public Set<BorrowedBook> getBorrowedBooks() {
        return borrowedBooks;
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

    public void borrowBook(Book... books) {
        for (Book book : books) {

            if (borrowedBooks.size() >= 5) {
                System.out.println("Aynı anda en fazla 5 adet kitap ödünç alabilirsiniz. Daha fazla kitap almak için lütfen elinizdeki kitapları tekrar kütüphanemize getirin.");
            } else if (borrowedBooks.contains(new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition()))) {
                System.out.println(book.getName() + " kitabını zaten ödünç almışsınız. Bir kez daha alamazsınız.");
            } else if (book.getQuantity() == 0) {
                System.out.println(getName() + "'nın ödünç almak istediği kitap kütüphanemizde kalmadı.");
            } else if (!Library.getInstance().getBooks().containsKey(book)) {
                System.out.println("Ödünç almak istediğiniz kitap kütüphanemizde bulunmamaktadır.");
            } else if (book.getQuantity() > 0) {
                if (balance >= book.getPrice()) {
                    borrowedBooks.add(new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition()));
                    int newCount = book.getQuantity() - 1;
                    book.setQuantity(newCount);
                    balance -= book.getPrice();
                    ActiveInvoice activeInvoice = new ActiveInvoice(this, book);
                    this.activeInvoices.add(activeInvoice);
                    if (borrowedBooks == null) {//eğer ilk defa kitap ödünç alıyorsa library readers mapinde yeni bir value(set) oluşturulur.
                        Library.getInstance().getReaders().put(this, new HashSet<>());
                    }
                    Library.getInstance().getReaders().get(this).add(new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition()));
                    System.out.println(getName() + ", " + book.getName() + " Kitabını ₺" + book.getPrice() + " karşılığında ödünç aldı. Yeni bakiye ₺" + String.format("%.2f", balance));
                } else {
                    System.out.println("Yetersiz bakiye. " + getName() + " maalesef ₺" + String.format("%.2f", book.getPrice() - balance) + " tutarında eksiğin var.");
                }
            }
        }
    }

    public void returnBook(Book... books) {
        for (Book book : books) {

            BorrowedBook borrowedBook = new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition());
            if (borrowedBooks.contains(borrowedBook)) {
                borrowedBooks.remove(borrowedBook);
                int newCount = book.getQuantity() + 1;
                book.setQuantity(newCount);
                balance += book.getPrice();
                activeInvoices.removeIf(invoice -> invoice.getBook().equals(book));
                PaidInvoice paidInvoice = new PaidInvoice(this, book);
                this.paidInvoices.add(paidInvoice);
                System.out.println(getName() + ", " + book.getName() + " kitabını iade etti. Teşekkür ederiz. Yeni bakiye ₺" + String.format("%.2f", balance));
            } else {
                System.out.println(getName() + " " + book.getName() + " kitabını görünüşe göre ödünç almamış.");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: ").append(getName());
        if (borrowedBooks.isEmpty()) {
            sb.append(" henüz kitap ödünç almamış.");
        } else {
            sb.append("'nın ödünç aldığı kitaplar. ");
            for (BorrowedBook borrowedBook : borrowedBooks) {
                sb.append(borrowedBook.getName()).append(",");
            }
        }
        return sb.toString();
    }
}
