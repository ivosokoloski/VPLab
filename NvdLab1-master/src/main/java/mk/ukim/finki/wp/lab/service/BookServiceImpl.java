package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.repository.AuthorRepository;
import mk.ukim.finki.wp.lab.repository.BookRepository;
import mk.ukim.finki.wp.lab.repository.InMemoryBookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService{
    public final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return this.bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String text, Double rating) {
        return List.of();
    }

    @Override
    public Book save(String title, String genre, Double averageRating, Long authorId) {
        if (title == null || title.isEmpty() ||
                genre == null || genre.isEmpty()  ||
                averageRating == null || averageRating < 0)
                 {
            throw new IllegalArgumentException();
        }
        Author author = authorRepository.findById(authorId);


        Book book = new Book(title, genre, averageRating,author );
        return bookRepository.save(book);

    }



    @Override
    public Book update(Long bookId,String title, String genre, Double averageRating, Long authorId) {
        if (title == null || title.isEmpty() ||
                genre == null || genre.isEmpty()  ||
                averageRating == null || averageRating < 0)
        {
            throw new IllegalArgumentException();
        }
        Author author = authorRepository.findById(authorId);
        Book book = findById(bookId);
        book.setAuthor(author);
        book.setAverageRating(averageRating);
        book.setTitle(title);
        book.setGenre(genre);
        return bookRepository.save(book);

    }

    @Override
    public void delete(Long id) {
        bookRepository.delete(id);

    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
