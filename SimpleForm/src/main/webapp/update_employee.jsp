<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Update Employee</h2>
		<form method="post" action="UpdateEmployeeServlet">
			<input type="hidden" name="empId"
				value="<%= request.getAttribute("empId") %>"> <label
				for="empName">Name:</label> <input type="text" id="empName"
				name="empName" value="<%= request.getAttribute("empName") %>"
				required><br> <label for="empPosition">Position:</label>
			<input type="text" id="empPosition" name="empPosition"
				value="<%= request.getAttribute("empPosition") %>" required><br>

			<label for="empNoTel">No Telephone:</label> <input type="tel"
				id="empNoTel" name="empNoTel"
				value="<%= request.getAttribute("empNoTel") %>" required><br>

			<input type="submit" value="Update Employee">
		</form>
	</div>
</section>
