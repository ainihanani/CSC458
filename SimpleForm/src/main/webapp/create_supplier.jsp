<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Create Supplier</h2>
		<form method="post" action="CreateSupplierServlet">
			<label for="supplierName">Supplier Name:</label> <input type="text"
				id="supplierName" name="supplierName" required><br> <label
				for="supplierAddress">Supplier Address:</label> <input type="text"
				id="supplierAddress" name="supplierAddress" required><br>

			<label for="supplierEmail">Supplier Email:</label> <input
				type="email" id="supplierEmail" name="supplierEmail" required><br>

			<input type="submit" value="Create Supplier">
		</form>

	</div>
</section>
