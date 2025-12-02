package mk.ukim.finki.wp.lab.web.controler;

import mk.ukim.finki.wp.lab.model.Author;
import mk.ukim.finki.wp.lab.service.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthorsPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", error);
        }

        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "listAuthors"; // Thymeleaf template
    }

    @PostMapping("/add")
    public String saveAuthor(@RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography) {
        authorService.save(name, surname, country, biography);
        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String deleteAuthor(@PathVariable Long id) {
        authorService.delete(id);
        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String getEditAuthorForm(@PathVariable Long id, Model model) {
        try {
            Author author = authorService.findById(id);
            model.addAttribute("author", author);
            return "author-form";
        } catch (RuntimeException e) {
            return "redirect:/authors?error=AuthorNotFound";
        }
    }

    @PostMapping("/edit/{id}")
    public String editAuthor(@PathVariable Long id,
                             @RequestParam String name,
                             @RequestParam String surname,
                             @RequestParam String country,
                             @RequestParam String biography) {
        Author author = authorService.findById(id);
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        author.setBiography(biography);
        authorService.save(author.getName(), author.getSurname(), author.getCountry(), author.getBiography());
        return "redirect:/authors";
    }

    @GetMapping("/add-form")
    public String getAddAuthorPage(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }
}