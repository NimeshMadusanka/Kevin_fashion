<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page
	import="javax.servlet.http.HttpSession, kevin.model.User, kevin.service.UserService, java.util.ArrayList, kevin.model.CreditCard, kevin.service.CreditCardService"%>
<%
	HttpSession logged = request.getSession(false);
	User user = new User();
	ArrayList<CreditCard> cards = new ArrayList<>();
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect("admin.jsp");


	if (logged.getAttribute("user") != null) {
		
		user = (User) logged.getAttribute("user");
		cards = CreditCardService.getCreditCards(user.getEmail());

	} else {
		
		response.sendRedirect("account.jsp");
		
	}
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Credit Cards</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
<link rel="stylesheet" href="fontawesome/css/all.css">
<link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
<script src="js/alert.js"></script>
<script src="js/confirm.js"></script>
<script src="js/prompt.js"></script>
<script src="js/log.js"></script>
<script src="js/pack.js"></script>
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
					out.print("<a id='acb' class='nvbut' href='card.jsp'><i class='fas fa-credit-card'></i>&ensp;Credit Cards</a>");
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
	</nav> <img src="img/logo.png" alt="logo" id="logo"> <img
		src="img/h.jpg" alt="header">
		<div style="position: absolute; right: 6vw; top: 8vw"><form action="SearchServlet" method="get"><input type="text" class="textbox" name="query" placeholder="Search"><button type="submit" class="searchbut" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fa fa-search"></i></button><button type="button" class="searchbut" onclick="location.href='history.jsp';" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fas fa-history"></i></button></form></div>
	</header>
	<div class="reg-container">
		<div class="reg-cont">
			<h3>Add Credit Card</h3>
			<form method="post" action="CreditCardServlet">
				<table>
                    <tr>
                        <td><input type="text" name="name" class="textbox" placeholder="Name" autocomplete="off" style="width: 18vw"></td>
                        <td><input type="text" name="address" class="textbox" placeholder="Address" autocomplete="off" style="width: 18vw"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="number" min="0" pattern="[0-9]{16}" name="cardno" class="textbox" placeholder="Credit Card No." autocomplete="off" style="width: 40vw"></td>
                    </tr>
                    <tr>
                        <td><input type="date" name="expiredate" class="textbox" placeholder="Expire Date" autocomplete="off" style="width: 18vw"></td>
                        <td><input type="number" pattern="[0-9]{3}" min="0" title="CVV should contain three digits" name="cvv" class="textbox" placeholder="CVV" autocomplete="off" style="width: 18vw"></td>
                    </tr>
                    <tr>
                        <td><input type="number" step="0.01" min="0" name="creditlimit" class="textbox" placeholder="Credit Limit" autocomplete="off" style="width: 18vw"></td>
                        <td><input type="text" name="country" class="textbox" placeholder="Country" autocomplete="off" style="width: 18vw"></td>
                    </tr>
                    <tr>
                            <td><select class="select" style="width: 20vw" name="network"><option value="" disabled selected>Select Network</option><option value="MasterCard">MasterCard</option><option value="Visa">Visa</option><option value="AmericanExpress">AmericanExpress</option></select></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="text-align: end"><button type="submit" class="button"><i class="fa fa-save">&ensp;</i>Save</button>&emsp;<button type="reset" value="Clear" class="button"><i class="fa fa-times">&ensp;</i>Clear</button></td>
                    </tr>
                </table>
			</form>
		</div>
		<div class="reg-cont">
			<h3>Credit Cards</h3>
    		<%
    			if (!cards.isEmpty()) {
    			
	    			out.print("<div style='width: 100%; padding: 1vw; padding-left: 0vw;'><table style='width: 100%; margin: auto;'>");
	    		
	    			out.print("<tr><td style='padding: 1vh 0.5vw'><b>Card No.</b></td><td style='padding: 1vh 0.5vw'><b>Name</b></td><td style='padding: 1vh 0.5vw'><b>Credit Limit</b></td><td style='padding: 1vh 0.5vw'><b>Network</b></td><td style='padding: 1vh 0.5vw'><b>Edit</b></td><td style='padding: 1vh 0.5vw'><b>Delete</b></td></tr>");
	    			
	    			for (CreditCard c : cards) {
	    				
	    				out.print("<tr>");
	    				out.print("<td style='padding: 1vh 0.5vw'>" + c.getCardNo() + "</td><td style='padding: 1vh 0.5vw'>" + c.getName() + "</td><td style='padding: 1vh 0.5vw'>" + c.getCreditLimit() + "</td><td style='padding: 1vh 0.5vw'>" + c.getNetwork() + "</td><td style='padding: 1vh 0.5vw'><button class='button' onclick=\"Prompt.render('Enter New Credit Limit', '" + c.getCardNo() + "', 'EditCreditCardServlet')\"><i class='fa fa-edit'></i></button></td><td><form method='post' action='DeleteCreditCardServlet' id='CR" + c.getCardNo() + "'><button class='button' type='button' onclick=\"Confirm.render('Are you sure want to delete " + c.getCardNo() + " Credit Card?', 'CR" + c.getCardNo() + "')\"><i class='fa fa-trash'></i></button><input hidden type='text' name='id' value='" + c.getCardNo() + "'></form></td>");
	    				out.print("</tr>");
	    				
	    			}
	    		
	    			out.print("</table></div>");
	    			
    			} else {
    				
    				out.print("<div style='margin-top: 6vw; color: grey;'>No Credit Cards</div>");
    				
    			}
    		%>
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