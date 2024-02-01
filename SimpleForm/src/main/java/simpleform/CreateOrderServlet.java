package simpleform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CreateOrderServlet")
public class CreateOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String orderIdStr = request.getParameter("orderId");
            String orderDateStr = request.getParameter("orderDate");
            String amountStr = request.getParameter("amount");
            String status = request.getParameter("status");

            // Validate orderId, orderDate, and amount
            int orderId = 0;
            try {
                orderId = Integer.parseInt(orderIdStr);
            } catch (NumberFormatException e) {
                // Handle invalid orderId (not an integer)
                response.sendRedirect("create_order.jsp?message=error");
                return;
            }

            java.util.Date orderDate = java.sql.Date.valueOf(orderDateStr);

            double amount = 0.0;
            try {
                amount = Double.parseDouble(amountStr);
            } catch (NumberFormatException e) {
                // Handle invalid amount (not a double)
                response.sendRedirect("create_order.jsp?message=error");
                return;
            }

            String dbUrl = "jdbc:mysql://localhost:3306/test";
            String dbUser = "root";
            String dbPass = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            // SQL query to insert the new order
            String sql = "INSERT INTO orders (orderId, date, amount, status) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, orderId);
            pstmt.setDate(2, new java.sql.Date(orderDate.getTime()));
            pstmt.setDouble(3, amount);
            pstmt.setString(4, status);

            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

            // Redirect to the list_order page on success
            response.sendRedirect(request.getContextPath() + "/ListOrderServlet");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("create_order.jsp?message=error");
        }
    }
}
