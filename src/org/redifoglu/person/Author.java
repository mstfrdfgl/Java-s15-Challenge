package org.redifoglu.person;

import org.redifoglu.interfaces.Observer;
import org.redifoglu.library.Book;
import org.redifoglu.library.Library;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Author extends Person implements Observer {
    Set<Book> books = new HashSet<>();

    public Author(String name) {
        super(name);
        Library.getInstance().addObservers(this);
    }

    public void update(){
        books.clear();
        for(Book book: Library.getInstance().getBooks().keySet()){
            if(book.getAuthor().equals(this)){
                books.add(book);
            }
        }
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public void whoYouAre() {
        System.out.println(getName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(" tarafından yazılmış ").append(books.size()).append(" adet kitap mevcut.");
        if (!books.isEmpty()) {
            sb.append(" Bu kitaplar: ");
            sb.append(books.stream()
                    .map(Book::getName)
                    .collect(Collectors.joining(", ")));
        }
        return sb.toString();
    }


}
