<%@ page import="java.sql.*" %>
<%@ include file="header.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete Order</title>
    <!-- Add any additional styling or scripts if needed -->
</head>
<body>

    <div class="container">
        <h2>Delete Order</h2>

        <!-- Check for success or error message -->
        <% 
            String message = request.getParameter("message");
            if (message != null && message.equals("error")) {
        %>
        <div style="background-color: #FF5733; color: white; padding: 10px; border-radius: 5px; text-align: center;">
            Error deleting order. Please try again.</div>
        <% } %>

        <!-- Your HTML form for deleting an order -->
        <form action="DeleteOrderServlet" method="get">
            <label for="orderId">Order ID to Delete:</label>
            <input type="text" id="orderId" name="orderId" required>
            <input type="submit" value="Delete Order">
        </form>

        <br> <a href="homepage.jsp" class="button" style="background-color: #3498db;">Back to Dashboard</a>
        <a href="list_order.jsp" class="button" style="background-color: #3498db;">View Order List</a>
    </div>

</body>
</html>
