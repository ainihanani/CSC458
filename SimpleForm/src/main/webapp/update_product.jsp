<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Update Product</h2>
		<form method="post" action="UpdateProductServlet">
			<label for="productName">Product Name:</label> <input type="text"
				id="productName" name="productName"
				value="<%= request.getAttribute("productName") %>" required><br>

			<label for="quantity">Quantity:</label> <input type="number"
				id="quantity" name="quantity"
				value="<%= request.getAttribute("quantity") %>" required><br>

			<label for="productPrice">Product Price:</label> <input type="number"
				id="productPrice" name="productPrice"
				value="<%= request.getAttribute("productPrice") %>" required><br>

			<input type="hidden" name="productId"
				value="<%= request.getAttribute("productId") %>"> <input
				type="submit" value="Update Product">
		</form>
	</div>
</section>
