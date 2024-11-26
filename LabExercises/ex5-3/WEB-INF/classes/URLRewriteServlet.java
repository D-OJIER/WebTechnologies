import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/URLRewriteServlet")
public class URLRewriteServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sessionId = session.getId();

        // Redirect with session ID in the URL
        response.sendRedirect("PCComponentServlet?jsessionid=" + sessionId);
    }
}
