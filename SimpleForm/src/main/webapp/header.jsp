<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stationery Management System</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-image: url('images/background.jpg');
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 15px;
        }

        nav {
            width: 250px;
            height: 100%;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #555;
            padding-top: 100px;
            padding-left: 10px;
            box-sizing: border-box;
        }

        nav a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            margin-bottom: 5px;
            border-bottom: 1px solid #444;
        }

        section {
            margin-left: 300px;
            padding: 20px;
        }

        /* Container for the form */
        .container {
            width: 50%;
            margin: 0 auto;
            background-color: #f5f5f5;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        /* Form styles */
        form {
            display: grid;
            grid-gap: 15px;
        }

        label {
            font-weight: bold;
            display: block;
        }

        input[type="text"], input[type="tel"], input[type="date"] {
            width: 100%;
            padding: 10px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #333;
            color: white;
            padding: 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>

    <header>
        <h1>Stationery Inventory Management</h1>
    </header>

    <nav>
        <a href="#">Home</a>
        <a href="create_emp.jsp">Create Employee</a>
        <a href="list_emp.jsp">List Employee</a>
        <a href="create_supplier.jsp">Create Supplier</a>
        <a href="list_supplier.jsp">List Supplier</a>
        <a href="create_product.jsp">Create Product</a>
        <a href="list_product.jsp">List Product</a>
        <a href="create_order.jsp">Create Order</a> <!-- Added link for Create Order -->
        <a href="list_order.jsp">List Order</a> <!-- Added link for List Order -->
        <a href="logout.jsp">Logout</a>
        <!-- Updated Logout link -->
    </nav>
