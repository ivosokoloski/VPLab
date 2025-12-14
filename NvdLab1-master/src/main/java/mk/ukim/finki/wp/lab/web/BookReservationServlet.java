package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.BookReservation;
import mk.ukim.finki.wp.lab.service.BookReservationService;
import mk.ukim.finki.wp.lab.service.BookService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "bookReservation",urlPatterns = "/ne")
public class BookReservationServlet extends HttpServlet {
    private final BookReservationService bookReservationService;
    private final SpringTemplateEngine springTemplateEngine;
    public BookReservationServlet(BookReservationService bookReservationService, SpringTemplateEngine springTemplateEngine) {
        this.bookReservationService = bookReservationService;

        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. Прочитај податоци од формата
        String bookTitle = req.getParameter("bookTitle");
        String readerName = req.getParameter("readerName");
        String readerAddress = req.getParameter("readerAddress");
        int numCopies = Integer.parseInt(req.getParameter("numCopies"));

        // 2. IP адреса на клиентот
        String clientIp = req.getRemoteAddr();

        // 3. Креирај резервација
        bookReservationService.placeReservation(bookTitle, readerName, readerAddress, numCopies);

        // 4. Подготви контекст за Thymeleaf
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        context.setVariable("readerName", readerName);
        context.setVariable("clientIp", clientIp);
        context.setVariable("bookTitle", bookTitle);
        context.setVariable("numCopies", numCopies);

        // 5. Прикажи потврда
        springTemplateEngine.process("da.html", context, resp.getWriter());
    }
}
