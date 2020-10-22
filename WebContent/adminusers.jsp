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
</head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, kevin.service.UserService, java.util.ArrayList, kevin.model.Message, kevin.service.MessageService" %>
<%
	HttpSession logged = request.getSession(false);

	if (logged.getAttribute("admin") == null)
		response.sendRedirect("account.jsp");

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
    <div class="usrcont">
    	<div id="usrhead">Users</div>
    	<%
    		for (User u: users) {
    			
    		    if (MessageService.getLastMessage(u.getEmail()).isAdminSeen() || MessageService.getLastMessage(u.getEmail()).getMessage() == null) {
    		    	
    				out.print("<form hidden method='post' action='AdminUsersServlet' name='" + u.getEmail() + "'><input hidden type='text' name='userId' value='" + u.getEmail() + "'></form>");
    				out.print("<div id='usr' onclick=\"document.forms[\'" + u.getEmail() + "\'].submit()\"><i class='fa fa-user' style='font-size: 2vw'></i>&emsp;" + u.getFirstName() + " " + u.getLastName() + "</i></div>");
    		    
    		    }
    		    
    		    else if (!MessageService.getLastMessage(u.getEmail()).isAdminSeen()) {
    		    	
    		    	out.print("<form hidden method='post' action='AdminUsersServlet' name='" + u.getEmail() + "'><input hidden type='text' name='userId' value='" + u.getEmail() + "'></form>");
    				out.print("<div id='usr' style='background-color: red; color: white' onclick=\"document.forms[\'" + u.getEmail() + "\'].submit()\"><i class='fa fa-user' style='font-size: 2vw'></i>&emsp;" + u.getFirstName() + " " + u.getLastName() + "</i></div>");
    		    	
    		    }
    		    
    		}
    	%>
    </div>
    <%
		if (request.getAttribute("result") != null) {
			out.print("<script type='text/javascript'>Alert.render('" + request.getAttribute("result")
					+ "')</script>");
		}
	%>
</body>
</html>