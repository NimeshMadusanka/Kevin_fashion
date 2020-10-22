<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Shopping Cart</title>
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
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, java.util.ArrayList, kevin.model.Cart, kevin.service.CartService, kevin.model.CreditCard, kevin.service.CreditCardService, kevin.model.Item, kevin.service.ItemService" %>
<% 
	HttpSession logged = request.getSession(false);
	boolean loggedin;
	User user = new User();
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect("admin.jsp");

	if (logged.getAttribute("user") != null) {
		
		loggedin = true;
		user = (User) logged.getAttribute("user");
		
	} else {
		
		response.sendRedirect("account.jsp");
		
	}
	
	ArrayList<Cart> carts = new ArrayList<>();
	carts = CartService.getCart(user.getEmail());
	
	ArrayList<CreditCard> cards = new ArrayList<>();
	cards = CreditCardService.getCreditCards(user.getEmail());
	
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
            <a href="items.jsp"><i class="fa fa-shopping-bag"></i>&ensp;Items</a><a href="feedback.jsp"><i class="fas fa-comments"></i>&ensp;Feedback</a><a href="auction.jsp"><i class="fas fa-gavel"></i>&ensp;Auction</a>
            <div class="innav">
            <%
				if (logged.getAttribute("user") != null) {
					out.print("<a class='nvbut' href='dispute.jsp'><i class='fa fa-exclamation-triangle'></i>&ensp;Disputes</a>");
					out.print("<a class='nvbut' href='refund.jsp'><i class='fas fa-hand-holding-usd'></i>&ensp;Refunds</a>");
					out.print("<a class='nvbut' href='order.jsp'><i class='fas fa-dollar-sign'></i>&ensp;Orders</a>");
					out.print("<a class='nvbut' href='card.jsp'><i class='fas fa-credit-card'></i>&ensp;Credit Cards</a>");
					out.print("<a class='nvbut' href='pack.jsp'><i class='fas fa-gift'></i>&ensp;Packages</a>");
					out.print("<a class='nvbut' href='track.jsp'><i class='fas fa-tasks'></i>&ensp;Tracking</a>");
					out.print("<a id='acb' class='nvbut' href='cart.jsp'><i class='fas fa-shopping-cart'></i>&ensp;Shopping Cart</a>");
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
    	<div class="reg-cont" style="grid-column: 1/3">
    		<h2>Shopping Cart</h2>
    	</div>
    	<div <% if (carts.isEmpty()) { out.print("hidden"); } %> class="reg-cont" style="grid-column: 1/3; padding: 1vw">
    		<%
    			double total = 0;
    		
    			if (!carts.isEmpty()) {
    			
	    			out.print("<div style='width: 100%; padding: 1vw; padding-left: 0vw;'><table style='width: 100%; margin: auto;'>");
	    		
	    			out.print("<tr><td style='padding: 1vh 1vw'><b>Item Name</b></td><td style='padding: 1vh 1vw'><b>Quantity</b></td><td style='padding: 1vh 1vw'><b>Price</b></td><td style='padding: 1vh 1vw'><b>Remove</b></td></tr>");
	    			
	    			for (Cart c : carts) {
	    				
	    				Item item = ItemService.getItem(c.getItemId());
	    				
	    				out.print("<tr>");
	    				out.print("<td style='padding: 1vh 1vw'>" + item.getName() + "</td><td style='padding: 1vh 1vw'>" + c.getQuantity() + "</td><td style='padding: 1vh 1vw'>" + c.getPrice() + "</td><td><form method='post' action='RemoveFromCartServlet' id='CA" + c.getId() + "'><button class='button' type='button' onclick=\"Confirm.render('Are you sure want to remove " + item.getName() + " from cart?', 'CA" + c.getId() + "')\"><i class='fa fa-trash'></i></button><input hidden type='text' name='id' value='" + c.getId() + "'></form></td>");
	    				out.print("</tr>");
	    				
	    				total += c.getPrice();
	    				
	    			}
	    			
    			}
    		%>
    		<tr <% if(carts.isEmpty()) { out.print("hidden"); }%>><form method='post' action='CheckOutServlet' id='CO'><td>Total</td><td><td><% if(!carts.isEmpty()) { out.print(total); }%><input hidden reqired type="text" name="total" value="<% if(!carts.isEmpty()) { out.print(total); }%>"></td><td rowspan="2" style="padding-top: 2vw"><select required class="select" style="width: 20vw" id="cardlist" name="card"><option value="" disabled selected>Select Credit Card</option>
    		<% 
    			if (!carts.isEmpty()) {
    				
		    		for (CreditCard c : cards) {
		    			
		    			out.print("<option value='" + c.getCardNo() + "'>" + c.getNetwork() + " - " + c.getCardNo() + "</option>");
		    			
		    		}
		    		
    			}
		    %></select><br><button style="width: 20vw; height: 2.5vw; margin-top: 2vh" class='button' type='button' onclick="Confirm.render('Are you sure want to check out?', 'CO')"><i class="fas fa-cart-arrow-down"></i>&ensp;Check Out</button></form></td></tr>
    		<% if(!carts.isEmpty()) { out.print("</table></div>"); }%>
    	</div>
    	<%
    	 	if(carts.isEmpty()) {
				
				out.print("<div class='reg-cont' style='grid-column: 1/3; color: grey; padding: 5vw'>Cart is Empty</div>");
				
			}
    	%>
    </div>
    <%
		if (request.getAttribute("result") != null) {
 			out.print("<script type='text/javascript'>Alert.render('"+request.getAttribute("result")+"')</script>");
		}
	%>
</body>
</html>