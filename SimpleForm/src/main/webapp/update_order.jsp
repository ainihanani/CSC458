<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
<html>
<head>
    <title>Update Order</title>
    <!-- Add any additional styling or scripts if needed -->
</head>
<body>

<div class="container">
    <h2>Update Order</h2>

    <!-- Check for success or error message -->
    <% 
        String message = request.getParameter("message");
        if (message != null && message.equals("error")) {
    %>
    <div style="background-color: #FF5733; color: white; padding: 10px; border-radius: 5px; text-align: center;">
        Error updating order. Please check your input and try again.
    </div>
    <% } %>

    <!-- Your HTML form for updating an order -->
    <form action="UpdateOrderServlet" method="post">
        <label for="orderId">Order ID:</label>
        <input type="text" id="orderId" name="orderId" value="<%= request.getAttribute("orderId") %>" readonly>

        <label for="orderDate">Order Date:</label>
        <input type="date" id="orderDate" name="orderDate" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(request.getAttribute("orderDate")) %>" required>

        <label for="amount">Total Amount:</label>
        <input type="text" id="amount" name="amount" value="<%= request.getAttribute("amount") %>" required>

        <label for="status">Status:</label>
        <select id="status" name="status" required>
            <option value="Processing" <%= ((String) request.getAttribute("status")).equals("Processing") ? "selected" : "" %>>Processing</option>
            <option value="Shipped" <%= ((String) request.getAttribute("status")).equals("Shipped") ? "selected" : "" %>>Shipped</option>
        </select>

        <input type="submit" value="Update Order">
    </form>
</div>

</body>
</html>
