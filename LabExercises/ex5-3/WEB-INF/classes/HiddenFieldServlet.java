import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/HiddenFieldServlet")
public class HiddenFieldServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><head><link rel='stylesheet' href='styles.css'></head><body>");
        out.println("<div class='form-container'>");
        out.println("<form action='PCComponentServlet' method='POST'>");
        out.println("<input type='hidden' name='username' value='HiddenUser'>");
        out.println("<h2>Session using Hidden Fields</h2>");
        out.println("<input type='submit' value='Submit'>");
        out.println("</form>");
        out.println("</div>");
        out.println("</body></html>");
    }
}
