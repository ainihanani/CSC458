<%@ page import="java.sql.*, java.text.SimpleDateFormat"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>List of Orders</title>

<!-- Styles -->
<style>
table {
	width: 100%;
	border-collapse: collapse;
}

th, td {
	border: 1px solid black;
	padding: 8px;
	text-align: center;
}

.button {
	background-color: purple;
	border: none;
	color: white;
	padding: 8px 12px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 14px;
	border-radius: 5px;
	cursor: pointer;
}
</style>
</head>
<body>
	<div class="container">
		<h2>List of Orders</h2>

		<!-- Check for success or error message -->
		<% 
            String message = request.getParameter("message");
            if (message != null && message.equals("success")) {
        %>
		<div
			style="background-color: #4CAF50; color: white; padding: 10px; border-radius: 5px; text-align: center;">
			Order operation successful!</div>
		<% } else if (message != null && message.equals("error")) { %>
		<div
			style="background-color: #FF5733; color: white; padding: 10px; border-radius: 5px; text-align: center;">
			Error performing operation. Please try again.</div>
		<% } %>

		<table>
			<tr>
				<th>Order ID</th>
				<th>Date</th>
				<th>Total Amount (RM)</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>

			<% 
                String dbUrl = "jdbc:mysql://localhost:3306/test";
                String dbUser = "root"; 
                String dbPass = "";
                
                Connection conn = null;
                PreparedStatement pstmt = null;
                ResultSet rs = null;
                
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                    String sql = "SELECT * FROM orders";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    
                    while (rs.next()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        String formattedDate = sdf.format(rs.getDate("date"));
            %>
			<tr>
				<td><%= rs.getInt("orderId") %></td>
				<td><%= formattedDate %></td>
				<td>RM <%= rs.getDouble("amount") %></td>
				<td><%= rs.getString("status") %></td>
				<td><a
					href="UpdateOrderServlet?orderId=<%= rs.getInt("orderId") %>"
					class="button">Update</a> <a
					href="DeleteOrderServlet?orderId=<%= rs.getInt("orderId") %>"
					class="button" style="background-color: red;">Delete</a></td>
			</tr>
			<% 
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (pstmt != null) pstmt.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            %>
		</table>

		<br> <a href="homepage.jsp" class="button"
			style="background-color: #3498db;">Back to Dashboard</a> <a
			href="create_order.jsp" class="button"
			style="background-color: #3498db;">Create Order</a>
	</div>
</body>
</html>
