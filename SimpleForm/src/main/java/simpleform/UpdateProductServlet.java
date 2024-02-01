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

@WebServlet("/UpdateProductServlet")
public class UpdateProductServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE productId = ?");
            statement.setInt(1, productId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("productId", resultSet.getInt("productId"));
                request.setAttribute("productName", resultSet.getString("productName"));
                request.setAttribute("quantity", resultSet.getInt("quantity"));
                request.setAttribute("productPrice", resultSet.getDouble("productPrice"));
            }

            request.getRequestDispatcher("/update_product.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to retrieve product data");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productId = Integer.parseInt(request.getParameter("productId"));
        String productName = request.getParameter("productName");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE products SET productName = ?, quantity = ?, productPrice = ? WHERE productId = ?");
            statement.setString(1, productName);
            statement.setInt(2, quantity);
            statement.setDouble(3, productPrice);
            statement.setInt(4, productId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("list_product.jsp?message=success");
            } else {
                response.sendRedirect("list_product.jsp?message=error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to update product information");
        }
    }
}
