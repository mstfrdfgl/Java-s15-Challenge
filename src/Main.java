import org.example.author.Author;
import org.example.category.Category;
import org.example.category.Child;
import org.example.category.MiddleEarth;
import org.example.category.ScienceFiction;
import org.example.enums.Cat;
import org.example.library.Book;
import org.example.library.Library;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Library library = Library.getInstance();
        Author rowling = new Author("J.K.Rowling");
        Author tolkien = new Author("J.R.R.Tolkien");
        Author asimov = new Author("Isaac Asimov");
        Category child = new Child();
        Category middleEarth = new MiddleEarth();
        Category scienceFiction = new ScienceFiction();


        Book yuzuk = new Book(2, tolkien, "Yüzüklerin Efendisi", middleEarth, 2, "Third", LocalDate.of(2022, 6, 25));
        Book silmarillion = new Book(10, tolkien, "Silmarillion", middleEarth, 3, "Second", LocalDate.of(2023, 8, 29));
        Book Hobbit = new Book(11, tolkien, "Hobbit", middleEarth, 1, "First", LocalDate.of(2019, 4, 21));


        Book harry = new Book(1, rowling, "Harry Potter ve Felsefe Taşı", child, 5, "First edition", LocalDate.of(2024, 8, 10));
        Book harry2 = new Book(3, rowling, "Harry Potter ve Sırlar Odası", child, 10, "First edition", LocalDate.of(2021, 10, 2));//        library.addBook(harry);

        Book vakif = new Book(100, asimov, "Vakıf Kurulurken", scienceFiction, 1, "Special", LocalDate.of(2010, 6, 6));
        Book sonsuz = new Book(101, asimov, "Sonsuzluğun Sonu", scienceFiction, 2, "Abstract", LocalDate.of(2019, 4, 19));
//        library.removeBook(silmarillion);
        System.out.println(tolkien);
        System.out.println(asimov);

//        System.out.println(library.getBooks());


    }
}