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

@WebServlet("/UpdateEmployeeServlet")
public class UpdateEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empId = Integer.parseInt(request.getParameter("empId"));

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE empId = ?");
            statement.setInt(1, empId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                request.setAttribute("empId", resultSet.getInt("empId"));
                request.setAttribute("empName", resultSet.getString("empName"));
                request.setAttribute("empPosition", resultSet.getString("empPosition"));
                request.setAttribute("empNoTel", resultSet.getString("empNoTel"));
            }

            request.getRequestDispatcher("/update_employee.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to retrieve employee data");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int empId = Integer.parseInt(request.getParameter("empId"));
        String empName = request.getParameter("empName");
        String empPosition = request.getParameter("empPosition");
        String empNoTel = request.getParameter("empNoTel");

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE employees SET empName = ?, empPosition = ?, empNoTel = ? WHERE empId = ?");
            statement.setString(1, empName);
            statement.setString(2, empPosition);
            statement.setString(3, empNoTel);
            statement.setInt(4, empId);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                response.sendRedirect("list_emp.jsp?message=success");
            } else {
                response.sendRedirect("list_emp.jsp?message=error");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to update employee information");
        }
    }
}
