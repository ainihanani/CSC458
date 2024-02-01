<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Delete Product</h2>
		<p>Are you sure you want to delete this product?</p>
		<p>
			Name:
			<%= request.getAttribute("productName") %></p>
		<p>
			Quantity:
			<%= request.getAttribute("quantity") %></p>
		<p>
			Price:
			<%= request.getAttribute("productPrice") %></p>

		<form method="post" action="DeleteProductServlet">
			<input type="hidden" name="productId"
				value="<%= request.getAttribute("productId") %>"> <input
				type="submit" value="Delete Product">
		</form>
	</div>
</section>
