<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Delete Supplier</h2>
		<p>Are you sure you want to delete this supplier?</p>
		<p>
			Name:
			<%= request.getAttribute("supplierName") %></p>
		<p>
			Address:
			<%= request.getAttribute("supplierAddress") %></p>
		<p>
			Email:
			<%= request.getAttribute("supplierEmail") %></p>

		<form method="post" action="DeleteSupplierServlet">
			<input type="hidden" name="supplierId"
				value="<%= request.getAttribute("supplierId") %>"> <input
				type="submit" value="Delete Supplier">
		</form>
	</div>
</section>
