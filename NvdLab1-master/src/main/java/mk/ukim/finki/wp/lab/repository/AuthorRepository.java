package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.model.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthorRepository {

    private final List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        authors.add(new Author("Petar", "Petrov", "Macedonia", "Short biography of Petar."));
        authors.add(new Author("Ana", "Stojanovska", "Macedonia", "Short biography of Ana."));
        authors.add(new Author("Ivan", "Markovski", "Macedonia", "Short biography of Ivan."));
    }

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(Long id) {
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
