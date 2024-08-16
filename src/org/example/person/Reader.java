package org.example.person;

import org.example.library.Book;
import org.example.library.BorrowedBook;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Reader extends Person {
    private Set<BorrowedBook> books = new LinkedHashSet<>();

    public Reader(String name) {
        super(name);
    }

    public Set<BorrowedBook> getBooks() {
        return books;
    }

    @Override
    public void whoYouAre() {
        super.toString();
    }

    public void borrowBook(Book book) {
        if (books.size() >= 5) {
            System.out.println("Aynı anda en fazla 5 adet kitap ödünç alabilirsiniz. Daha fazla kitap almak için lütfen elinizdeki kitapları tekrar kütüphanemize getirin.");
        } else if (books.contains(new BorrowedBook(book.getName(),book.getAuthor(),book.getCategory(),book.getEdition()))) {
            System.out.println(book.getName() + " kitabını zaten ödünç almışsınız. Bir kez daha alamazsınız.");
        } else if (book.getQuantity()==0) {
            System.out.println(getName() + "'nın ödünç almak istediği kitap kütüphanemizde kalmadı.");
        }else if (book.getQuantity() > 0) {
            books.add(new BorrowedBook(book.getName(),book.getAuthor(),book.getCategory(),book.getEdition()));
            int newCount = book.getQuantity() - 1;
            book.setQuantity(newCount);
            System.out.println(getName() + " " + book.getName() + " Kitabını ödünç aldı.*** ");
        }
    }
    public void returnBook(Book book){
        if(books.contains(book)){

        }
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
