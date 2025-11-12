package mk.ukim.finki.wp.lab.web.controler;

import mk.ukim.finki.wp.lab.model.Book;
import mk.ukim.finki.wp.lab.service.AuthorServiceImpl;
import mk.ukim.finki.wp.lab.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private final AuthorServiceImpl authorServiceImpl;

    public BookController(BookService bookService, AuthorServiceImpl authorServiceImpl) {
        this.bookService = bookService;
        this.authorServiceImpl = authorServiceImpl;
    }

    @GetMapping
    public String getBooksPage(@RequestParam(required = false) String error, Model model){
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Book> books = bookService.listAll();
        model.addAttribute("books", books);
        return "listBooks";
    }

    @PostMapping("/add")
    public String saveBook(@RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId){
        bookService.save(title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String deleteBook(@PathVariable Long id){
        bookService.delete(id);
        return "redirect:/books";
    }

    @PostMapping("/edit/{bookId}")
    public String editBook(@PathVariable Long bookId,
                           @RequestParam String title,
                           @RequestParam String genre,
                           @RequestParam Double averageRating,
                           @RequestParam Long authorId) {
        bookService.update(bookId, title, genre, averageRating, authorId);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String getEditBookForm(@PathVariable Long id, Model model) {
        try {
            Book book = bookService.findById(id);
            model.addAttribute("book", book);
            model.addAttribute("authors", authorServiceImpl.listAll());
            return "book-form";
        } catch (Exception e) {
            return "redirect:/books?error=BookNotFound";
        }
    }

    @GetMapping("/add-form")
    public String getAddBookPage(Model model) {
        Book emptyBook = new Book();
        model.addAttribute("book", emptyBook);
        model.addAttribute("authors", authorServiceImpl.listAll());
        return "book-form";
    }
}