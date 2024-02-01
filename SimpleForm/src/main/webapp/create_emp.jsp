<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Create Employee</h2>
		<form method="post" action="CreateEmployeeServlet">
			<label for="empName">Name:</label> <input type="text" id="empName"
				name="empName" required><br> <label for="empPosition">Position:</label>
			<input type="text" id="empPosition" name="empPosition" required><br>

			<label for="empTelephone">No Telephone:</label> <input type="tel"
				id="empTelephone" name="empTelephone" required><br> <input
				type="submit" value="Create Employee">
		</form>
	</div>
</section>
