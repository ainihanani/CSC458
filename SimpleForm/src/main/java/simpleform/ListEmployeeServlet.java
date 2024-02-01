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

@WebServlet("/ListEmployeeServlet")
public class ListEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Employee> employees = getEmployeesFromDatabase();
            request.setAttribute("employees", employees);

            // Forward the request to the JSP
            request.getRequestDispatcher("/list_emp.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error: Unable to retrieve employee list");
        }
    }

    private List<Employee> getEmployeesFromDatabase() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmpId(resultSet.getInt("empId"));
                employee.setEmpName(resultSet.getString("empName"));
                employee.setEmpPosition(resultSet.getString("empPosition"));
                employee.setEmpNoTel(resultSet.getString("empNoTel"));
                employees.add(employee);
            }
        }

        return employees;
    }
}
