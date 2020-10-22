<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page
	import="javax.servlet.http.HttpSession, kevin.model.User, kevin.service.UserService, java.util.ArrayList, kevin.model.Package, kevin.service.PackageService, kevin.model.CreditCard, kevin.service.CreditCardService"%>
<%
	HttpSession logged = request.getSession(false);
	User user = new User();
	ArrayList<Package> packs = new ArrayList<>();
	ArrayList<CreditCard> cards = new ArrayList<>();
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect("admin.jsp");


	if (logged.getAttribute("user") != null) {
		
		user = (User) logged.getAttribute("user");
		packs = PackageService.getAllPackages();
		cards = CreditCardService.getCreditCards(user.getEmail());

	} else {
		
		response.sendRedirect("account.jsp");
		
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Packages</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
<link rel="stylesheet" href="fontawesome/css/all.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
<script src="js/alert.js"></script>
<script src="js/confirm.js"></script>
<script src="js/prompt.js"></script>
<script src="js/log.js"></script>
</head>
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
				<button
					style="float: right; border: none; background-color: transparent; outline: none; cursor: pointer; font-size: 1.5vw; color: rgba(254, 0, 2, 1);"
					onclick="Login.close()">✖</button>
				<h3 style="transform: translateX(5%)"><img src="img/logo.png" width="164vw" height="33vw"></h3>
			</div>
			<form method="POST" action="LoginServlet">
				<div id="logboxbody" style="padding: 0.5vw">
					<input required type="text" name="email" class="textbox"
						placeholder="E-Mail" autocomplete="off"
						style="margin: 0.5vw 1vw; width: 20vw"> <input required
						type="password" name="password" class="textbox"
						placeholder="Password" autocomplete="off"
						style="margin: 0.5vw 1vw; width: 20vw">
				</div>
				<div id="logboxfoot" style="text-align: center; padding: 0.5vw;">
					<button class="button" type="submit">
						<i class="fas fa-sign-in-alt">&ensp;</i>Login
					</button>
				</div>
			</form>
		</div>
	</div>
	<nav>
	<a href="items.jsp"><i class="fa fa-shopping-bag"></i>&ensp;Items</a><a href="feedback.jsp"><i class="fas fa-comments"></i>&ensp;Feedback</a><a href="auction.jsp"><i class="fas fa-gavel"></i>&ensp;Auction</a>
	<div class="innav">
		<%
				if (logged.getAttribute("user") != null) {
					out.print("<a class='nvbut' href='dispute.jsp'><i class='fa fa-exclamation-triangle'></i>&ensp;Disputes</a>");
					out.print("<a class='nvbut' href='refund.jsp'><i class='fas fa-hand-holding-usd'></i>&ensp;Refunds</a>");
					out.print("<a class='nvbut' href='order.jsp'><i class='fas fa-dollar-sign'></i>&ensp;Orders</a>");
					out.print("<a class='nvbut' href='card.jsp'><i class='fas fa-credit-card'></i>&ensp;Credit Cards</a>");
					out.print("<a id='acb' class='nvbut' href='pack.jsp'><i class='fas fa-gift'></i>&ensp;Packages</a>");
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
	</nav> <img src="img/logo.png" alt="logo" id="logo"> <img
		src="img/h.jpg" alt="header">
		<div style="position: absolute; right: 6vw; top: 8vw"><form action="SearchServlet" method="get"><input type="text" class="textbox" name="query" placeholder="Search"><button type="submit" class="searchbut" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fa fa-search"></i></button><button type="button" class="searchbut" onclick="location.href='history.jsp';" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fas fa-history"></i></button></form></div>
	</header>
	<div class="reg-container">
	<div class="reg-cont">
		<h2>Packages</h2>
    		<%
    			if (!packs.isEmpty()) {
    			
    				out.print("<div style='margin: 1vw; color: grey;'>Packages are only valid for regular item purchases</div>");
    				
	    			out.print("<div style='width: 100%; padding: 1vw; padding-left: 0vw;'><table style='width: 100%; margin: auto;'>");
	    		
	    			out.print("<tr><td style='padding: 1vh 1vw'><b>ID</b></td><td style='padding: 1vh 1vw'><b>Name</b></td><td style='padding: 1vh 1vw'><b>Discount</b></td><td style='padding: 1vh 1vw'><b>Price</b></td></tr>");
	    			
	    			for (Package p : packs) {
	    				
	    				out.print("<tr>");
	    				out.print("<td style='padding: 1vh 1vw'>" + p.getId() + "</td><td style='padding: 1vh 1vw'>" + p.getName() + "</td><td style='padding: 1vh 1vw'>" + p.getDiscountPercentage() + "%</td><td style='padding: 1vh 1vw'>" + p.getPrice() + "</td>");
	    				out.print("</tr>");
	    				
	    			}
	    		
	    			out.print("</table></div>");
	    			
    			} else {
    				
    				out.print("<div style='margin-top: 6vw; color: grey;'>No Packages</div>");
    				
    			}
    		%>
	</div>
	<div id="adp" class="reg-cont">
	<form method="post" action="PackageSubscribeServlet">
		<table style="border-spacing: 1vw; margin: 2vw auto">
		<tr><td>Credit Card</td><td> : </td><td><select required class="select" style="width: 20vw" id="cardlist" name="card"><option value="" disabled selected>Select Credit Card</option><% 
		    		for (CreditCard c : cards) {
		    			
		    			out.print("<option value='" + c.getCardNo() + "'>" + c.getNetwork() + " - " + c.getCardNo() + "</option>");
		    			
		    		}
		    		%></select></td></tr>
		 <tr><td>Package</td><td> : </td><td><select required class="select" style="width: 20vw" id="packlist" name="pack"><option value="" disabled selected>Select Package</option><% 
		    		for (Package p : packs) {
		    			
		    			out.print("<option value='" + p.getId() + "'>" + p.getName() + " - " + p.getPrice() + "</option>");
		    			
		    		}
		    		%></select></td></tr>
		 <tr><td colspan="3"><button class="button" type="submit"><i class="fa fa-plus"></i>&ensp;Subscribe</button></td></tr>
		</table>
		</form>
	</div>
	</div>
	<%
		if (request.getAttribute("result") != null) {
			out.print("<script type='text/javascript'>Alert.render('" + request.getAttribute("result")
					+ "')</script>");
		}
	%>
</body>
</html>