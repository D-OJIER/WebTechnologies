package classes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Servlet to handle PC Component information
public class PCComponentServlet extends HttpServlet {

    // Internal list to hold PC components (no database)
    private List<PCComponent> componentList = new ArrayList<>();

    // Class representing a PC Component
    class PCComponent {
        private String name;
        private String category;
        private String brand;
        private String price;
        private String description;

        public PCComponent(String name, String category, String brand, String price, String description) {
            this.name = name;
            this.category = category;
            this.brand = brand;
            this.price = price;
            this.description = description;
        }

        public String getName() { return name; }
        public String getCategory() { return category; }
        public String getBrand() { return brand; }
        public String getPrice() { return price; }
        public String getDescription() { return description; }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    
        // Display the list of PC Components with enhanced CSS
        out.println("<html>");
        out.println("<head>");
        out.println("<title>PC Components</title>");
        
        // Inline CSS for consistent styling
        out.println("<style>");
        out.println("body { background-color: #0D0D0D; color: #D3F8E2; font-family: Arial, sans-serif; }");
        out.println("h1, h2 { color: #33FF66; text-align: center; transition: color 0.5s ease; }");
        out.println("ul { list-style-type: none; padding: 0; }");
        out.println("li { background-color: #1A1A1A; margin: 10px 0; padding: 15px; border-radius: 8px; border: 2px solid #33FF66; transition: transform 0.3s ease, background-color 0.3s ease; }");
        out.println("li:hover { background-color: #333333; transform: scale(1.02); }");
        out.println("strong { color: #33FF66; }");
        
        // Form styling with transitions
        out.println("form { max-width: 600px; margin: 0 auto; padding: 20px; background-color: #1A1A1A; border-radius: 8px; border: 2px solid #33FF66; transition: border-color 0.3s ease; }");
        out.println("input[type='text'], textarea { width: 100%; padding: 10px; margin: 10px 0; border: 2px solid #33FF66; border-radius: 5px; background-color: #0D0D0D; color: #D3F8E2; transition: border-color 0.3s ease; }");
        out.println("input[type='text']:focus, textarea:focus { border-color: #66FF99; }");
        out.println("input[type='submit'] { background-color: #33FF66; color: #0D0D0D; border: none; padding: 10px 20px; cursor: pointer; transition: background-color 0.3s ease; }");
        out.println("input[type='submit']:hover { background-color: #66FF99; }");
        out.println("</style>");
        
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>PC Components</h1>");
        
        // Check if the list is empty
        if (componentList.isEmpty()) {
            out.println("<p style='text-align:center;'>No components added yet.</p>");
        } else {
            out.println("<ul>");
            for (PCComponent component : componentList) {
                out.println("<li><strong>Name:</strong> " + component.getName() + 
                            ", <strong>Category:</strong> " + component.getCategory() +
                            ", <strong>Brand:</strong> " + component.getBrand() +
                            ", <strong>Price:</strong> $" + component.getPrice() +
                            ", <strong>Description:</strong> " + component.getDescription() + "</li>");
            }
            out.println("</ul>");
        }
    
        // Form to add new PC Component
        out.println("<h2>Add New PC Component</h2>");
        out.println("<form action='PCComponentServlet' method='POST'>");
        out.println("<label for='name'>Name:</label>");
        out.println("<input type='text' id='name' name='name' required><br>");
        
        out.println("<label for='category'>Category:</label>");
        out.println("<input type='text' id='category' name='category' required><br>");
        
        out.println("<label for='brand'>Brand:</label>");
        out.println("<input type='text' id='brand' name='brand' required><br>");
        
        out.println("<label for='price'>Price:</label>");
        out.println("<input type='text' id='price' name='price' required><br>");
        
        out.println("<label for='description'>Description:</label>");
        out.println("<textarea id='description' name='description' rows='4' required></textarea><br>");
        
        out.println("<input type='submit' value='Add Component'>");
        out.println("</form>");
        
        out.println("</body></html>");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        String brand = request.getParameter("brand");
        String price = request.getParameter("price");
        String description = request.getParameter("description");

        // Create a new PC Component and add it to the list
        PCComponent newComponent = new PCComponent(name, category, brand, price, description);
        componentList.add(newComponent);

        // Redirect back to the GET view to display the updated list
        response.sendRedirect("PCComponentServlet");
    }
}
