<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page
	import="javax.servlet.http.HttpSession, kevin.model.User, kevin.service.UserService, kevin.model.Review, kevin.service.ReviewService, java.util.ArrayList, kevin.util.RoundNumbers"%>
<%
	HttpSession logged = request.getSession(false);
	boolean loggedin, reviewed;
	User user = new User();
	Review review = new Review();
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect(request.getHeader("referer").replace("http://localhost:8080/KEVIN/", ""));


	if (logged.getAttribute("user") != null) {

		loggedin = false;
		user = (User) logged.getAttribute("user");
		review = ReviewService.getReview(user.getEmail());

	} else {

		loggedin = true;

	}

	if (review.getUserId() != null)
		reviewed = false;
	else
		reviewed = true;
	
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Feedback</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
<link rel="stylesheet" href="fontawesome/css/all.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/style.css" />
<style type="text/css">
	.bar-5 {width: <%=starsPrecentage[4] + "%"%>; height: 2.083333333333333vh; background-color: #4CAF50; border-radius: 2vw;}
	.bar-4 {width: <%=starsPrecentage[3] + "%"%>; height: 2.083333333333333vh; background-color: #2196F3; border-radius: 2vw;}
	.bar-3 {width: <%=starsPrecentage[2] + "%"%>; height: 2.083333333333333vh; background-color: #00bcd4; border-radius: 2vw;}
	.bar-2 {width: <%=starsPrecentage[1] + "%"%>; height: 2.083333333333333vh; background-color: #ff9800; border-radius: 2vw;}
	.bar-1 {width: <%=starsPrecentage[0] + "%"%>; height: 2.083333333333333vh; background-color: #f44336; border-radius: 2vw;}
</style>
<script src="js/alert.js"></script>
<script src="js/confirm.js"></script>
<script src="js/prompt.js"></script>
<script src="js/log.js"></script>
<script src="js/review.js"></script>
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
	<a href="items.jsp"><i class="fa fa-shopping-bag"></i>&ensp;Items</a><a href="feedback.jsp" class="active"><i class="fas fa-comments"></i>&ensp;Feedback</a><a href="auction.jsp"><i class="fas fa-gavel"></i>&ensp;Auction</a>
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
	</nav> <img src="img/logo.png" alt="logo" id="logo"> <img
		src="img/h.jpg" alt="header"> <div style="position: absolute; right: 6vw; top: 8vw"><form action="SearchServlet" method="get"><input type="text" class="textbox" name="query" placeholder="Search"><button type="submit" class="searchbut" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fa fa-search"></i></button><button type="button" class="searchbut" onclick="location.href='history.jsp';" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fas fa-history"></i></button></form></div> </header>
	<div class="feedback-container">
		<div class="feedback" id="fd" style="padding: 2vw;">
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
		<div class="feedback" id="ir" <%if (!loggedin) {
				out.print("hidden");
			}%> style="hight: 2vw">
			<h1 style="font-family: head">Contact Us</h1>
		</div>
		<div style="color: gray; text-align: start; font-size: 1.6vw; color: rgba(0, 0, 0, 0.7);" class="feedback" id="in" <%if (!loggedin) {
				out.print("hidden");
			}%>>
			<table style="margin: 3vh auto; border-spacing: 1.2vw;">
				<tr><td><b><i class="fas fa-envelope"></i></b></td><td></td><td style="font-size : 1.3vw">kevinf@gmail.com</td></tr>
				<tr><td><b><i class="fas fa-phone"></i></b></td><td></td><td style="font-size : 1.3vw">+94776098243</td></tr>
				<tr><td style="vertical-align: top;"><b><i class="fas fa-map-marker-alt"></i></b></td><td></td><td rowspan="3" style="font-size : 1.3vw">No 28,<br> Ward Street,<br> Galle.</td></tr>
			</table>
		</div>
		<div id="lr" class="feedback"
			<%if ((reviewed && !loggedin) || loggedin) {
				out.print("hidden");
			}%>>
			<div style="text-align: start; padding: 2vw" id="dr">
				<form method="post" action="RemoveReviewServlet" id="rvform">
					<h3 id="rh">
						<i class="fa fa-user" style="font-size: 2vw"></i>&emsp;
						<%
							out.print(user.getFirstName() + " " + user.getLastName());
						%><span style="float: right"><button type="button"
								onclick="editReview()" class="button" style="margin-top: 0">
								<i class="fa fa-edit"></i>
							</button>
							<button type="button" class="button" style="margin-top: 0"
								onclick="Confirm.render('Are you sure, You want to delete this review?','rvform');">
								<i class="fa fa-trash"></i>
							</button></span>
					</h3>
				</form>
				<br>
				<h3 id="rr">
					<%
						int rating = review.getRating();

						for (int i = 1; i <= 5; i++) {

							if (i <= rating) {
								out.print("<i class='fa fa-star checked'></i> ");
							} else {
								out.print("<i class='fa fa-star' style='color: rgba(0, 0, 0, 0.1)'></i> ");
							}

						}
					%>
				</h3>
				<p id="rd"><%=review.getDescription()%></p>
			</div>
			<h3 hidden id="erh">Share Your Experience</h3>
			<form method="post" action="EditReviewServlet">
				<div class="rating" hidden id="err">
					<ul class="rate-area">
						<input type="radio" id="5-star" name="rating" value="5" />
						<label for="5-star" title="Amazing">5 stars</label>
						<input type="radio" id="4-star" name="rating" value="4" />
						<label for="4-star" title="Good">4 stars</label>
						<input type="radio" id="3-star" name="rating" value="3" />
						<label for="3-star" title="Average">3 stars</label>
						<input type="radio" id="2-star" name="rating" value="2" />
						<label for="2-star" title="Not Good">2 stars</label>
						<input type="radio" id="1-star" name="rating" value="1" />
						<label for="1-star" title="Bad">1 star</label>
					</ul>
				</div>
				<textarea placeholder="Review" name="description" hidden id="erd"></textarea>
				<button id="ers" hidden type="submit" class="button">
					<i class="fa fa-save"></i>&ensp;Save
				</button>
				&emsp;&emsp;
				<button id="eru" hidden type="button" class="button"
					onclick="seeReview()">
					<i class="fa fa-eye"></i>&ensp; See Current Review
				</button>
				<button id="ere" hidden type="button" class="button"
					onclick="hideReview()">
					<i class="fa fa-eye-slash"></i>&ensp;Hide Current Review
				</button>
				&emsp;&emsp;
				<button id="erc" hidden type="button" class="button"
					onclick="cancelReviewEdit()">
					<i class="fa fa-times"></i>&ensp;Cancel
				</button>
			</form>
		</div>
		<div style="grid-column-start: 4; grid-column-end: 7;" class="feedback"
			<%if (loggedin || !reviewed) {
				out.print("hidden");
			}%>>
			<h3>Share Your Experience</h3>
			<form method="post" action="ReviewServlet">
				<div class="rating">
					<ul class="rate-area">
						<input type="radio" id="5-sta" name="rating" value="5" />
						<label for="5-sta" title="Amazing">5 stars</label>
						<input type="radio" id="4-sta" name="rating" value="4" />
						<label for="4-sta" title="Good">4 stars</label>
						<input type="radio" id="3-sta" name="rating" value="3" />
						<label for="3-sta" title="Average">3 stars</label>
						<input type="radio" id="2-sta" name="rating" value="2" />
						<label for="2-sta" title="Not Good">2 stars</label>
						<input type="radio" id="1-sta" name="rating" value="1" />
						<label for="1-sta" title="Bad">1 star</label>
					</ul>
				</div>
				<textarea placeholder="Review" name="description" required></textarea>
				<button type="submit" class="button">
					<i class="fa fa-save"></i>&ensp;Save
				</button>
				&emsp;&emsp;
				<button type="reset" class="button">
					<i class="fa fa-times"></i>&ensp;Clear
				</button>
			</form>
		</div>
		<div class="feedback2">
			<%
				int it = 0;

				for (Review r : reviews) {

					it++;

					for (User u : users) {

						if (u.getEmail().equals(r.getUserId())) {
							
							out.print("<i class='fa fa-user' style='font-size: 2vw'></i>&emsp;" + u.getFirstName() + " " + u.getLastName() + "</i><br><br>");

							int rat = r.getRating();

							for (int i = 1; i <= 5; i++) {

								if (i <= rat) {
									out.print("<i class='fa fa-star checked'></i> ");
								} else {
									out.print("<i class='fa fa-star' style='color: rgba(0, 0, 0, 0.1)'></i> ");
								}

							}

							out.print("<p id='rd'>" + r.getDescription() + "</p>");
							
							if (r.getReply() != null) {
								
								out.print("<br><hr style='border: 1px solid #f1f1f1'><br>");
								out.print("<div style='padding-left: 20%;'>");
								out.print("<i class='fa fa-reply'>&ensp;Reply</i>");
								out.print("<p style='margin-top: 2vw'>" + r.getReply() + "</p>");
								out.print("</div>");
								
							}

							if (it != reviews.size())
								out.print("<br><hr style='border: 3px solid #f1f1f1'><br>");
							
						}
						
					}
					
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
	<span id="desc" hidden><%=review.getDescription()%></span>
	<span id="rat" hidden><%=review.getRating()%></span>
</body>
</html>