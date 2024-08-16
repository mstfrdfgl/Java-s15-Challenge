package org.example.person;

import org.example.interfaces.Observer;
import org.example.library.Book;
import org.example.library.Library;

import java.util.HashSet;
import java.util.Set;

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
        return "Author{" +
                "name=" + getName() +
                ", booksCount=" + books.size() +
                "} ";
    }

}
