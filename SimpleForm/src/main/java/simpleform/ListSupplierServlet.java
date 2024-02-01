package simpleform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ListSupplierServlet")
public class ListSupplierServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Supplier> suppliers = getSuppliersFromDatabase();
            request.setAttribute("suppliers", suppliers);

            // Forward the request to the JSP
            request.getRequestDispatcher("/list_supplier.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to retrieve supplier list");
        }
    }

    private List<Supplier> getSuppliersFromDatabase() throws SQLException {
        List<Supplier> suppliers = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM suppliers");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setSupplierId(resultSet.getInt("supplierId"));
                supplier.setSupplierName(resultSet.getString("supplierName"));
                supplier.setSupplierAddress(resultSet.getString("supplierAddress"));
                supplier.setSupplierEmail(resultSet.getString("supplierEmail"));
                suppliers.add(supplier);
            }
        }

        return suppliers;
    }
}
