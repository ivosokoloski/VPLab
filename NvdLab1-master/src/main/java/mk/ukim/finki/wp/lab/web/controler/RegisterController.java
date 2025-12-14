package mk.ukim.finki.wp.lab.web.controler;

import lombok.AllArgsConstructor;

import mk.ukim.finki.wp.lab.model.Role;
import mk.ukim.finki.wp.lab.service.InvalidArgumentsException;
import mk.ukim.finki.wp.lab.service.PasswordsDoNotMatchException;
import mk.ukim.finki.wp.lab.service.UserService;
import mk.ukim.finki.wp.lab.service.UsernameAlreadyExistsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(Model model) {
        model.addAttribute("bodyContent", "register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatPassword,
                           Model model) {

        try {
            this.userService.register(username, password, repeatPassword, name, surname, Role.ROLE_ADMIN);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("bodyContent", "register");
            return "master-template";
        } catch (PasswordsDoNotMatchException e) {
            throw new RuntimeException(e);
        } catch (UsernameAlreadyExistsException e) {
            throw new RuntimeException(e);
        } catch (InvalidArgumentsException e) {
            throw new RuntimeException(e);
        }

    }

}
