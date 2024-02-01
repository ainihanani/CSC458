package simpleform;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ProcessEmployee")
public class ProcessEmployee extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handling GET request
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Create Employee - GET</title></head>");
        out.println("<body>");
        out.println("<h1>Create Employee - GET</h1>");
        out.println("<p>This page should be accessed via the form.</p>");
        out.println("</body>");
        out.println("</html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handling POST request
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empName = request.getParameter("empName");
        String empPosition = request.getParameter("empPosition");
        String empNoTel = request.getParameter("empNoTel"); // Changed from empSalary to empNoTel

        // Create an Employee object
        Employee employee = new Employee();
        employee.setEmpName(empName);
        employee.setEmpPosition(empPosition);
        employee.setEmpNoTel(empNoTel);

        // Save the employee information to the database
        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO employees (empName, empPosition, empNoTel) VALUES (?, ?, ?)");
            statement.setString(1, employee.getEmpName());
            statement.setString(2, employee.getEmpPosition());
            statement.setString(3, employee.getEmpNoTel());

            int rowsAffected = statement.executeUpdate();

            out.println("<html>");
            out.println("<head><title>Create Employee - POST</title></head>");
            out.println("<body>");
            out.println("<h1>Create Employee - POST</h1>");

            if (rowsAffected > 0) {
                out.println("<p>Employee information saved in the database.</p>");
            } else {
                out.println("<p>Failed to save employee information. Please try again.</p>");
            }

            out.println("<p><a href=\"list_emp.jsp\">View Employee List</a></p>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: Unable to save employee information");
        }
    }
}
