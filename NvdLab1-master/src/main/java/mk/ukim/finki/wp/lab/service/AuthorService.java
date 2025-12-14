package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;

import java.util.List;

public interface AuthorService {
    public List<Author> findAll();
    Author save(String name, String surname, String country, String biography);
    Author update(Long id, String name, String surname, String country, String biography);

    void delete(Long id);
    Author findById(Long id);
}
