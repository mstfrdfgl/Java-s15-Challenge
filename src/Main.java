import org.redifoglu.person.Author;
import org.redifoglu.category.Category;
import org.redifoglu.category.Child;
import org.redifoglu.category.MiddleEarth;
import org.redifoglu.category.ScienceFiction;
import org.redifoglu.library.Book;
import org.redifoglu.library.Library;
import org.redifoglu.person.Reader;

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

        Reader mustafa = new Reader("mustafa", 10);
        Reader hilal = new Reader("hilal", 25);
        Reader berkay = new Reader("berkay", 65);
        System.out.println("**************************************************************");
        Book yuzuk = new Book(1, tolkien, "Yüzüklerin Efendisi", middleEarth, 2, "Third", LocalDate.of(2022, 6, 25));
        Book silmarillion = new Book(2, tolkien, "Silmarillion", middleEarth, 3, "Second", LocalDate.of(2023, 8, 29));
        Book hobbit = new Book(3, tolkien, "Hobbit", middleEarth, 1, "First", LocalDate.of(2019, 4, 21));
        Book harry = new Book(10, rowling, "Harry Potter ve Felsefe Taşı", child, 5, "First edition", LocalDate.of(2024, 8, 10));
        Book harry2 = new Book(11, rowling, "Harry Potter ve Sırlar Odası", child, 10, "First edition", LocalDate.of(2021, 10, 2));
        Book vakif = new Book(100, asimov, "Vakıf Kurulurken", scienceFiction, 1, "Special", LocalDate.of(2010, 6, 6));
        Book sonsuz = new Book(101, asimov, "Sonsuzluğun Sonu", scienceFiction, 2, "Abstract", LocalDate.of(2019, 4, 19));
        System.out.println("**************************************************************");


        hilal.borrowBook(sonsuz);
        library.removeBook(sonsuz);
        System.out.println(library);


    }
}