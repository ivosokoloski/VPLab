package mk.ukim.finki.wp.lab.web.controler;

import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bookReservation")
public class BookReservationController {

    private final BookReservationService bookReservationService;

    public BookReservationController(BookReservationService bookReservationService) {
        this.bookReservationService = bookReservationService;
    }
    @PostMapping
    public String placeReservation(@RequestParam String bookTitle,
                                   @RequestParam String readerName,
                                   @RequestParam String readerAddress,
                                   @RequestParam int numCopies,
                                   HttpServletRequest request,
                                   Model model) {

        String clientIp = request.getRemoteAddr();

        bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        model.addAttribute("readerName", readerName);
        model.addAttribute("bookTitle", bookTitle);
        model.addAttribute("numCopies", numCopies);
        model.addAttribute("clientIp", clientIp);

        return "reservationConfirmation";
    }
}
