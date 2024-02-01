package simpleform; // Replace with your actual package name

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

@WebServlet("/CreateEmployeeServlet")
public class CreateEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String empName = request.getParameter("empName");
        String empPosition = request.getParameter("empPosition");
        String empNoTel = request.getParameter("empTelephone");

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO employees (empName, empPosition, empNoTel) VALUES (?, ?, ?)");
            statement.setString(1, empName);
            statement.setString(2, empPosition);
            statement.setString(3, empNoTel);

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
