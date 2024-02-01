<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="header.jsp"%>

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

<style>
.dashboard-box {
	border: 1px solid #ddd;
	padding: 20px;
	margin: 10px;
	text-align: center;
	border-radius: 5px;
}

h3 {
	color: #3498db;
}

p {
	font-size: 24px;
	font-weight: bold;
	color: #333;
}

.chart-container {
	width: 80%;
	max-width: 800px;
	margin: 20px auto;
	background-color: #fff; /* White background */
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.bar-container {
	display: flex;
	flex-direction: row;
	align-items: flex-end;
	height: 200px; /* Adjust the height as needed */
}

.bar {
	flex: 1;
	background-color: #000; /* Change to black color */
	margin-right: 5px;
	position: relative;
}

.bar-text {
	position: absolute;
	bottom: 5px;
	left: 50%;
	transform: translateX(-50%);
	color: #fff;
}

.chart-label {
	font-size: 14px;
	margin-top: 5px;
	text-align: center;
}
</style>
</head>
<body>

	<section>
		<div class="container">
			<h2>Dashboard</h2>

			<div class="dashboard-box">
				<h3>Registered Users</h3>
				<p><%= new simpleform.UserCountRetriever().getCount() %></p>
			</div>

			<div class="dashboard-box">
				<h3>Registered Employees</h3>
				<p><%= new simpleform.EmployeeCountRetriever().getCount() %></p>
			</div>

			<div class="dashboard-box">
				<h3>Registered Suppliers</h3>
				<p><%= new simpleform.SupplierCountRetriever().getCount() %></p>
			</div>

			<div class="dashboard-box">
				<h3>Registered Products</h3>
				<p><%= new simpleform.ProductCountRetriever().getCount() %></p>
			</div>
			
			<div class="dashboard-box">
				<h3>Total Orders</h3>
				<p><%= new simpleform.OrderCountRetriever().getCount() %></p>
			</div>
			
		</div>

		<!-- Vertical Bar Chart in a white box -->
		<div class="chart-container">
			<h3>Product Quantity Over Time</h3>
			<div class="bar-container" id="verticalChart"></div>
			<div class="chart-label">Date</div>
		</div>

		<!-- Bar Chart for Employee Positions -->
		<div class="chart-container">
			<h3>Employee Positions</h3>
			<div class="bar-container" id="employeePositionChart"></div>
			<div class="chart-label">Position</div>
		</div>

		<script>
        function getProductChartData() {
            var url = "GetProductChartDataServlet";

            fetch(url)
                .then(response => response.json())
                .then(data => {
                    createVerticalChart(data);
                })
                .catch(error => console.error('Error fetching data:', error));
        }

        function getEmployeePositionData() {
            var url = "EmployeePositionChartDataServlet";

            fetch(url)
                .then(response => response.json())
                .then(data => {
                    createBarChart(data);
                })
                .catch(error => console.error('Error fetching employee position data:', error));
        }

        function createVerticalChart(data) {
            var chartContainer = document.getElementById('verticalChart');
            var maxValue = Math.max(...data.map(item => item.quantity));

            data.forEach(item => {
                var bar = document.createElement('div');
                bar.className = 'bar';
                bar.style.height = (item.quantity / maxValue) * 100 + '%';

                var barText = document.createElement('div');
                barText.className = 'bar-text';
                barText.textContent = item.quantity;

                bar.appendChild(barText);
                chartContainer.appendChild(bar);

                var label = document.createElement('div');
                label.className = 'chart-label';
                label.textContent = item.date;
                chartContainer.appendChild(label);
            });
        }

        function createBarChart(data) {
            var chartContainer = document.getElementById('employeePositionChart');
            var maxValue = Math.max(...data.map(item => item.count));

            data.forEach(item => {
                var bar = document.createElement('div');
                bar.className = 'bar';
                bar.style.height = (item.count / maxValue) * 100 + '%';

                var barText = document.createElement('div');
                barText.className = 'bar-text';
                barText.textContent = item.count;

                bar.appendChild(barText);
                chartContainer.appendChild(bar);

                var label = document.createElement('div');
                label.className = 'chart-label';
                label.textContent = item.position;
                chartContainer.appendChild(label);
            });
        }

        getProductChartData();
        getEmployeePositionData();
    </script>
	</section>

</body>
</html>
