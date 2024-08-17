package org.redifoglu.library;

import org.redifoglu.category.Category;
import org.redifoglu.interfaces.Observer;
import org.redifoglu.person.Reader;

import java.util.*;

public class Library {

    private static Library instance;
    private Map<Book, Integer> books;
    private Map<Reader, Set<BorrowedBook>> readers;
    private List<Observer> observers = new ArrayList<>();//library classındaki değişiklikleri gözlemlemek için gelen gözlemcilerin tutulduğu liste

    private Library() {
        books = new HashMap<>();
        readers = new HashMap<>();
        System.out.println();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }

    public void addObservers(Observer observer) {//library classına gözlemci gönderecek classlar bu metod ile gönderir
        observers.add(observer);
    }

    public void removeObservers(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {//bu metod tetiklendiğinde library classında gözlemci bulunduran classlar uyarılır ve gerekli güncellemeler yapılır
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }

    public Map<Reader, Set<BorrowedBook>> getReaders() {
        return readers;
    }

    //    public Set<Book> getBooksByCategory(Category category) {
//        return category.getBooks();
//    }

    public void addBook(Book book) {
        for (Book existingBook : books.keySet()) {
            if (existingBook.getName().equalsIgnoreCase(book.getName())) {
                System.out.println("Bu kitap zaten mevcut.".toUpperCase());
                return;
            }
            if (existingBook.getBookID() == book.getBookID()) {
                System.out.println("Bu ID daha önce başka bir kitapta kullanılmış.".toUpperCase());
                return;
            }
        }
        int count = book.getQuantity();
        if (count <= 0) {
            System.out.println("En az 1 adet kitap eklemelisiniz.");
            return;
        }
        books.put(book, count);
        book.setQuantity(count);
        notifyObservers();
        System.out.println(book.getName() + " kütüphaneye eklendi.");
    }

    public void removeBook(Book book) {
        boolean isBookBorrowed = false;
        for (Set<BorrowedBook> borrowedBooks : readers.values()) {
            if (borrowedBooks.contains(new BorrowedBook(book.getName(), book.getAuthor(), book.getCategory(), book.getEdition()))) {
                isBookBorrowed = true;
                break;
            }
        }
        if (isBookBorrowed) {
            System.out.println("Bu kitap şuanda ödünç alınmış durumda olduğu için silinemez.");
        } else {
            books.remove(book);
            notifyObservers();
            System.out.println(book.getName() + " kütüphaneden kaldırıldı.");
        }

    }

    public void addReader(Reader reader) {
        if (readers.containsKey(reader)) {
            System.out.println(reader.getName() + " zaten kütüphanede kayıtlı bir okuyucu.");
        } else {
            readers.put(reader, new HashSet<>());
            System.out.println(reader.getName() + " kütüphaneye kayıt oldu.");
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---").append(books.size()).append(" Library Books---\n");
        for (Map.Entry<Book, Integer> entry : books.entrySet()) {
            sb.append(entry.getKey()).append("\n");
        }
        sb.append("Readers:\n");
        sb.append(readers);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Library library = (Library) o;
        return Objects.equals(books, library.books) && Objects.equals(readers, library.readers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(books, readers);
    }
}
