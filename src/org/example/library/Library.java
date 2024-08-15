package org.example.library;

import org.example.author.Author;
import org.example.category.Category;
import org.example.interfaces.Observer;

import java.util.*;

public class Library {

    private static Library instance;
    private Map<Book, Integer> books;
    private Map<Book, Integer> readers;
    private List<Observer> observers=new ArrayList<>();

    private Library() {
        books = new HashMap<>();
        readers = new HashMap<>();
    }

    public static Library getInstance() {
        if (instance == null) {
            instance = new Library();
        }
        return instance;
    }
    public void addObservers(Observer observer){
        observers.add(observer);
    }
    public void removeObservers(Observer observer){
        observers.remove(observer);
    }
    public void notifyObservers(){
        for (Observer observer:observers){
            observer.update();
        }
    }

    public Map<Book, Integer> getBooks() {
        return books;
    }

    public Map<Book, Integer> getReaders() {
        return readers;
    }

    public Set<Book> getBooksByCategory(Category category) {
        return category.getBooks();
    }
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
        int count=book.getStatus().get(true);
        if (count <= 0) {
            System.out.println("En az 1 adet kitap eklemelisiniz.");
            return;
        }
        books.put(book, count);
        book.getStatus().put(true, count);
        notifyObservers();
    }


    public void removeBook(Book book) {
        books.remove(book);
        notifyObservers();
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
