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

@WebServlet("/DeleteEmployeeServlet")
public class DeleteEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empId = Integer.parseInt(request.getParameter("empId"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM employees WHERE empId = ?");
            statement.setInt(1, empId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("list_emp.jsp?message=success");
            } else {
                response.sendRedirect("list_emp.jsp?message=error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to delete employee");
        }
    }
}
