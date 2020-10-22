<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Refund</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="fontawesome/css/all.css">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    <script src="js/alert.js"></script>
    <script src="js/confirm.js"></script>
    <script src="js/prompt.js"></script>
    <script src="js/log.js"></script>
    <script src="js/form.js"></script>
</head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, java.util.ArrayList, kevin.model.Refund, kevin.service.RefundService, kevin.model.Payment, kevin.service.PaymentService" %>
<% 
	HttpSession logged = request.getSession(false);
	User user = new User();
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect("admin.jsp");

	if (logged.getAttribute("user") != null) {
		
		user = (User) logged.getAttribute("user");
		
	} else {
		
		response.sendRedirect("account.jsp");
		
	}
	
	
	ArrayList<Refund> refunds = new ArrayList<>();
	refunds = RefundService.getRefunds(user.getEmail());
	
%>
<script type="text/javascript">
    window.onload = function() {
    	
    	/*----------LOAD PAYMENTS----------*/
    	var xhttp = new XMLHttpRequest();

    	xhttp.onreadystatechange = function() {

    		if (this.readyState == 4 && this.status == 200) {

    			var items = JSON.parse(this.responseText);
    			var doc = document.getElementById("paylist");

    			items.forEach(function(item) {
    				doc.innerHTML += "<option value=" + item.id + "> Date : " + item.date.substring(2,10)
    						+ "&emsp; Time : " + item.date.substring(11,19) +  "&emsp; Price : " + item.price + "</option>";
    			})
    		}

    	};

    	xhttp.open("GET", "GetPaymentsServlet", true);
    	xhttp.send();
    	
    }
    </script>
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
            <a href="items.jsp"><i class="fa fa-shopping-bag"></i>&ensp;Home</a><a href="feedback.jsp"><i class="fas fa-comments"></i>&ensp;Feedback</a><a href="auction.jsp"><i class="fas fa-gavel"></i>&ensp;Auction</a>
            <div class="innav">
            <%
				if (logged.getAttribute("user") != null) {
					out.print("<a class='nvbut' href='dispute.jsp'><i class='fa fa-exclamation-triangle'></i>&ensp;Disputes</a>");
					out.print("<a id='acb' class='nvbut' href='refund.jsp'><i class='fas fa-hand-holding-usd'></i>&ensp;Refunds</a>");
					out.print("<a class='nvbut' href='order.jsp'><i class='fas fa-dollar-sign'></i>&ensp;Orders</a>");
					out.print("<a class='nvbut' href='card.jsp'><i class='fas fa-credit-card'></i>&ensp;Credit Cards</a>");
					out.print("<a class='nvbut' href='pack.jsp'><i class='fas fa-gift'></i>&ensp;Packages</a>");
					out.print("<a class='nvbut' href='track.jsp'><i class='fas fa-tasks'></i>&ensp;Tracking</a>");
					out.print("<a class='nvbut' href='cart.jsp'><i class='fas fa-shopping-cart'></i>&ensp;Shopping Cart</a>");
					out.print("<a class='nvbut' href='message.jsp'><i class='fas fa-envelope'></i>&ensp;Messages</a>");
					out.print("<a class='nvbut' href='account.jsp'><i class='fas fa-user'></i>&ensp;Profile</a>");
					out.print("<a class='nvbut' href='LogoutServlet'><i class='fas fa-sign-out-alt'></i>&ensp;Sign Out</a>");
				} else {
					out.print("<a class='nvbut' href='account.jsp'><i class='fas fa-user'></i>&ensp;Sign Up</a>");
					out.print("<a class='nvbut' style='cursor: pointer;' onclick='Login.render()'><i class='fas fa-sign-in-alt'></i>&ensp;Sign In</a>");
				}
			%>
            </div>
        </nav>
        <img src="img/logo.png" alt="logo" id="logo">
        <img src="img/h.jpg" alt="header">
        <div style="position: absolute; right: 6vw; top: 8vw"><form action="SearchServlet" method="get"><input type="text" class="textbox" name="query" placeholder="Search"><button type="submit" class="searchbut" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fa fa-search"></i></button><button type="button" class="searchbut" onclick="location.href='history.jsp';" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fas fa-history"></i></button></form></div>
    </header>
    <div class="reg-container">
    	<div class="reg-cont">
    		<h2>Refund Request</h2>
    		<form method="post" action="RefundServlet">
    			<table>
    				<tr>
    					<td colspan="2"><select required class="select" style="width: 42vw" name="payment" id="paylist"><option value="" disabled selected>Select Payment</option></select></td>
    				</tr>
    				<tr>
    					<td colspan="2"><input required autocomplete="off" class="textbox" type="text" placeholder="Reason for request" name="description" style="width: 40vw; height: 4vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><button class="button" type="submit" value="Submit"><i class="fas fa-paper-plane"></i>&ensp;Send</button>&emsp;<button class="button" type="reset" value="Clear"><i class="fas fa-times"></i>&ensp;Clear</button></td>
    				</tr>
    			</table>
    		</form>
    	</div>
    	<div class="reg-cont">
    		<h2>All Requests</h2>
    		<%
    			if (!refunds.isEmpty()) {
    			
	    			out.print("<div style='width: 100%; padding: 1vw; padding-left: 0vw;'><table style='width: 100%; margin: auto;'>");
	    		
	    			out.print("<tr><td style='padding: 1vh 1vw'><b>Payment ID</b></td><td style='padding: 1vh 1vw'><b>Decription</b></td><td style='padding: 1vh 1vw'><b>Amount</b></td><td style='padding: 1vh 1vw'><b>Status</b></td><td style='padding: 1vh 1vw'><b>Delete</b></td></tr>");
	    			
	    			for (Refund r : refunds) {
	    				
	    				Payment payment = PaymentService.getPayment(r.getPaymentId());
	    				
	    				String status;
	    				
	    				if (r.getStatus() == Refund.PENDING)
	    					status = "Pending";
	    				else if (r.getStatus() == Refund.REJECTED)
	    					status = "Rejected";
	    				else
	    					status = "Accepted";
	    					
	    				
	    				out.print("<tr>");
	    				out.print("<td style='padding: 1vh 1vw'>" + r.getPaymentId() + "</td><td style='padding: 1vh 1vw'>" + r.getDescription() + "</td><td style='padding: 1vh 1vw'>" + payment.getPrice() + "</td><td style='padding: 1vh 1vw'>" + status + "</td><td><form method='post' action='DeleteRefundServlet' id='RE" + r.getId() + "'><button class='button' type='button' onclick=\"Confirm.render('Are you sure want to delete this request?', 'RE" + r.getId() + "')\"><i class='fa fa-trash'></i></button><input hidden type='text' name='id' value='" + r.getId() + "'></form></td>");
	    				out.print("</tr>");
	    				
	    			}
	    		
	    			out.print("</table></div>");
	    			
    			} else {
    				
    				out.print("<div style='margin-top: 6vw; color: grey;'>No Refund Requests</div>");
    				
    			}
    		%>
    	</div>
    </div>
    <%
		if (request.getAttribute("result") != null) {
 			out.print("<script type='text/javascript'>Alert.render('"+request.getAttribute("result")+"')</script>");
		}
	%>
</body>
</html>