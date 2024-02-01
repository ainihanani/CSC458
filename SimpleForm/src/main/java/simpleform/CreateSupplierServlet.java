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

@WebServlet("/CreateSupplierServlet")
public class CreateSupplierServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String supplierName = request.getParameter("supplierName");
        String supplierAddress = request.getParameter("supplierAddress");
        String supplierEmail = request.getParameter("supplierEmail");

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO suppliers (supplierName, supplierAddress, supplierEmail) VALUES (?, ?, ?)");
            statement.setString(1, supplierName);
            statement.setString(2, supplierAddress);
            statement.setString(3, supplierEmail);

            int rowsAffected = statement.executeUpdate();

            out.println("<html>");
            out.println("<head><title>Create Supplier - POST</title></head>");
            out.println("<body>");
            out.println("<h1>Create Supplier - POST</h1>");

            if (rowsAffected > 0) {
                out.println("<p>Supplier information saved in the database.</p>");
            } else {
                out.println("<p>Failed to save supplier information. Please try again.</p>");
            }

            out.println("<p><a href=\"list_supplier.jsp\">View Supplier List</a></p>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: Unable to save supplier information");
        }
    }
}
