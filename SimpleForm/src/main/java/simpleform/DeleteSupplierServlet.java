package simpleform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteSupplierServlet")
public class DeleteSupplierServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM suppliers WHERE supplierId = ?");
            statement.setInt(1, supplierId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("list_supplier.jsp?message=success");
            } else {
                response.sendRedirect("list_supplier.jsp?message=error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to delete supplier");
        }
    }
}
