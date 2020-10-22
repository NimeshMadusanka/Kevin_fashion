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
    <script src="js/adminauc.js"></script>
</head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User" %>
<% 
	HttpSession logged = request.getSession(false);

	if (logged.getAttribute("admin") == null)
		response.sendRedirect("account.jsp");
	
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
    <div class="reg-container">
    	<div style="display: grid; grid-template-columns: 1fr 1fr; grid-gap: 2vw">
    		<div style="grid-column: 1/3; box-shadow: 0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3); border-radius: 1vw; padding: 0.5vw;"><div style="float: left; width: 50%; text-align: center; border-radius: 1vw 0 0 1vw;" id="itmtab" onclick="switchTab('itm')"><h1>Items</h1></div><div style="float: left; width: 50%; text-align: center; border-radius: 0 1vw 1vw 0;" id="auctab" onclick="switchTab('auc')"><h1>Auction</h1></div></div>
    		<div id="itab" style="grid-column: 1/3;  box-shadow: 0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3); border-radius: 1vw; padding: 1vw; text-align: center; height: 17vw">
    			<form method="post" action="GetItemServlet" id="itmfl"><input type="text" hidden required name="id" id="idsub" value=""><select onchange="getItemDetails(document.getElementById('itmfl'));" required class="select" style="width: 20vw" id="itmlist"><option value="" disabled selected>Select Item</option></select></form>
    			<div id="adsub" style="margin-top: 1vw">
    			<form method="post" action="AuctionServlet">
    				<table style="width: 90%; margin: auto; border-spacing: 1vw">
    					<tr><td>Starting Price</td><td colspan="3"><input autocomplete="off" style="width: 28vw" type="number" min="0" step="0.01" class="textbox" required name="price" min="0" placeholder="Starting Price for the whole batch"></td></tr>
    					<tr><td>Bid Duration</td><td><input type="number" style="width: 7vw" class="textbox" required name="days" min="0" placeholder="Days"></td><td><input style="width: 7vw" type="number" class="textbox" required name="hours" min="0" max="23" placeholder="Hours"></td><td><input type="number" class="textbox" required name="minutes" min="0" max="59" style="width: 7vw" placeholder="Minutes"></td></tr>
    				</table>
    				<input type="text" hidden required name="id" id="idauc" value="">
    				<input type="submit" class="button" value="Add to Auction">
    			</form>
    			</div>
    		</div>
    		<div id="atab" style="grid-column: 1/3; box-shadow: 0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3); border-radius: 1vw; padding: 1vw; text-align: center; height: 17vw">
    			<br><br>
    			<form method="post" action="GetAuctionItemServlet" id="aucfl"><input type="text" hidden required name="id" id="aucsub" value=""><select onchange="getAuctionItemDetails(document.getElementById('aucfl'));" required class="select" style="width: 20vw" id="auclist"><option value="" disabled selected>Select Auction Item</option></select></form>
    			<div id="asub" style="margin-top: 1vw">
    			<div style="margin-bottom: 1vw; font-size: 1.2vw">Auction Item</div>
    			<form method="post" action="DeleteAuctionItemServlet" id="delauc">
    				<input type="text" hidden required name="id" id="ida" value="">
    				<button type="button" class="button" onclick="deleteAuctionItem(document.getElementById('delauc'));"><i class="fa fa-trash"></i>&ensp;Remove</button>&emsp;<button onclick="editAuction();" type="button" class="button"><i class="fa fa-edit"></i>&ensp;Edit</button>
    			</form>
    			</div>
    		</div>
    	</div>
    	<div style="display: grid; grid-template-columns: 1fr 1fr; grid-gap: 1vw;">
    		<div id="itmdiv"></div>
    		<div hidden id="edaucdiv">
    			<form method="post" action="EditAuctionServlet">
    				<h3>Edit Auction</h3>
    				<input hidden type="text" name="id" id="eau" value="">
    				<table style="border-spacing: 0.5vw;">
    					<tr><td><input autocomplete="off" style="width: 18vw" type="number" min="0" step="0.01" class="textbox" name="price" placeholder="Starting Price for the whole batch"></td></tr>
    					<tr><td><input type="number" min="0" style="width: 18vw" class="textbox" name="days" min="0" placeholder="Days"></td></tr>
    					<tr><td><input type="number" min="0" max="23" style="width: 18vw" class="textbox" name="hours" min="0" placeholder="Hours"></td></tr>
    					<tr><td><input type="number" min="0" max="59" style="width: 18vw" class="textbox" name="minutes" min="0" placeholder="Minutes"></td></tr>
    					<tr><td style="text-align: center"><button class="button" type="submit"><i class="fa fa-save"></i>&ensp;Save</button></td></tr>
    				</table>
    			</form>
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