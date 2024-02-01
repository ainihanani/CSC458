package simpleform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdateOrderServlet")
public class UpdateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE orderId = ?");
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("orderId", resultSet.getInt("orderId"));
                request.setAttribute("orderDate", resultSet.getDate("date"));
                request.setAttribute("amount", resultSet.getDouble("amount"));
                request.setAttribute("status", resultSet.getString("status"));
            }

            request.getRequestDispatcher("/update_order.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to retrieve order data");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        java.util.Date orderDate = java.sql.Date.valueOf(request.getParameter("orderDate"));
        double amount = Double.parseDouble(request.getParameter("amount"));
        String status = request.getParameter("status");

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE orders SET date = ?, amount = ?, status = ? WHERE orderId = ?");
            statement.setDate(1, new java.sql.Date(orderDate.getTime()));
            statement.setDouble(2, amount);
            statement.setString(3, status);
            statement.setInt(4, orderId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("ListOrderServlet?message=success");
            } else {
                response.sendRedirect("ListOrderServlet?message=error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to update order information");
        }
    }
}
