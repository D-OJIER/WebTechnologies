package classes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/visitCounter")
public class VisitCounterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String css = "<style>" +
                     "body { font-family: Arial, sans-serif; background-color: #121212; color: #00ff00; display: flex; justify-content: center; align-items: center; height: 100vh; margin: 0; }" +
                     ".container { background-color: #1c1c1c; padding: 20px 30px; border: 2px solid #00ff00; border-radius: 15px; box-shadow: 0 0 15px rgba(0, 255, 0, 0.5); width: 40%; text-align: center; transition: transform 0.3s, box-shadow 0.3s; }" +
                     ".container:hover { transform: scale(1.05); box-shadow: 0 0 25px rgba(0, 255, 0, 0.8); }" +
                     "h1, h2 { color: #00ff00; }" +
                     "</style>";

        HttpSession session = request.getSession(true);

        String userId = request.getParameter("userId");
        if (userId == null || userId.isEmpty()) {
            userId = "defaultUser";
        }

        Integer visitCount = (Integer) session.getAttribute(userId);

        if (visitCount == null) {
            visitCount = 1;
        } else {
            visitCount += 1;
        }
        session.setAttribute(userId, visitCount);

        out.println("<html>" + css + "<body>");
        out.println("<div class='container'>");
        out.println("<h1>Welcome Back, " + userId + "!</h1>");
        out.println("<h2>You have visited this page " + visitCount + " times.</h2>");
        out.println("</div>");
        out.println("</body></html>");

        Cookie visitCookie = new Cookie("visitCount", visitCount.toString());
        visitCookie.setMaxAge(60 * 60 * 24);
        response.addCookie(visitCookie);

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
