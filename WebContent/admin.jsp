<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page
	import="javax.servlet.http.HttpSession, kevin.model.User, kevin.service.UserService, kevin.model.Review, kevin.service.ReviewService, java.util.ArrayList, kevin.util.RoundNumbers"%>
<%
	HttpSession logged = request.getSession(false);

	if (logged.getAttribute("admin") == null)
		response.sendRedirect("account.jsp");

	
	ArrayList<Review> reviews = new ArrayList<Review>();
	reviews = ReviewService.getReviews();
	ArrayList<User> users = new ArrayList<User>();
	users = UserService.getUsers();
	
	int[] stars = {0, 0, 0, 0, 0};
	int count = 0, totalStars = 0;
	
	for (Review r : reviews) {
		
		for (int i = 0 ; i < stars.length ; i++) {
			
			if (r.getRating() == (i+1))
				stars[i]++;
			
		}
		
		count++;
		
	}
	
	for (int i = 0 ; i < stars.length ; i++) {
		
		int sum = 0;
		sum = (i+1) * stars[i];
		totalStars+=sum;
		
	}
	
	double avg = totalStars / (double) count;
	double[] starsPrecentage = new double[stars.length];
	
	for (int i = 0 ; i < stars.length ; i++) {
		
		starsPrecentage[i] = ((double) stars[i] / count) * 100;
		
	}
	
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="fontawesome/css/all.css">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    <style type="text/css">
	.bar-5 {width: <%=starsPrecentage[4] + "%"%>; height: 18px; background-color: #4CAF50; border-radius: 2vw;}
	.bar-4 {width: <%=starsPrecentage[3] + "%"%>; height: 18px; background-color: #2196F3; border-radius: 2vw;}
	.bar-3 {width: <%=starsPrecentage[2] + "%"%>; height: 18px; background-color: #00bcd4; border-radius: 2vw;}
	.bar-2 {width: <%=starsPrecentage[1] + "%"%>; height: 18px; background-color: #ff9800; border-radius: 2vw;}
	.bar-1 {width: <%=starsPrecentage[0] + "%"%>; height: 18px; background-color: #f44336; border-radius: 2vw;}
	</style>
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
                    <button style="float: right; border: none; background-color: transparent; outline: none; cursor: pointer; font-size: 1.5vw; color: rgba(254, 0, 2, 1);"
                        onclick="Login.close()">✖</button>
                    <h3 style="transform: translateX(5%)"><img src="img/logo.png" width="164vw" height="33vw"></h3>
                </div>
                <form method="POST" action="LoginServlet">
                    <div id="logboxbody" style="padding: 0.5vw">
                        <input required type="admin" name="email" class="textbox" placeholder="E-Mail" autocomplete="off" style="margin: 0.5vw 1vw; width: 20vw">
                        <input required type="password" name="password" class="textbox" placeholder="Password" autocomplete="off" style="margin: 0.5vw 1vw; width: 20vw">
                    </div>
                    <div id="logboxfoot" style="text-align: center; padding: 0.5vw;">
                        <button class="button" type="submit"><i class="fas fa-sign-in-alt">&ensp;</i>Login</button>
                    </div>
                </form>
            </div>
        </div>
        <nav class="navbar">
        <a href="admin.jsp"><i class="fas fa-table"></i>&ensp;Admin Home</a><a href="users.jsp"><i class="fas fa-users"></i>&ensp;Users</a>
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
    <div class="admin-container">
    	<div id="admin-rev" class="admin-con">
    	<div>
				<span class="heading">&emsp;User Rating</span><br><br>
				<span style="font-size: 2vw">
				<%
						long totalRating =  Math.round(avg);

						for (int i = 1; i <= 5; i++) {

							if (i <= totalRating) {
								out.print("<i class='fa fa-star checked fa-spin' style='font-size: 2vw;'></i> ");
							} else {
								out.print("<i class='fa fa-star fa-spin' style='font-size: 2vw; color: rgba(0, 0, 0, 0.1);'></i> ");
							}

						}
					%>
					</span>
				<p><%=RoundNumbers.round(avg,1)%> average based on <%=count%> reviews.</p>
				<hr style="border: 0.2vw solid #f1f1f1; border-radius: 2vw">

				<div class="row">
					<div class="side">
						<div>5 <i class='fa fa-star checked' style='color: rgba(0, 0, 0, 0.2)'></i></div>
					</div>
					<div class="middle">
						<div class="bar-container">
							<div class="bar-5"></div>
						</div>
					</div>
					<div class="side right">
						<div><%=stars[4]%></div>
					</div>
					<div class="side">
						<div>4 <i class='fa fa-star checked' style='color: rgba(0, 0, 0, 0.2)'></i></div>
					</div>
					<div class="middle">
						<div class="bar-container">
							<div class="bar-4"></div>
						</div>
					</div>
					<div class="side right">
						<div><%=stars[3]%></div>
					</div>
					<div class="side">
						<div>3 <i class='fa fa-star checked' style='color: rgba(0, 0, 0, 0.2)'></i></div>
					</div>
					<div class="middle">
						<div class="bar-container">
							<div class="bar-3"></div>
						</div>
					</div>
					<div class="side right">
						<div><%=stars[2]%></div>
					</div>
					<div class="side">
						<div>2 <i class='fa fa-star checked' style='color: rgba(0, 0, 0, 0.2)'></i></div>
					</div>
					<div class="middle">
						<div class="bar-container">
							<div class="bar-2"></div>
						</div>
					</div>
					<div class="side right">
						<div><%=stars[1]%></div>
					</div>
					<div class="side">
						<div>1 <i class='fa fa-star checked' style='color: rgba(0, 0, 0, 0.2)'></i></div>
					</div>
					<div class="middle">
						<div class="bar-container">
							<div class="bar-1"></div>
						</div>
					</div>
					<div class="side right">
						<div><%=stars[0]%></div>
					</div>
				</div>
			</div>
    	</div>
    	<div id="admin-head" class="admin-con"><span>Administrator Dashboard</span></div>
    	<div id="a1" class="admin-cont"><div class="admin-head"><img src="img/messages.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="adminusers.jsp"><button type="button">Messages</button></a></div></div>
    	<div id="a2" class="admin-cont"><div class="admin-head"><img src="img/payments.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="payment.jsp"><button type="button">Payments</button></a></div></div>
    	<div id="a3" class="admin-cont"><div class="admin-head"><img src="img/reviews.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="adminreview.jsp"><button type="button">Reviews</button></a></div></div>
    	<div id="a4" class="admin-cont"><div class="admin-head"><img src="img/items.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="item.jsp"><button type="button">Items</button></a></div></div>
    	<div id="a5" class="admin-cont"><div class="admin-head"><img src="img/promos.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="promo.jsp"><button type="button">Promos</button></a></div></div>
    	<div id="a6" class="admin-cont"><div class="admin-head"><img src="img/auction.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="adminauction.jsp"><button type="button">Auction Items</button></a></div></div>
    	<div id="a7" class="admin-cont"><div class="admin-head"><img src="img/package.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="supplier.jsp"><button type="button">Suppliers</button></a></div></div>
    	<div id="a8" class="admin-cont"><div class="admin-head"><img src="img/supplier.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="package.jsp"><button type="button">Packages</button></a></div></div>
    	<div id="a9" class="admin-cont"><div class="admin-head"><img src="img/order.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="adminorder.jsp"><button type="button">Orders</button></a></div></div>
    	<div id="a10" class="admin-cont"><div class="admin-head"><img src="img/refund.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="adminrefund.jsp"><button type="button">Refunds</button></a></div></div>
    	<div id="a11" class="admin-cont"><div class="admin-head"><img src="img/dispute.jpg" style="width: 100%; heigth: auto"></div><div class="admin-foot"><a href="admindispute.jsp"><button type="button">Disputes</button></a></div></div>
    </div>
    <%
		if (request.getAttribute("result") != null) {
			out.print("<script type='text/javascript'>Alert.render('" + request.getAttribute("result")
					+ "')</script>");
		}
	%>
</body>
</html>