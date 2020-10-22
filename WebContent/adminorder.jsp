<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="fontawesome/css/all.css">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    <script src="js/alert.js"></script>
    <script src="js/confirm.js"></script>
    <script src="js/prompt.js"></script>
    <script src="js/log.js"></script>
    <script src="js/item.js"></script>
    <script type="text/javascript">
    window.onload = function() {
    	
    	document.getElementById("do").disabled = true;
    	
    	/*----------LOAD ORDERS----------*/
    	var xhttp = new XMLHttpRequest();

    	xhttp.onreadystatechange = function() {

    		if (this.readyState == 4 && this.status == 200) {

    			var items = JSON.parse(this.responseText);
    			var doc = document.getElementById("olist");
    			doc.innerHTML = "<option value='' disabled selected>Select Order</option>";

    			items.forEach(function(item) {
    				doc.innerHTML += "<option value=" + item.id + ">" + item.id
    						+ " - " + item.description + "</option>";
    			})
    		}

    	};

    	xhttp.open("GET", "GetAllOrdersServlet", true);
    	xhttp.send();
    	
    }
    
    function getOrderDetails(oFormElement) {

    	var value = document.getElementById("olist").value;
    	document.getElementById("do").disabled = false;

    	if (value != "") {

    		document.getElementById("orid").value = value;

    		var xhr = new XMLHttpRequest();
    		xhr.onerror = function() {
    			Alert.render(xhr.responseText);
    		}

    		xhr.onreadystatechange = function() {
    			if (this.readyState == 4 && this.status == 200) {

    				document.getElementById("orderdiv").hidden = false;

    				var item = JSON.parse(this.responseText);
    				var doc = document.getElementById("orderdiv");
    				var status, cost, payed;

    				if (item.cost == -1)
    					cost = "Not Accepted";
    				else
    					cost = item.cost;
    				
    				if (item.accepted == false)
    					status = "Not Accepted";
    				else
    					status = "Accepted";

    				if (item.payed == false)
    					payed = "Not Payed";
    				else
    					payed = "Payed";   				

    				doc.innerHTML = "<div class='or' style='padding-bottom: 2.2vw; height: 27vw'><div style='grid-row: 1/2'><img src='"
    						+ item.imagePath
    						+ "' style='width: 100%; height: 100%'></div><div style='grid-row: 2/3; padding: 1vw;'><table style='margin: auto; border-spacing: 0.5vw; text-align: start'><tr><td><b>"
    						+ "Quantity"
    						+ "<td><b> : </b></td>"
    						+ "</b></td><td>"
    						+ item.quantity
    						+ "</td></tr><tr><td><b>"
    						+ "Status"
    						+ "<td><b> : </b></td>"
    						+ "</b></td><td>"
    						+ status
    						+ "</td></tr><td><b>"
    						+ "Cost"
    						+ "<td><b> : </b></td>"
    						+ "</b></td><td>"
    						+ cost
    						+ "</td></tr><td><b>"
    						+ "Payment"
    						+ "<td><b> : </b></td>"
    						+ "</b></td><td>"
    						+ payed
    						+ "</td></tr><td><b>"
    						+ "Description"
    						+ "<td><b> : </b></td>"
    						+ "</b></td><td>"
    						+ item.description
    						+ "</td></tr>"
    						+ "</table></div></div>";

    			}
    		};

    		xhr.open(oFormElement.method, oFormElement.action, true);
    		xhr.setRequestHeader("Content-type",
    				"application/x-www-form-urlencoded");
    		xhr.send("orderId=" + value);

    	}

    }
    
    function AcceptOrder() {

    	this.render = function(dialog, servlet) {
    		
    		if (document.getElementById("olist").value != "") {

    			var winW = window.innerWidth;
    			var winH = window.innerHeight;
    			var dialogoverlay = document.getElementById('overlay');
    			var dialogbox = document.getElementById('alertbox');
    			dialogoverlay.style.display = "block";
    			dialogoverlay.style.height = winH + "px";
    			dialogbox.style.left = (winW / 2) - (550 * .5) + "px";
    			dialogbox.style.top = "30%";
    			dialogbox.style.display = "block";
    			document.getElementById('alertboxhead').innerHTML = '<img src="img/logo.png" width="164vw" height="33vw">';
    			document.getElementById('alertboxbody').innerHTML = '<form id="val" method="post" action="'
    					+ servlet
    					+ '"><input autocomplete="off" required type="number" min="20" class="textbox" name="cost" style="width: 80%; margin-top: 1vh;" placeholder="'
    					+ dialog
    					+ '"><input hidden name="id" type="number" step="0.01" min="0" value="'
    					+ document.getElementById("olist").value + '"></form>';
    			document.getElementById('alertboxfoot').innerHTML = '<button class="button" onclick="Order.ok()">OK</button>&emsp;&emsp;<button class="button" onclick="Order.cancel()">Cancel</button>';

    		} else {
    			
    			Alert.render('Please Select an Order');
    			
    		}

    	}

    	this.cancel = function() {

    		document.getElementById('alertbox').style.display = "none";
    		document.getElementById('overlay').style.display = "none";

    	}

    	this.ok = function() {

    		document.forms['val'].submit();
    		document.getElementById('alertbox').style.display = "none";
    		document.getElementById('overlay').style.display = "none";

    	}

    }

    var Order = new AcceptOrder();
    </script>
</head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, java.util.ArrayList, kevin.service.OrderService, kevin.model.Order" %>
<% 
	HttpSession logged = request.getSession(false);

	if (logged.getAttribute("admin") == null)
		response.sendRedirect("account.jsp");

	ArrayList<Order> orders;
	orders = OrderService.getOrders();
	
	
%>
<body>
    <header>
    <div id="overlay"></div>
        <div id="alertbox">
            <div>
                <div id="alertboxhead"></div>
                <div id="alertboxbody"></div>
                <div id="alertboxfoot"></div>
            </div>
        </div>
        <div id="logbox" style="width: 26vw">
            <div>
                <div id="logboxhead" style="padding: 0.5vw;">
                    <button style="float: right; border: none; background-color: transparent; outline: none; cursor: pointer; font-size: 1.5vw; color: rgba(254, 0, 2, 1);"
                        onclick="Login.close()">✖</button>
                    <h3 style="transform: translateX(5%)"><img src="img/logo.png" width="164vw" height="33vw"></h3>
                </div>
                <form method="POST" action="LoginServlet">
                    <div id="logboxbody" style="padding: 0.5vw">
                        <input required type="text" name="email" class="textbox" placeholder="E-Mail" autocomplete="off" style="margin: 0.5vw 1vw; width: 20vw">
                        <input required type="password" name="password" class="textbox" placeholder="Password" autocomplete="off" style="margin: 0.5vw 1vw; width: 20vw">
                    </div>
                    <div id="logboxfoot" style="text-align: center; padding: 0.5vw;">
                        <button class="button" type="submit"><i class="fas fa-sign-in-alt">&ensp;</i>Login</button>
                    </div>
                </form>
            </div>
        </div>
        <nav class="navbar">
        <a href="admin.jsp"><i class="fas fa-table"></i>&ensp;Admin Home</a>
            <div class="innav">
            <% 
            	if (logged.getAttribute("admin") != null) {
            		out.print("<a class='nvbut' href='AdminLogoutServlet'><i class='fas fa-sign-out-alt'></i>&ensp;Sign Out</a>");
            	} else {
            		out.print("<a class='nvbut' style='cursor: pointer;' onclick='Login.render()'><i class='fas fa-sign-in-alt'></i>&ensp;Sign In</a>");
            	}
            %>
            </div>
        </nav>
        <img src="img/logo.png" alt="logo" id="logo">
        <img src="img/h.jpg" alt="header">
    </header>
    <div class="track-container">
    	<div class="track-cont1">
    		<h2>Orders</h2>
    		<form method="post" action="GetOrderServlet" id="ofl"><input type="text" hidden required name="id" id="osub" value=""><select onchange="getOrderDetails(document.getElementById('ofl'));" required class="select" style="width: 20vw; margin-bottom: 2vw" id="olist"><option value="" disabled selected>Select Auction Item</option></select></form>
			<form method="post" action="DeleteOrderServlet" id="door"><input type="text" hidden required name="id" id="orid" value=""><button type="button" onclick="Order.render('Enter the Cost', 'AcceptOrderServlet')" class="button"><i class="fa fa-check"></i>&ensp;Accept</button>&emsp;<button type="button" id="do" class="button" onclick="Confirm.render('Are you sure want to delete this order?', 'door')"><i class="fa fa-trash"></i>&ensp;Delete</button></form>    	
    	</div>
    	<div id="orderdiv" hidden></div>
    </div>
    <%
		if (request.getAttribute("result") != null) {
 			out.print("<script type='text/javascript'>Alert.render('"+request.getAttribute("result")+"')</script>");
		}
	%>
</body>
</html>