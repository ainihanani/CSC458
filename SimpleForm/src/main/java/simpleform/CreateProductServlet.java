package simpleform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateProductServlet")
public class CreateProductServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO products (productName, quantity, productPrice) VALUES (?, ?, ?)");
            statement.setString(1, productName);
            statement.setInt(2, quantity);
            statement.setDouble(3, productPrice);

            int rowsAffected = statement.executeUpdate();

            out.println("<html>");
            out.println("<head><title>Create Product - POST</title></head>");
            out.println("<body>");
            out.println("<h1>Create Product - POST</h1>");

            if (rowsAffected > 0) {
                out.println("<p>Product information saved in the database.</p>");
            } else {
                out.println("<p>Failed to save product information. Please try again.</p>");
            }

            out.println("<p><a href=\"list_product.jsp\">View Product List</a></p>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: Unable to save product information");
        }
    }
}
