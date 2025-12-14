package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
@Entity
@Data // Generates getters, setters, toString, equals, and hashCode methods
@Table(name = "books")
public class Book {
    String title;
    String genre;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @ManyToOne
    private Author author;

    public Book() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Book(String title, String genre, double rating, Author author) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.averageRating = rating;
    }
    double averageRating;

    public Long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public double getAverageRating() {
        return averageRating;
    }
}
