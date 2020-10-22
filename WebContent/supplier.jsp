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
    <script src="js/supplier.js"></script>
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
    	<div class="reg-cont" style="grid-column: 1/2">
    		<h2>Suppliers</h2>
    		<form method="post" action="GetSupplierServlet" id="supfl"><input type="text" hidden required name="id" id="idsup" value=""><select onchange="getSupplierDetails(document.getElementById('supfl'));" required class="select" style="width: 20vw" id="suplist"><option value="" disabled selected>Select Supplier</option></select>&emsp;<button type="button" class="button" id="edsup" onclick="editSupplier();">Edit Supplier</button><button type="button" class="button" id="delsup" onclick="Confirm.render('Are you sure want to delete this supplier?', 'dels');">Delete Supplier</button></form>
    		<div id="supdiv" style="text-align: start; margin-top: 2vw; margin-left: 2.5vw">
    		</div>
    		<form method="post" action="DeleteSupplierServlet" id="dels" name="supf"><input hidden type="text" name="id" value="" id="isup"></form>
    	</div>
    	<div class="reg-cont" style="grid-column: 2/3" id="addsup">
    		<h2>Add Supplier</h2>
    		<form method="post" action="SupplierServlet">
    			<table>
    				<tr>
    					<td><input required autocomplete="off" class="textbox" type="text" placeholder="Supplier Name" name="name" style="width: 18vw"></td>
    					<td><input required autocomplete="off" class="textbox" type="text" placeholder="Telephone No." name="telephone" style="width: 18vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><input required autocomplete="off" class="textbox" type="text" placeholder="Address" name="address" style="width: 40vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><button class="button" type="submit" value="Submit"><i class="fas fa-save"></i>&ensp;Save</button>&emsp;<button class="button" type="reset" value="Clear"><i class="fas fa-times"></i>&ensp;Clear</button></td>
    				</tr>
    			</table>
    		</form>
    	</div>
    	<div class="reg-cont" style="grid-column: 2/3" id="edtsup">
    		<h2>Edit Supplier</h2>
    		<form method="post" action="EditSupplierServlet">
    			<input type="text" name="id" id="supid" hidden>
    			<table>
    				<tr>
    					<td><input autocomplete="off" class="textbox" type="text" placeholder="Supplier Name" name="name" style="width: 18vw"></td>
    					<td><input autocomplete="off" class="textbox" type="text" placeholder="Telephone No." name="telephone" style="width: 18vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><input autocomplete="off" class="textbox" type="text" placeholder="Address" name="address" style="width: 40vw"></td>
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