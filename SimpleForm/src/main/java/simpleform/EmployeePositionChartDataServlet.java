package simpleform;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/EmployeePositionChartDataServlet")
public class EmployeePositionChartDataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<EmployeePositionData> positions = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT empPosition, COUNT(*) as count FROM employees GROUP BY empPosition");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                EmployeePositionData positionData = new EmployeePositionData();
                positionData.setPosition(resultSet.getString("empPosition"));
                positionData.setCount(resultSet.getInt("count"));
                positions.add(positionData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the list to JSON and send it as the response
        out.print("[");
        for (int i = 0; i < positions.size(); i++) {
            out.print("{\"position\":\"" + positions.get(i).getPosition() + "\",\"count\":" + positions.get(i).getCount() + "}");
            if (i < positions.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
    }
}
