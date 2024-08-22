package org.redifoglu.person;

import org.redifoglu.library.Bills.ActiveInvoice;
import org.redifoglu.library.Bills.PaidInvoice;
import org.redifoglu.library.Book;
import org.redifoglu.library.BorrowedBook;
import org.redifoglu.library.Library;

import java.util.HashSet;

public class Librarian extends Person {
    private int librarianID;

    public Librarian(int librarianID, String name) {
        super(name);
        this.librarianID = librarianID;
    }

    public Librarian(String name) {
        super(name);
    }

    public int getLibrarianID() {
        return librarianID;
    }

    @Override
    public String getName() {
        return super.getName();
    }


//    public void lendBook(Reader reader, Book... books) {
//        for (Book book : books) {
//            BorrowedBook borrowedBook = new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition());
//            if (reader.getBorrowedBooks().size() >= 5) {
//                System.out.println("Aynı anda en fazla 5 adet kitap ödünç alabilirsiniz. Daha fazla kitap almak için lütfen elinizdeki kitapları tekrar kütüphanemize getirin.");
//                return;
//            }
//            if (reader.getBorrowedBooks().contains(borrowedBook)) {
//                System.out.println(book.getName() + " kitabını zaten ödünç almışsınız. Bir kez daha alamazsınız.");
//                continue;
//            }
//            if (book.getQuantity() == 0) {
//                System.out.println(reader.getName() + "'nın ödünç almak istediği kitap kütüphanemizde kalmadı.");
//                continue;
//            }
//            if (!Library.getInstance().getBooks().containsKey(book.getBookID())) {
//                System.out.println("Ödünç almak istediğiniz kitap kütüphanemizde bulunmamaktadır.");
//                continue;
//            }
//            if (reader.getBalance() < book.getPrice()) {
//                System.out.println("Yetersiz bakiye. " + reader.getName() + " maalesef ₺" + String.format("%.2f", book.getPrice() - reader.getBalance()) + " tutarında eksiğin var.");
//                continue;
//            }
//            //tüm koşullar sağlanırsa kitap ödünç alma işlemi başlar
//            reader.getBorrowedBooks().add(borrowedBook);
//            book.setQuantity(book.getQuantity() - 1);
//            reader.setBalance(reader.getBalance() - book.getPrice());
//            ActiveInvoice activeInvoice = new ActiveInvoice(reader, book);
//            reader.getActiveInvoices().add(activeInvoice);
//            if (reader.getBorrowedBooks() == null) {//eğer ilk defa kitap ödünç alıyorsa library readers mapinde yeni bir value(set) oluşturulur.
//                Library.getInstance().getReaders().put(reader, new HashSet<>());
//            }
//            Library.getInstance().getReaders().get(reader).add(borrowedBook);
//            System.out.println(reader.getName() + ", " + book.getName() + " Kitabını ₺" + book.getPrice() + " karşılığında ödünç aldı, yeni miktar " + book.getQuantity() + ". Yeni bakiye ₺" + String.format("%.2f", reader.getBalance()));
//        }
//    }
    public void lendBook(Reader reader, Book... books) {
        for (Book book : books) {
            BorrowedBook borrowedBook = new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition());
            if (reader.getBorrowedBooks().size() >= 5) {
                System.out.println("Aynı anda en fazla 5 adet kitap ödünç alabilirsiniz. Daha fazla kitap almak için lütfen elinizdeki kitapları tekrar kütüphanemize getirin.");
                return;
            }
            if (reader.getBorrowedBooks().contains(borrowedBook)) {
                System.out.println(book.getName() + " kitabını zaten ödünç almışsınız. Bir kez daha alamazsınız.");
                continue;
            }
            if (book.getQuantity() == 0) {
                System.out.println(reader.getName() + "'nın ödünç almak istediği kitap kütüphanemizde kalmadı.");
                continue;
            }
            if (!Library.getInstance().getBooks().containsKey(book.getBookID())) {
                System.out.println("Ödünç almak istediğiniz kitap kütüphanemizde bulunmamaktadır.");
                continue;
            }
            if (reader.getBalance() < book.getPrice()) {
                System.out.println("Yetersiz bakiye. " + reader.getName() + " maalesef ₺" + String.format("%.2f", book.getPrice() - reader.getBalance()) + " tutarında eksiğin var.");
                continue;
            }
            //tüm koşullar sağlanırsa kitap ödünç alma işlemi başlar
            reader.getBorrowedBooks().add(borrowedBook);
            book.setQuantity(book.getQuantity() - 1);
            reader.setBalance(reader.getBalance() - book.getPrice());
            ActiveInvoice activeInvoice = new ActiveInvoice(reader, book);
            reader.getActiveInvoices().add(activeInvoice);
            if (reader.getBorrowedBooks() == null) {//eğer ilk defa kitap ödünç alıyorsa library readers mapinde yeni bir value(set) oluşturulur.
                Library.getInstance().getReaders().put(reader, new HashSet<>());
            }
            Library.getInstance().getReaders().get(reader).add(borrowedBook);
            System.out.println(reader.getName() + ", " + book.getName() + " Kitabını ₺" + book.getPrice() + " karşılığında ödünç aldı, yeni miktar " + book.getQuantity() + ". Yeni bakiye ₺" + String.format("%.2f", reader.getBalance()));
        }
    }

    public void takeBackBook(Reader reader, Book... books) {
        for (Book book : books) {
            BorrowedBook borrowedBook = new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition());
            if (reader.getBorrowedBooks().contains(borrowedBook)) {
                reader.getBorrowedBooks().remove(borrowedBook);
                int newCount = book.getQuantity() + 1;
                book.setQuantity(newCount);
                reader.setBalance(reader.getBalance() + book.getPrice());
                reader.getActiveInvoices().removeIf(invoice -> invoice.getBook().equals(book));
                PaidInvoice paidInvoice = new PaidInvoice(reader, book);
                reader.getPaidInvoices().add(paidInvoice);
                System.out.println(reader.getName() + ", " + book.getName() + " kitabını iade etti,yeni miktar " + book.getQuantity() + ". Teşekkür ederiz. Yeni bakiye ₺" + String.format("%.2f", reader.getBalance()));
            } else {
                System.out.println(reader.getName() + " " + book.getName() + " kitabını görünüşe göre ödünç almamış.");
            }
        }
    }

    public void addReader(Reader reader) {
        if (Library.getInstance().getReaders().containsKey(reader)) {
            System.out.println(reader.getName() + " zaten kütüphanede kayıtlı bir okuyucu.");
        } else {
            Library.getInstance().getReaders().put(reader, new HashSet<>());
            System.out.println(reader.getName() + " kütüphaneye kayıt oldu.");
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
