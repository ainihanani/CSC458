<%@ page import="java.sql.*, java.time.LocalDate"%>
<%@ include file="header.jsp"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>List of Products</title>

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
		<h2>List of Products</h2>

		<!-- Check for success or error message -->
		<% 
            String message = request.getParameter("message");
            if (message != null && message.equals("success")) {
        %>
		<div
			style="background-color: #4CAF50; color: white; padding: 10px; border-radius: 5px; text-align: center;">
			Product operation successful!</div>
		<% } else if (message != null && message.equals("error")) { %>
		<div
			style="background-color: #FF5733; color: white; padding: 10px; border-radius: 5px; text-align: center;">
			Error performing operation. Please try again.</div>
		<% } %>

		<table>
			<tr>
				<th>Product ID</th>
				<th>Name</th>
				<th>Quantity (Unit)</th>
				<th>Price (RM)</th>
				<th>Total Amount (RM)</th>
				<th>Date Added</th>
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
                    String sql = "SELECT *, (quantity * productPrice) as totalAmount FROM products";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    
                    while (rs.next()) {
            %>
			<tr>
				<td><%= rs.getInt("productId") %></td>
				<td><%= rs.getString("productName") %></td>
				<td><%= rs.getInt("quantity") %> (Unit)</td>
				<td>RM <%= rs.getDouble("productPrice") %></td>
				<td>RM <%= rs.getDouble("totalAmount") %></td>
				<td><%= rs.getDate("dateAdded") %></td>
				<td><a
					href="UpdateProductServlet?productId=<%= rs.getInt("productId") %>"
					class="button">Update</a> <a
					href="DeleteProductServlet?productId=<%= rs.getInt("productId") %>"
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
			href="create_product.jsp" class="button"
			style="background-color: #3498db;">Create Product</a>
	</div>
	</section>
</body>
</html>
