package classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PCComponentServlet extends HttpServlet {

    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/pc_catalog";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>PC Components</title>");
        out.println("<style>/* Include the same CSS from the HTML above */</style>");
        out.println("</head><body>");
        out.println("<h1>PC Components</h1>");
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM components");

            out.println("<div class='table-container'><table>");
            out.println("<tr><th>Name</th><th>Category</th><th>Brand</th><th>Price</th><th>Description</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>" + rs.getString("name") + "</td>"
                        + "<td>" + rs.getString("category") + "</td>"
                        + "<td>" + rs.getString("brand") + "</td>"
                        + "<td>$" + rs.getDouble("price") + "</td>"
                        + "<td>" + rs.getString("description") + "</td></tr>");
            }
            out.println("</table></div>");
        } catch (SQLException e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "INSERT INTO components (name, category, brand, price, description) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, name);
            pstmt.setString(2, category);
            pstmt.setString(3, brand);
            pstmt.setDouble(4, Double.parseDouble(price));
            pstmt.setString(5, description);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.sendRedirect("PCComponentServlet");
    }
}
