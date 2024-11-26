import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PCComponentServlet")
public class PCComponentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cookie[] cookies = request.getCookies();
        String username = null;

        // Check if a cookie exists
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><link rel='stylesheet' href='styles.css'></head><body>");
        out.println("<div class='form-container'>");
        out.println("<h1>Welcome, " + (username != null ? username : "Guest") + "</h1>");
        out.println("<p>Session ID: " + session.getId() + "</p>");
        out.println("<a href='index.html'>Back to Home</a>");
        out.println("</div>");
        out.println("</body></html>");
    }
}
