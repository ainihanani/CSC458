<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Update Supplier</h2>
		<form method="post" action="UpdateSupplierServlet">
			<label for="supplierName">Supplier Name:</label> <input type="text"
				id="supplierName" name="supplierName"
				value="<%= request.getAttribute("supplierName") %>" required><br>

			<label for="supplierAddress">Supplier Address:</label> <input
				type="text" id="supplierAddress" name="supplierAddress"
				value="<%= request.getAttribute("supplierAddress") %>" required><br>

			<label for="supplierEmail">Supplier Email:</label> <input
				type="email" id="supplierEmail" name="supplierEmail"
				value="<%= request.getAttribute("supplierEmail") %>" required><br>

			<input type="hidden" name="supplierId"
				value="<%= request.getAttribute("supplierId") %>"> <input
				type="submit" value="Update Supplier">
		</form>
	</div>
</section>
