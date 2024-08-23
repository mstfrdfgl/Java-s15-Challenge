package org.redifoglu.library;

import org.redifoglu.category.Category;
import org.redifoglu.interfaces.Observer;
import org.redifoglu.person.Author;
import org.redifoglu.person.Reader;

import java.time.LocalDate;
import java.util.*;

public class Library {

    private static Library instance;
    private Map<Integer, Book> books;
    private Map<Integer, Author> authors;
    private Map<Integer, Category> categories;
    private Map<Reader, Set<BorrowedBook>> readers;
    private List<Observer> observers = new ArrayList<>();//library classındaki değişiklikleri gözlemlemek için gelen gözlemcilerin tutulduğu liste

    private Library() {
        books = new HashMap<>();
        readers = new HashMap<>();
        authors = new HashMap<>();
        categories = new HashMap<>();
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

    public Map<Integer, Book> getBooks() {
        return books;
    }

    public Map<Reader, Set<BorrowedBook>> getReaders() {
        return readers;
    }

    public Map<Integer, Author> getAuthors() {
        return authors;
    }

    public Map<Integer, Category> getCategories() {
        return categories;
    }

    public void addBook(Book... booksToAdd) {
        for (Book book : booksToAdd) {
            if (books.values().stream().anyMatch(b -> b.getName().equalsIgnoreCase(book.getName()) && b.getEdition().equalsIgnoreCase(book.getEdition()))) {
                System.out.println("Kitap: " + book.getName() + " zaten mevcut.".toUpperCase());
                continue;
            }
            if (books.containsKey(book.getBookID())) {
                System.out.println("ID: " + book.getBookID() + " daha önce başka bir kitapta kullanılmış.".toUpperCase());
                continue;
            }
            int count = book.getQuantity();
            if (count <= 0) {
                System.out.println("Kitap: " + book.getName() + " için en az 1 adet kitap eklemelisiniz.");
                continue;
            }
            books.put(book.getBookID(), book);
            book.setQuantity(count);
            System.out.println(book.getName() + " kütüphaneye eklendi.");
        }
        notifyObservers();
    }

    public void updateBook(int bookID, String newName, Author newAuthor, Category newCategory, int newQuantity, String newEdition, LocalDate newDateOfPurchase) {
        Book bookToUpdate = books.get(bookID);
        if (bookToUpdate == null) {
            System.out.println("Güncellenecek kitap bulunamadı");
            return;
        }
        boolean isBookBorrowed = false;
        for (Set<BorrowedBook> borrowedBooks : readers.values()) {
            if (borrowedBooks.contains(new BorrowedBook(bookToUpdate.getName(), bookToUpdate.getAuthor(), bookToUpdate.getCategory(), bookToUpdate.getEdition()))) {
                isBookBorrowed = true;
                break;
            }
        }
        if (isBookBorrowed) {
            System.out.println("Bu kitap şuanda ödünç alınmış durumda olduğu için güncellenemez.");
        } else {
            if (books.values().stream().filter(b -> b.getBookID() != bookID)
                    .noneMatch(b -> b.getName().equalsIgnoreCase(newName) ||
                            b.getEdition().equalsIgnoreCase(newEdition))) {
                bookToUpdate.setName(newName);
                bookToUpdate.setAuthor(newAuthor);
                bookToUpdate.setCategory(newCategory);
                bookToUpdate.setQuantity(newQuantity);
                bookToUpdate.setEdition(newEdition);
                bookToUpdate.setDateOfPurchase(newDateOfPurchase);
                System.out.println(bookID + " ID numarasına sahip kitap başarıyla güncellendi.");
            } else {
                System.out.println("Güncellemek istediğiniz kitap zaten bulunuyor.");
            }
        }
    }

    public void addAuthor(Author author) {
        if (authors.containsKey(author.getAuthorID())) {
            System.out.println("Bu ID daha önce başka bir yazarda kullanılmış.".toUpperCase());
            return;
        }
        authors.put(author.getAuthorID(), author);
        System.out.println(author.getName() + " Yazarlara eklendi.");
    }

    public void addCategories(Category category) {
        if (categories.containsKey(category.getCategoryID())) {
            System.out.println("Bu ID daha önce başka bir kategoride kullanılmış");
            return;
        }
        categories.put(category.getCategoryID(), category);
        System.out.println(category.getName() + " Kategorilere eklendi.");
    }

    public void removeBook(Book book) {
        if (!books.containsValue(book)) {
            System.out.println("Bu kitap zaten kütüphanemizde bulunmamakta.");
            return;
        }
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
            books.remove(book.getBookID());
            notifyObservers();
            System.out.println(book.getName() + " kütüphaneden kaldırıldı.");
        }
    }

    public void removeBook(Category category) {
        List<Book> booksToRemove = new ArrayList<>(category.getBooks());
        if (category.getBooks().isEmpty()) {
            System.out.println("Bu kategoride zaten kitap yok");
        }
        for (Book book : booksToRemove) {
            removeBook(book);
        }
    }

    public void removeBook(Author author) {
        List<Book> booksToRemove = new ArrayList<>(author.getBooks());
        if (author.getBooks().isEmpty()) {
            System.out.println("Bu yazarın zaten kitabı yok");
        }
        for (Book book : booksToRemove) {
            removeBook(book);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("---").append(books.size()).append(" Library Books---\n");
        for (Book book : books.values()) {
            sb.append(book).append("\n");
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
