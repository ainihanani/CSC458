package simpleform;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

@WebServlet("/ListOrderServlet")
public class ListOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String dbUrl = "jdbc:mysql://localhost:3306/test";
            String dbUser = "root";
            String dbPass = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);

            // SQL query to retrieve all orders
            String sql = "SELECT * FROM orders";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            // Create a list to store Order objects
            List<Order> orderList = new ArrayList<>();

            // Populate the list with orders from the result set
            while (rs.next()) {
                int orderId = rs.getInt("orderId");
                java.util.Date orderDate = rs.getDate("date");
                double amount = rs.getDouble("amount");
                String status = rs.getString("status");

                // Create Order object and add to the list
                Order order = new Order(orderId, orderDate, amount, status);
                orderList.add(order);
            }

            rs.close();
            pstmt.close();
            conn.close();

            // Set the order list as a request attribute
            request.setAttribute("orderList", orderList);

            // Forward the request to the JSP for rendering
            request.getRequestDispatcher("list_order.jsp").forward(request, response);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            response.sendRedirect("list_order.jsp?message=error");
        }
    }
}
