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
    <script src="js/item.js"></script>
    <script type="text/javascript">
    window.onload = function() {
    	
    	/*----------LOAD ITEMS----------*/
    	var xhttp = new XMLHttpRequest();

    	xhttp.onreadystatechange = function() {

    		if (this.readyState == 4 && this.status == 200) {

    			var items = JSON.parse(this.responseText);
    			var doc = document.getElementById("itmlist");

    			items.forEach(function(item) {
    				doc.innerHTML += "<option value=" + item.id + ">" + item.id
    						+ " - " + item.name + "</option>";
    			})
    		}

    	};

    	xhttp.open("GET", "GetItemsServlet", true);
    	xhttp.send();
    	
    }
    </script>
</head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, java.util.ArrayList, kevin.service.PromoService, kevin.model.Promotion" %>
<% 
	HttpSession logged = request.getSession(false);

	if (logged.getAttribute("admin") == null)
		response.sendRedirect("account.jsp");

	ArrayList<Promotion> promos;
	promos = PromoService.getAllPromos();
	
	
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
        <div style="position: absolute; right: 6vw; top: 8vw"><form action="SearchServlet" method="get"><input type="text" class="textbox" name="query" placeholder="Search"><button type="submit" class="searchbut" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fa fa-search"></i></button></form></div>
    </header>
    <div class="reg-container">
    	<div class="reg-cont" style="grid-column: 1/2">
    		<h2>Promotions</h2>
    		<%
    			if (!promos.isEmpty()) {
    			
	    			out.print("<div style='width: 100%; padding: 1vw; padding-left: 0vw;'><table style='width: 100%; margin: auto;'>");
	    		
	    			out.print("<tr><td style='padding: 1vh 1vw'><b>Promo ID</b></td><td style='padding: 1vh 1vw'><b>Name</b></td><td style='padding: 1vh 1vw'><b>Discount</b></td><td style='padding: 1vh 1vw'><b>Item ID</b></td><td style='padding: 1vh 1vw'><b>Edit</b></td><td style='padding: 1vh 1vw'><b>Delete</b></td></tr>");
	    			
	    			for (Promotion p : promos) {
	    				
	    				out.print("<tr>");
	    				out.print("<td style='padding: 1vh 1vw'>" + p.getId() + "</td><td style='padding: 1vh 1vw'>" + p.getName() + "</td><td style='padding: 1vh 1vw'>" + p.getDiscountPrecentage() + "%</td><td style='padding: 1vh 1vw'>" + p.getItemId() + "</td><td style='padding: 1vh 1vw'><button class='button' onclick=\"Prompt.render('Enter New Discount Percentage', '" + p.getId() + "', 'EditPromoServlet')\"><i class='fa fa-edit'></i></button></td><td><form method='post' action='DeletePromoServlet' id='PR" + p.getId() + "'><button class='button' type='button' onclick=\"Confirm.render('Are you sure want to delete " + p.getName() + "?', 'PR" + p.getId() + "')\"><i class='fa fa-trash'></i></button><input hidden type='text' name='id' value='" + p.getId() + "'></form></td>");
	    				out.print("</tr>");
	    				
	    			}
	    		
	    			out.print("</table></div>");
	    			
    			} else {
    				
    				out.print("<div style='margin-top: 6vw; color: grey;'>No Promotions</div>");
    				
    			}
    		%>
    	</div>
    	<div class="reg-cont" style="grid-column: 2/3">
    		<h2>Add Promo</h2>
    		<form method="post" action="PromoServlet">
    			<table>
    				<tr>
    					<td><select required class="select" style="width: 20vw" id="itmlist" name="itemid"><option value="" disabled selected>Select Item</option></select></td>
    					<td><input required autocomplete="off" class="textbox" type="number" step="0.01" max="100" min="0" placeholder="Discount Percentage" name="percentage" style="width: 18vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><input required autocomplete="off" class="textbox" type="text" placeholder="Name" name="name" style="width: 40vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><button class="button" type="submit" value="Submit"><i class="fas fa-save"></i>&ensp;Save</button>&emsp;<button class="button" type="reset" value="Clear"><i class="fas fa-times"></i>&ensp;Clear</button></td>
    				</tr>
    			</table>
    		</form>
    	</div>
    </div>
    <%
		if (request.getAttribute("result") != null) {
 			out.print("<script type='text/javascript'>Alert.render('"+request.getAttribute("result")+"')</script>");
		}
	%>
</body>
</html>