import org.redifoglu.person.Author;
import org.redifoglu.category.Category;
import org.redifoglu.category.Child;
import org.redifoglu.category.MiddleEarth;
import org.redifoglu.category.ScienceFiction;
import org.redifoglu.library.Book;
import org.redifoglu.library.Library;
import org.redifoglu.person.Librarian;
import org.redifoglu.person.Reader;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //singleton patterne göre bir sınıftan tek bir instance oluşturduk.
        Library library = Library.getInstance();
        Librarian librarian = new Librarian(1, "Mrs.Librarian");

        //Author sınıfına yeni yazarlar ekledik
        System.out.println("*****************************YAZAR EKLENDİ*********************************");
        Author rowling = new Author(1, "J.K. Rowling");
        Author tolkien = new Author(2, "J.R.R. Tolkien");
        Author asimov = new Author(3, "Isaac Asimov");
        Author martin = new Author(4, "George R.R. Martin");
        library.addAuthor(rowling);
        library.addAuthor(tolkien);
        library.addAuthor(asimov);
        library.addAuthor(martin);

        //Category sınıfına yeni kategoriler ekledik
        System.out.println("*****************************KATEGORİ EKLENDİ*********************************");
        Category child = new Child();
        Category middleEarth = new MiddleEarth();
        Category scienceFiction = new ScienceFiction();
        library.addCategories(child);
        library.addCategories(middleEarth);
        library.addCategories(scienceFiction);

        //Okuyucular için instancelar oluşturduk ve kütüphanemize ekledik
        System.out.println("*****************************OKUYUCU EKLENDİ*********************************");
        Reader mustafa = new Reader(1, "mustafa", 70);
        Reader hilal = new Reader(2, "hilal", 25);
        Reader berkay = new Reader(3, "berkay", 650);
        librarian.addReader(mustafa);
        librarian.addReader(hilal);
        librarian.addReader(berkay);

        //Kitaplar için instancelar oluşturduk ve kütüphanemize ekledik
        System.out.println("*****************************KİTAP EKLENDİ*********************************");
        Book yuzuk = new Book(1, tolkien, "Yüzüklerin Efendisi", middleEarth, 2, "OOP", LocalDate.of(2022, 6, 25));
        Book silmarillion = new Book(2, tolkien, "Silmarillion", middleEarth, 3, "Singleton", LocalDate.of(2023, 8, 29));
        Book hobbit = new Book(3, tolkien, "Hobbit", middleEarth, 1, "Observer", LocalDate.of(2019, 4, 21));
        Book harry = new Book(10, rowling, "Harry Potter ve Felsefe Taşı", child, 5, "Overloading", LocalDate.of(2024, 8, 10));
        Book harry2 = new Book(11, rowling, "Harry Potter ve Sırlar Odası", child, 10, "Overriding", LocalDate.of(2021, 10, 2));
        Book vakif = new Book(100, asimov, "Vakıf Kurulurken", scienceFiction, 1, "Interface", LocalDate.of(2010, 6, 6));
        Book sonsuz = new Book(101, asimov, "Sonsuzluğun Sonu", scienceFiction, 2, "Abstract", LocalDate.of(2019, 4, 19));
        Book taht = new Book(1000, martin, "Taht Oyunları", scienceFiction, 4, "Java", LocalDate.of(2023, 9, 16));
        Book kral = new Book(1001, martin, "Kralların Çarpışması", child, 3, "React", LocalDate.of(2021, 5, 13));
        Book kilic = new Book(1002, martin, "Kılıçların Fırtınası", middleEarth, 9, "Javascript", LocalDate.of(2020, 9, 6));
        Book karga = new Book(1003, martin, "Kargaların Ziyafeti", child, 14, "Html", LocalDate.of(2021, 1, 1));
        Book ejderha = new Book(1004, martin, "Ejderhalarla Dans", scienceFiction, 6, "Css", LocalDate.of(2024, 4, 4));
        library.addBook(yuzuk, silmarillion, hobbit);
        library.addBook(harry, taht, kral, kilic, karga);
        library.addBook(harry2, ejderha);
        library.addBook(vakif);
        library.addBook(sonsuz);
        //id bilgisine göre kitap seçtik.
        System.out.println("*****************************IDYE GÖRE KİTAP SEÇİLDİ*********************************");
        System.out.println(library.getBooks().get(100));
        System.out.println(library.getBooks().get(1004));

        //okuyucu kütüphanede boşta bulunan bir kitabı ödünç alabilir ve aldığı zaman okuyucudan kitap tutarı kadar ücret kesilir. aynı anda en fazla 5 ödünç kitaba sahip olunabilir.
        System.out.println("*****************************KİTAP(LAR) ÖDÜNÇ ALINDI*********************************");
        librarian.lendBook(berkay, hobbit, harry, vakif, silmarillion, sonsuz, harry2);
        librarian.lendBook(hilal, hobbit);
        librarian.lendBook(mustafa, ejderha);
        System.out.println(library.getReaders());

        //okuyucu daha önce ödünç aldığı kitabı teslim edebilir ve ettiği zaman ödünç alırken kesilen ücret iade edilir
        System.out.println("*****************************ÖDÜNÇ ALINAN KİTAPLAR İADE EDİLDİ*********************************");
        librarian.takeBackBook(berkay, hobbit, vakif);
        librarian.takeBackBook(hilal, hobbit);

        //seçilen IDdeki kitabın bilgilerini güncelledik ama ödünç alınmış durumdaysa güncelleyemeyiz
        System.out.println("*****************************SEÇİLEN IDDEKİ KİTAP BİLGİLERİ GÜNCELLENDİ*********************************");
        library.updateBook(1, "Yüzüklerin Efendisi Yüzük Kardeşliği", tolkien, middleEarth, 7, "OOP", LocalDate.of(2022, 6, 25));
        library.updateBook(3, "Hobbit 2", tolkien, middleEarth, 7, "OOP", LocalDate.of(2022, 6, 25));

        //sistemdeki bir kitabı sildik ama ödünç alınmış durumdaysa silemeyiz
        System.out.println("*****************************SEÇİLEN KİTAP SİLİNDİ*********************************");
        library.removeBook(hobbit);
        library.removeBook(vakif);
        library.removeBook(karga);

        //seçilen kategorideki tüm kitapları sildik
        System.out.println("*****************************SEÇİLEN KATEGORİDEKİ TÜM KİTAPLAR SİLİNDİ*********************************");
        library.removeBook(child);

        //seçilen yazarın tüm kitaplarını sildik
        System.out.println("*****************************SEÇİLEN YAZARIN TÜM KİTAPLARI SİLİNDİ*********************************");
        library.removeBook(tolkien);
    }
}
