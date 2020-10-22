<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Item</title>
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
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, java.util.ArrayList, kevin.model.Item, kevin.service.ItemService, kevin.model.Promotion, kevin.service.PromoService" %>
<% 
	HttpSession logged = request.getSession(false);
	boolean loggedin;
	User user = new User();
	Item item = new Item();
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect("admin.jsp");

	if (logged.getAttribute("user") == null)
		response.sendRedirect("account.jsp");
		
	user = (User) logged.getAttribute("user");
	item = (Item) logged.getAttribute("itm");

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
            <a href="items.jsp" class="active"><i class="fa fa-shopping-bag"></i>&ensp;Items</a><a href="feedback.jsp"><i class="fas fa-comments"></i>&ensp;Feedback</a><a href="auction.jsp"><i class="fas fa-gavel"></i>&ensp;Auction</a>
            <div class="innav">
            <%
				if (logged.getAttribute("user") != null) {
					out.print("<a class='nvbut' href='dispute.jsp'><i class='fa fa-exclamation-triangle'></i>&ensp;Disputes</a>");
					out.print("<a class='nvbut' href='refund.jsp'><i class='fas fa-hand-holding-usd'></i>&ensp;Refunds</a>");
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
    <div class="iwrap">
	    <div class="icont">
	    	<div style="border-radius: 1vw; grid-row: 1/2"><img src="<%out.print(item.getImagePath());%>" style="width: 100%; height: 100%; border-radius: 1vw 1vw 0 0"></div>
	    	<table style="border-spacing: 1vw">
	    	<%Promotion promo = PromoService.getPromoByItem(item.getId()); %>
	    		<tr><td><b>Name</b></td><td><b> : </b></td><td><%out.print(item.getName());%></td></tr>
	    		<%
		    		if (promo != null) {
						out.print("<tr><td><b>Price</b></td><td><b> : </b></td><td><del>"+item.getPrice()+"</del></td></tr>");
						out.print("<tr><td><b>&ensp;</b></td><td><b>&ensp;</b></td><td>"+((100.0-promo.getDiscountPrecentage())/100.0)*item.getPrice()+"</td></tr>");
					} else {
						out.print("<tr><td><b>Price</b></td><td><b> : </b></td><td>"+item.getPrice()+"</td></tr>");
					}
	    		%>
	    		<tr><td><b>Quantity</b></td><td><b> : </b></td><td><%out.print(item.getQuantity());%></td></tr>
	    		<tr><td><b>Color</b></td><td><b> : </b></td><td><%out.print(ItemService.getColor(item.getColor()));%></td></tr>
	    		<tr><td><b>Category</b></td><td><b> : </b></td><td><%out.print(item.getCategory());%></td></tr>
	    		<tr><td><b>Size</b></td><td><b> : </b></td><td><%out.print(item.getSize());%></td></tr>
	    		<tr><td><b>Description</b></td><td><b> : </b></td><td><%out.print(item.getDescription());%></td></tr>
	    	</table>
	    </div>
	    <div class="bcont">
	    	<div <%if (item.getQuantity() <= 0) { out.print("hidden"); } %>>
	    	<form method="post" action="AddToCartServlet">
	    		<h1>Purchase</h1><br><br>
	    		<table style="margin: auto; border-spacing: 1vw;">
		    		<input hidden type="text" name="item" value="<%out.print(item.getId());%>">
		    		<tr><td>Quantity</td><td> : </td><td><input required style="width: 18vw" class="textbox" type="number" step="1"  min="1" max="<%out.print(item.getQuantity());%>" placeholder="Quantity" name="quantity"></td></tr>
		    		<tr><td colspan="3"><button type="sumbit" class="button"><i class="fas fa-cart-plus"></i>&ensp;Add to Cart</button></td></tr>
	    		</table>
	    	</form>
	    	</div>
	    	<div <%if (item.getQuantity() > 0) { out.print("hidden"); } %> class="aclose">
	    		<h1>Out of Stock</h1>
	    	</div>
	    </div>
    </div>
    <%
		if (request.getAttribute("result") != null) {
 			out.print("<script type='text/javascript'>Alert.render('"+request.getAttribute("result")+"')</script>");
		}
	%>
</body>
</html>