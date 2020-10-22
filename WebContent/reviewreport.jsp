<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%@ page import="java.util.ArrayList, kevin.model.Review" %>
<%

	ArrayList<Review> reviews = new ArrayList<>();
	reviews = (ArrayList<Review>) request.getAttribute("reviews");
	
	String startDate = (String) request.getAttribute("startdate");
	String endDate = (String) request.getAttribute("endDate");
	
	int count = reviews.size();
	int rate[] = new int[5];
	int sum = 0;
	
	for (Review r : reviews) {
		
		for (int i = 0 ; i < rate.length ; i++) {
			if (r.getRating() == i + 1)
				rate[i]++;
		}
		
		sum += r.getRating();
		
	}
	
	double average = (double) sum / count;
	
%>
<head>
<meta charset="UTF-8">
<title>KEVIN | Review Report</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="fontawesome/css/all.css">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
<script type="text/javascript" src="js/jquery-2.1.3.js"></script>
<script type="text/javascript" src="js/jspdf.js"></script>
<script type="text/javascript" src="js/pdfFromHTML.js"></script>
<script type="text/javascript" src="gstatic/loader.js"></script>
<script type="text/javascript">

google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);


function drawChart() {
  var data = google.visualization.arrayToDataTable([
  ['Review', 'Rating'],
  ['5 STAR', <%=rate[4]%>],
  ['4 STAR', <%=rate[3]%>],
  ['3 STAR', <%=rate[2]%>],
  ['2 STAR', <%=rate[1]%>],
  ['1 STAR', <%=rate[0]%>]
]);

  
  var options = {'title':'Reviews', 'width':750, 'height':600};

  
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>
<style>

.rtable {
	margin: 5vw auto;
	margin-bottom: 2vw;
	border-collapse: collapse;
	font-size: 1.5vw;
}

.td {
	border: 0.16vw solid grey;
	padding: 1vw;
}

</style>
</head>
<body style="text-align: center">
	<h1 style="grey">Review Report from <span style="color: red"><%=startDate%></span> to <span style="color: red"><%=endDate%></span></h1>
	<div id="piechart" style="float: left"></div>
	<div id="HTMLtoPDF">
		<h3 hidden>Review Report from <%=startDate%> to <%=endDate%></h1>
		<table class="rtable">
			<tr><td class="td">Review Category</td><td class="td">Count</td></tr>
			<tr><td class="td">5 Star Reviews</td><td class="td"><%=rate[4]%></td></tr>
			<tr><td class="td">4 Star Reviews</td><td class="td"><%=rate[3]%></td></tr>
			<tr><td class="td">3 Star Reviews</td><td class="td"><%=rate[2]%></td></tr>
			<tr><td class="td">2 Star Reviews</td><td class="td"><%=rate[1]%></td></tr>
			<tr><td class="td">1 Star Reviews</td><td class="td"><%=rate[0]%></td></tr>
			<tr><td class="td">Total Reviews</td><td class="td"><%=count%></td></tr>
			<tr><td class="td">Average Rating</td><td class="td"><%=average%></td></tr>
		</table>
	</div>
	<a href="#" onclick="HTMLtoPDF()">Download PDF</a>
</body>
</html>