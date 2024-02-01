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

@WebServlet("/DeleteOrderServlet")
public class DeleteOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE orderId = ?");
            statement.setInt(1, orderId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("ListOrderServlet?message=success");
            } else {
                response.sendRedirect("ListOrderServlet?message=error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to delete order");
        }
    }
}
