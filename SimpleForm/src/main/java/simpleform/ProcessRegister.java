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

@WebServlet("/ProcessRegister")
public class ProcessRegister extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        String position = req.getParameter("position"); // Updated from gender to position
        String password = req.getParameter("password");

        try {
            Connection connection = DBUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users (name, email, telephone, position, password) VALUES (?, ?, ?, ?, ?)"
            );
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, telephone);
            statement.setString(4, position); // Updated from gender to position
            statement.setString(5, password);

            int rowsAffected = statement.executeUpdate();

            out.println("<html>");
            out.println("   <head>");
            out.println("       <title>User Registration Form</title>");
            out.println("       <script>alert('Registration successful!'); window.location.href='login.html';</script>");
            out.println("   </head>");
            out.println("   <body>");
            out.println("   <h1>User Registration Form</h1>");

            if (rowsAffected > 0) {
                out.println("   <p>Registration successful!</p>");
            } else {
                out.println("   <p>Registration failed. Please try again.</p>");
            }

            out.println("   </body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Error: Unable to register user");
        }
    }
}
