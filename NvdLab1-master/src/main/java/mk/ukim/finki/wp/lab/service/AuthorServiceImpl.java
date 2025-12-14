package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.jpa.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    public final AuthorRepository authorRepository;
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public List<Author> listAll() {
        return this.authorRepository.findAll();
    }

    @Override
    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    public Author save(String name, String surname, String country, String biography) {
        if (name == null || surname.isEmpty() ||
                country == null || biography.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        Author author = new Author(name, surname, country,biography );
        return authorRepository.save(author);

    }


    public Author update(Long id,String name, String surname, String country, String biography) {
        if (name == null || surname.isEmpty() ||
                country == null || biography.isEmpty())
        {
            throw new IllegalArgumentException();
        }

        Author author = findById(id);
        author.setBiography(biography);
        author.setCountry(country);
        author.setName(name);
        author.setSurname(surname);
        return authorRepository.save(author);

    }
    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }


    public void delete(Long id) {
        authorRepository.deleteById(id);
    }
}
