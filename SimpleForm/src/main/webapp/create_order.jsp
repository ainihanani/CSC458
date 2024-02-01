<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<html>
<head>
    <title>Create Order</title>
    <!-- Add any additional styling or scripts if needed -->
</head>
<body>

    <div class="container">
        <h2>Create Order</h2>

        <!-- Check for success or error message -->
        <% 
            String message = request.getParameter("message");
            if (message != null && message.equals("error")) {
        %>
        <div style="background-color: #FF5733; color: white; padding: 10px; border-radius: 5px; text-align: center;">
            Error creating order. Please check your input and try again.</div>
        <% } %>

        <!-- Your HTML form for creating an order -->
        <form action="CreateOrderServlet" method="post">
            <label for="orderId">Order ID:</label>
            <input type="text" id="orderId" name="orderId" required>

            <label for="orderDate">Order Date:</label>
            <input type="date" id="orderDate" name="orderDate" required>

            <label for="amount">Total Amount:</label>
            <input type="text" id="amount" name="amount" required>

            <label for="status">Status:</label>
            <select id="status" name="status" required>
                <option value="Processing">Processing</option>
                <option value="Shipped">Shipped</option>
            </select>

            <input type="submit" value="Create Order">
        </form>
    </div>

</body>
</html>
