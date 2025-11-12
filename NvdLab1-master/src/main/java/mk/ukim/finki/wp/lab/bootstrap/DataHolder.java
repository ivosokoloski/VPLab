package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.model.BookReservation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Book> books = new ArrayList<>();
    public static List<Author> authors = new ArrayList<>();

    public static List<BookReservation> reservations = new ArrayList<>();
    @PostConstruct
    public void init() {

        authors.add(new Author("Petar", "Petrov", "Macedonia", "Short biography of Petar."));
        authors.add(new Author("Ana", "Stojanovska", "Macedonia", "Short biography of Ana."));
        authors.add(new Author("Ivan", "Markovski", "Macedonia", "Short biography of Ivan."));
        books.add(new Book("prva kniga","prv zanr",4.3,authors.getFirst() ));
        books.add(new Book("vtora kniga","vtor zanr",4.5,authors.getLast()));
        books.add(new Book("treta kniga","tret zanr",4.1,authors.getFirst()));
        books.add(new Book("cetvrta kniga","cetvrt zanr",4.7,authors.getLast()));
    }

}
