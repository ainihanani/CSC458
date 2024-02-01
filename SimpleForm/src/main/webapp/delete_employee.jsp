<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<section>
	<div class="container">
		<h2>Delete Employee</h2>
		<p>Are you sure you want to delete this employee?</p>
		<p>
			Name:
			<%= request.getAttribute("empName") %></p>
		<p>
			Position:
			<%= request.getAttribute("empPosition") %></p>
		<p>
			No Telephone:
			<%= request.getAttribute("empTelephone") %></p>

		<form method="post" action="DeleteEmployeeServlet">
			<input type="hidden" name="empId"
				value="<%= request.getAttribute("empId") %>"> <input
				type="submit" value="Delete Employee">
		</form>
	</div>
</section>
