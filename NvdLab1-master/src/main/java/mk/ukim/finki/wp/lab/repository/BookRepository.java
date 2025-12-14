package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Book;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface BookRepository {

        List<Book> findAll();
        Book save(Book book);
        void delete(Long id);
        List<Book> searchBooks(String text, Double rating);
        Optional<Book> findById(Long id);


}
