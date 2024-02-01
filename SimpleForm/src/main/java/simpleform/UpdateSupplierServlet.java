package simpleform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateSupplierServlet")
public class UpdateSupplierServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM suppliers WHERE supplierId = ?");
            statement.setInt(1, supplierId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("supplierId", resultSet.getInt("supplierId"));
                request.setAttribute("supplierName", resultSet.getString("supplierName"));
                request.setAttribute("supplierAddress", resultSet.getString("supplierAddress"));
                request.setAttribute("supplierEmail", resultSet.getString("supplierEmail"));
            }

            request.getRequestDispatcher("/update_supplier.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to retrieve supplier data");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int supplierId = Integer.parseInt(request.getParameter("supplierId"));
        String supplierName = request.getParameter("supplierName");
        String supplierAddress = request.getParameter("supplierAddress");
        String supplierEmail = request.getParameter("supplierEmail");

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE suppliers SET supplierName = ?, supplierAddress = ?, supplierEmail = ? WHERE supplierId = ?");
            statement.setString(1, supplierName);
            statement.setString(2, supplierAddress);
            statement.setString(3, supplierEmail);
            statement.setInt(4, supplierId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("list_supplier.jsp?message=success");
            } else {
                response.sendRedirect("list_supplier.jsp?message=error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to update supplier information");
        }
    }
}
