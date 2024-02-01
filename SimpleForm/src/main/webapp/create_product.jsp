<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Create Product</h2>
		<form method="post" action="CreateProductServlet">
			<label for="productName">Product Name:</label> <input type="text"
				id="productName" name="productName" required><br> <label
				for="quantity">Quantity:</label> <input type="number" id="quantity"
				name="quantity" required><br> <label for="productPrice">Product
				Price:</label> <input type="number" id="productPrice" name="productPrice"
				required><br> <input type="submit"
				value="Create Product">
		</form>

	</div>
</section>
