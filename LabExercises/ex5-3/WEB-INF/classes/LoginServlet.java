import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");

        // Create a cookie to store the username
        Cookie userCookie = new Cookie("username", username);
        userCookie.setMaxAge(60 * 60); // 1 hour
        response.addCookie(userCookie);

        response.sendRedirect("PCComponentServlet");
    }
}
