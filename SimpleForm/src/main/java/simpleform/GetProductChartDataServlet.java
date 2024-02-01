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

@WebServlet("/GetProductChartDataServlet")
public class GetProductChartDataServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        List<ProductChartData> chartDataList = new ArrayList<>();

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT productName, dateAdded, SUM(quantity) as quantity FROM products GROUP BY productName, dateAdded");
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                ProductChartData chartData = new ProductChartData();
                chartData.setProductName(resultSet.getString("productName"));
                chartData.setDateAdded(resultSet.getString("dateAdded"));
                chartData.setQuantity(resultSet.getInt("quantity"));
                chartDataList.add(chartData);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Convert the list to JSON and send it as the response
        out.print("[");
        for (int i = 0; i < chartDataList.size(); i++) {
            ProductChartData chartData = chartDataList.get(i);
            out.print("{\"productName\":\"" + chartData.getProductName() + "\",\"date\":\"" + chartData.getDateAdded() + "\",\"quantity\":" + chartData.getQuantity() + "}");
            if (i < chartDataList.size() - 1) {
                out.print(",");
            }
        }
        out.print("]");
    }
}
