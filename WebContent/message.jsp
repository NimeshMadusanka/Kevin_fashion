<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User" %>
<%
	HttpSession logged = request.getSession(false);
	boolean loggedin;
	User user = new User();
	String userId = null;
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect("admin.jsp");

	if (logged.getAttribute("user") != null) {
		
		loggedin = true;
		user = (User) logged.getAttribute("user");
		userId = user.getEmail();
		
	} else {
		
		loggedin = false;
 		response.sendRedirect("account.jsp");
		
	}
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Messages</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="shortcut icon" type="image/x-icon" href="img/icon.png">
    <link rel="stylesheet" href="fontawesome/css/all.css">
    <link rel="stylesheet" type="text/css" media="screen" href="css/style.css" />
    <script src="js/alert.js"></script>
    <script src="js/confirm.js"></script>
    <script src="js/prompt.js"></script>
    <script src="js/log.js"></script>
    <script>
    var msg = true;
    var count = 0;
    setInterval(function loadDoc() {
    	
    	  	  var xhttp = new XMLHttpRequest();
    	  	  
    	  	  xhttp.onreadystatechange = function() {
    	  		
    	  	  	if (this.readyState == 4 && this.status == 200) {
    	  	  		
    	  	    	var messages = JSON.parse(this.responseText);
    	  	    	var doc = document.getElementById("chatbox");
    	  	    	doc.innerHTML = "";
    	  	    	if (messages == "")
    	  	    		doc.innerHTML = "<div style='background-color: rgba(0, 0, 0, 0.8); border-radius: 1vw; color: white; padding: 1vw; text-align: center; grid-column-start: 1; grid-column-end: 7'>No Messages</div>";
    	  	    	messages.forEach(function(item) {
    	  	    		
    	  	    		if (item.client == false)
    	  	    			doc.innerHTML += "<div style='background-color: rgba(255, 0, 0, 1); float: right; color: white; width: 60%; box-shadow: 0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3); padding: 1vw; height: auto; margin-top: 1vw; text-align: center; grid-column-start: 3; grid-column-end: 7; border-radius: 2vw'>" + item.message + "</div>";
    	  	    		else
    	  	    			doc.innerHTML += "<div style='background-color: rgba(0, 128, 255, 1); float: left; color: white; width: 60%; box-shadow: 0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3); padding: 1vw; height: auto; margin-top: 1vw; text-align: center; grid-column-start: 1; grid-column-end: 5; border-radius: 2vw'>" + item.message + "</div>";
    	  	    	
    	  	    		msg = item.userSeen;
    	  	    			
    	  	    	})
    	  	    }
    	  	  	
    	  	  };
    	  	  
    	  	  xhttp.open("GET", "GetMessagesServlet?userId=<%=user.getEmail()%>", true);
    	  	  xhttp.send();
    	  	  
    	  	if (msg == false) {
    	  		var div = document.getElementById("chatbox");
    	      	div.scrollTop = div.scrollHeight - div.clientHeight;
    	      	var xhr = new XMLHttpRequest();
    	    	xhr.onerror = function(){  Alert.render(xhr.responseText); }
    	    	var userId = document.getElementById("em").value;
    	   		xhr.open ("POST", "UserSeenServlet", true);
    	    	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    	    	xhr.send ("userId="+userId);
    	  	  }
    	    	
    	  	if (count < 2) {
				var div = document.getElementById("chatbox");
    	      	div.scrollTop = div.scrollHeight - div.clientHeight;
			}
    	  	
    	  	count++;

    	  	  
    	  	}, 500);
    </script>
    <script>
	function submitForm(oFormElement)
	{
  		var xhr = new XMLHttpRequest();
  		xhr.onerror = function(){  Alert.render(xhr.responseText); }

  		var message = document.getElementById("sr").value;
 		xhr.open (oFormElement.method, oFormElement.action, true);
  		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  		xhr.send ("message="+message);
  		document.getElementById("sr").value = "";
  		return false;
	}
	</script>
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
					out.print("<a class='nvbut' href='cart.jsp'><i class='fas fa-shopping-cart'></i>&ensp;Shopping Cart</a>");
					out.print("<a id='acb' class='nvbut' href='message.jsp'><i class='fas fa-envelope'></i>&ensp;Messages</a>");
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
    <div class="msgcontainer">
    	<div class="msgcont">
    		<h3>Messages</h3>
<!--     		<div id="chatbox" style='margin-top: 2.5vw; padding: 1vw; border-radius: 1vw; display: grid; grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr; grid-gap: 0.5vw;'></div> -->
    	<div id="chatbox" style='margin-top: 2.5vw; border-radius: 1vw; padding: 1vw; display: block;'></div>
    	</div>
    	<div class="msgcont" style="height: 20vw; position: fixed; right: 2vw; padding: 2vw; margin-top: 2vw">
    		<h3>Need Help?</h3>
    		<form id="form" method="POST" action="SendMessagesServlet" onsubmit="return submitForm(this);">
    		<input hidden id="em" type="text" name="userId" value="<%=userId%>">
				<textarea id="sr" placeholder="Enter your message here..." name="message" required></textarea><br>
				<button type="submit" class="button">
					<i class="fa fa-paper-plane"></i>&ensp;Send
				</button>
				&emsp;&emsp;
				<button type="reset" class="button">
					<i class="fa fa-times"></i>&ensp;Clear
				</button>
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