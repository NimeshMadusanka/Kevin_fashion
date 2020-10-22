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
    <script src="js/reply.js"></script>
    <script type="text/javascript">
    	
    	window.onload = function() {
    		
    		document.getElementById("report").hidden = true;
    		document.getElementById("rbut").innerHTML = '<i class="fas fa-arrow-down"></i>';
    		
    	}
    	
    	function toggle() {
    		
    		if (document.getElementById("report").hidden == true) {
    			document.getElementById("report").hidden = false;
    			document.getElementById("rbut").innerHTML = '<i class="fas fa-arrow-up"></i>';
    		} else {
    			document.getElementById("report").hidden = true;
    			document.getElementById("rbut").innerHTML = '<i class="fas fa-arrow-down"></i>';
    		}
    		
    	}
    	
    	
    
    </script>
</head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, kevin.service.UserService, java.util.ArrayList, kevin.model.Review, kevin.service.ReviewService" %>
<% 
	HttpSession logged = request.getSession(false);

	if (logged.getAttribute("admin") == null)
		response.sendRedirect("account.jsp");

	ArrayList<Review> reviews = new ArrayList<Review>();
	reviews = ReviewService.getReviews();
	ArrayList<User> users = new ArrayList<User>();
	users = UserService.getUsers();

%>
<body>
<script type="text/javascript">window.onscroll = function() { document.getElementById("rep").hidden = true; }</script>
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
    <div class="msgcontainer">
    	<div class="msgcont" style="grid-column: 1/3;">
    	<h3>Reports</h3><button type="button" class="button" onclick="toggle()" id="rbut" class="button"></button>
    	<div id="report">
    	<form method="post" action="ReviewReportServlet" target="_blank">
    	<table style="border-spacing: 1vw; margin: 1vw auto; color: grey">
    		<tr><td>Start Date</<td><td> : </td><td><input required type="date" class="textbox" name="startdate" style="width: 20vw"></td></tr>
    		<tr><td>End Date</<td><td> : </td><td><input required type="date" class="textbox" name="enddate" style="width: 20vw"></td></tr>
    		<tr><td colspan="3"><button class="button" type="submit"><i class="fas fa-chart-pie"></i>&ensp;Get Report</button></td></tr>
    	</table>
    	</form>
    	</div>
    	</div>
    	<div class="msgcont" style="text-align: start;" id="msgc">
    		<%
				int it = 0;

				for (Review r : reviews) {

					it++;

					for (User u : users) {

						if (u.getEmail().equals(r.getUserId())) {
							
							out.print("<i class='fa fa-user' style='font-size: 2vw'></i>&emsp;" + u.getFirstName() + " " + u.getLastName() + "</i>");
							out.print("<form hidden method='post' action='RemoveReviewServlet' id='rmrv"+r.getUserId()+"' name='delr"+r.getUserId()+"'><input hidden type='text' name='userId' value='"+r.getUserId()+"'></form>");
							
							if (r.getReply() == null)
								out.print("<span style='float: right'><button type='button' title='Write Reply' onclick=\"reply('"+u.getEmail()+"', '"+u.getFirstName()+"', '"+u.getLastName()+"', 'reply')\" class='button' style='margin-top: 0'><i class='fa fa-reply'></i></button><button title='Delete Review' type='button' class='button' style='margin-top: 0' onclick=\"Confirm.render('Are you sure, You want to delete "+u.getFirstName()+" "+u.getLastName()+"\\'s review?','delr"+r.getUserId()+"')\"><i class='fa fa-trash'></i></button></span><br><br>");
							else
								out.print("<span style='float: right'></button><button type='button' title='Delete Review' class='button' style='margin-top: 0' onclick=\"Confirm.render('Are you sure, You want to delete this "+u.getFirstName()+" "+u.getLastName()+"\\'s review?','delr"+r.getUserId()+"')\"><i class='fa fa-trash'></i></button></span><br><br>");
							
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
							
								out.print("<form hidden method='post' action='EditReviewServlet' name='"+r.getUserId()+"delrr'><input hidden type='text' name='userId' value='"+r.getUserId()+"'></form>");
								out.print("<br><hr style='border: 1px solid #f1f1f1'><br>");
								out.print("<div style='padding-left: 20%;'>");
								out.print("<i class='fa fa-reply'>&ensp;Reply</i><span style='float: right'><button type='button' title='Edit Reply' onclick=\"reply('"+u.getEmail()+"', '"+u.getFirstName()+"', '"+u.getLastName()+"', 'edit')\" class='button' style='margin-top: 0'><i class='fa fa-edit'></i><button type='button' title='Delete Reply' class='button' style='margin-top: 0' onclick=\"Confirm.render('Are you sure, You want to delete reply to "+u.getFirstName()+" "+u.getLastName()+"\\'s review?','"+r.getUserId()+"delrr')\"><i class='fa fa-trash'></i></button></span><br>");
								out.print("<p style='margin: 2vw'>" + r.getReply() + "</p>");
								out.print("</div>");
								
							}

							if (it != reviews.size())
								out.print("<br><hr style='border: 3px solid #f1f1f1'><br>");
							
						}
						
					}
					
				}
			%>
    	</div>
    	<div class="msgcont" hidden id="rep" style="height: 20vw; position: fixed; right: 2vw; padding: 2vw; margin-top: 2vw">
    		<h3 id="tile">Reply</h3>
    		<form method="post" action="ReviewReplyServlet">
    			<input hidden type="text" name="email" id="em" value="">
				<textarea placeholder="Type your reply here..." name="reply" required></textarea><br>
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