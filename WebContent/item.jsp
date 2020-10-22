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
    	<div class="reg-cont">
    		<button class="button" onclick="displayCategories()" style="border-radius: 2vw 0 0 2vw; width: 9vw"><i class="fas fa-list-alt"></i>&ensp;Categories</button>&emsp;<button class="button" onclick="displaySizes()" style="border-radius: 0 0 0 0; width: 9vw"><i class="fas fa-crop"></i>&ensp;Sizes</button>&emsp;<button class="button" onclick="displayColors()" style="border-radius: 0 0 0 0; width: 9vw"><i class="fas fa-palette"></i>&ensp;Colors</button>&emsp;<button class="button" onclick="displayNone()" style="border-radius: 0 2vw 2vw 0; width: 9vw"><i class="fas fa-times"></i>&ensp;Close</button><br><br>
    		<div style="padding-bottom: 1vw;" id="catdiv" hidden>
    			<h2 style="color: red; font-family: android"><i class="fas fa-list-alt"></i>&ensp;Categories</h2>
    			<hr>
    			<p style="color: gray; font-size: 1.4vw">Add</p>
    			<form method="post" action="CategoryServlet" id="adcf">
    				<input required autocomplete="off" class="textbox" type="text" placeholder="Category Name" id="adct" name="addcategory" style="width: 20vw; height: 0.3vw">
    				<button class="button" type="button" onclick="addCategory(document.getElementById('adcf'));" value="Submit"><i class="fas fa-plus"></i>&ensp;Add</button>
    			</form>
    			<hr>
    			<p style="color: gray; font-size: 1.4vw">Remove</p>
    			<form method="post" action="CategoryServlet" id="dlcf">
    				<select id="delcat" required class="select" style="width: 22vw; height: 2.5vw" name="delcategory"><option value="" disabled selected>Select Category</option></select>
    				<button class="button" type="button" onclick="deleteCategory(document.getElementById('dlcf'));" value="Submit"><i class="fas fa-trash"></i>&ensp;Delete</button>
    			</form>
    		</div>
    		<div style="padding-bottom: 1vw" id="sizdiv" hidden>
    			<h2 style="color: red; font-family: android"><i class="fas fa-crop"></i>&ensp;Sizes</h2>
    			<hr>
    			<p style="color: gray; font-size: 1.4vw">Add</p>
    			<form method="post" action="SizeServlet" id="adsf">
    				<input id="adsiz" required autocomplete="off" class="textbox" type="text" placeholder="Size" name="addsize" style="width: 20vw; height: 0.3vw">
    				<button class="button" type="button" onclick="addSize(document.getElementById('adsf'));" value="Submit"><i class="fas fa-plus"></i>&ensp;Add</button>
    			</form>
    			<hr>
    			<p style="color: gray; font-size: 1.4vw">Remove</p>
    			<form method="post" action="SizeServlet" id="dlsf">
    				<select id="delsiz" required class="select" style="width: 22vw; height: 2.5vw" name="delsize"><option value="" disabled selected>Select Size</option></select>
    				<button class="button" type="button" onclick="deleteSize(document.getElementById('dlsf'));" value="Submit"><i class="fas fa-trash"></i>&ensp;Delete</button>
    			</form>
    		</div>
    		<div style="padding-bottom: 1vw" id="coldiv" hidden>
    			<h2 style="color: red; font-family: android"><i class="fas fa-palette"></i>&ensp;Colors</h2>
    			<hr>
    			<p style="color: gray; font-size: 1.4vw">Add</p>
    			<form method="post" action="ColorServlet" id="adclf">
    				<input id="adcolv" required autocomplete="off" class="textbox" type="color" name="addvalue" value="#b7873d" style="width: 21vw; height: 1.8vw; transform: translateX(-16%); padding: 0.5vw"><br>
    				<input id="adcoln" required autocomplete="off" class="textbox" type="text" placeholder="Color Name" name="addname" style="width: 20vw; height: 0.3vw">
    				<button class="button" type="button" onclick="addColor(document.getElementById('adclf'));" value="Submit"><i class="fas fa-plus"></i>&ensp;Add</button>
    			</form>
    			<hr>
    			<p style="color: gray; font-size: 1.4vw">Remove</p>
    			<form method="post" action="ColorServlet" id="dlclf">
    				<select id="delcol" required class="select" style="width: 22vw; height: 2.5vw" name="delcolor"><option value="" disabled selected>Select Color</option></select>
    				<button class="button" type="button" onclick="deleteColor(document.getElementById('dlclf'));" value="Submit"><i class="fas fa-trash"></i>&ensp;Delete</button>
    			</form>
    			<hr>
    			<p style="color: gray; font-size: 1.4vw">Edit</p>
    			<form method="post" action="ColorServlet"  id="edclf">
    				<select id="edtcol" required class="select" style="width: 22vw; height: 2.5vw; transform: translateX(-21%)" name="edtcolor"><option value="" disabled selected>Select Color</option></select><br>
    				<input id="edtncoln" required autocomplete="off" class="textbox" type="text" placeholder="Color Name" name="newname" style="width: 20vw; height: 0.3vw">
    				<button class="button" type="button" onclick="editColor(document.getElementById('edclf'));" value="Submit"><i class="fas fa-edit"></i>&ensp;Save Edit</button>
    			</form>
    		</div>
    		<div id="dddiv" style="padding-bottom: 1vw; height: 80%; color: gray">
    			<p style="font-size: 2vw; font-family: sans-serif">Select an Option to Start Tweaking</p><br><br><br>
    			<i class="fas fa-wrench" style="font-size: 12vw; color: gray"></i>
    		</div>
    	</div>
    	<div class="reg-cont" id="aim">
    		<button class="button btnactive" type="button" onclick="addItem();" style="border-radius: 2vw 0 0 2vw"><i class="fas fa-plus"></i>&ensp;Add Item</button>&emsp;&emsp;<button class="button" type="button" onclick="editItem();" style="border-radius: 0 2vw 2vw 0"><i class="fas fa-edit"></i>&ensp;Edit Item</button><br><br>
    		<h2 style="font-family: title">Add Item</h2>
    		<form method="post" action="ItemServlet" enctype="multipart/form-data">
    			<table>
    				<tr>
    					<td><input required autocomplete="off" class="textbox" type="text" placeholder="Item Name" name="name" style="width: 18vw"></td>
    					<td><input required autocomplete="off" class="textbox"  min="0" step="0.01" type="number" placeholder="Price" name="price" style="width: 18vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><input required autocomplete="off" class="textbox" type="text" placeholder="Description" name="description" style="width: 40vw"></td>
    				</tr>
    				<tr>
    					<td><select id="cat" required class="select" style="width: 20vw" name="category"><option value="" disabled selected>Category</option></select></td>
    					<td><input type="text" hidden value="" id="tcol" name="color"><select id="col" required onchange="itemAddColor();" class="select" style="width: 20vw"><option value="" disabled selected>Color</option></select></td>
    				</tr>
    				<tr>
    					<td><input type="text" hidden value="" id="tsiz" name="size"><select id="siz" required onchange="itemAddSize();" class="select" style="width: 20vw"><option value="" disabled selected>Size</option></select></td>
    					<td>
    						<div class="upload-btn-wrapper">
    							<input required class="textbox" type="file" accept="image/*" name="image">
  								<button class="btn">Upload a file</button>
    						</div>
    					</td>
    				</tr>
    				<tr>
    					<td><input required autocomplete="off" class="textbox" min="1" title="Minimum Quantity is 1" type="number" placeholder="Quantity" name="quantity" style="width: 18vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><button class="button" type="submit" value="Submit"><i class="fas fa-save"></i>&ensp;Save</button>&emsp;<button class="button" type="reset" value="Clear"><i class="fas fa-times"></i>&ensp;Clear</button></td>
    				</tr>
    			</table>
    		</form>
    	</div>
    	<div class="edtitm" id="eim">
    		<div style="grid-column: 1/3;"><button class="button" type="button" onclick="addItem();" style="border-radius: 2vw 0 0 2vw"><i class="fas fa-plus"></i>&ensp;Add Item</button>&emsp;&emsp;<button class="button btnactive" type="button" onclick="editItem();" style="border-radius: 0 2vw 2vw 0"><i class="fas fa-edit"></i>&ensp;Edit Item</button><br><br>
    		<div style="grid-column: 1/3"><h2 style="font-family: title; grid-row: 1/3">Edit Item</h2></div>
    		<div id="itmdiv" style="width: 20vw; float: left; margin-left: 1vw; margin-bottom: 1vw"><div>Select an Item</div></div>
    		<div style="float: left; padding: 2vw">
    			<form method="post" action="GetItemServlet" id="itmfl"><select onchange="getItemDetails(document.getElementById('itmfl'));" required class="select" style="width: 18vw" id="itmlist"><option value="" disabled selected>Select Item</option></select></form>
    			<br><br><form method="post" action="DeleteItemServlet" id="itmdl"><button class="button" type="button" onclick="deleteItem(document.getElementById('itmdl'));"><i class="fas fa-trash"></i>&ensp;Delete Item</button></form>
    		</div>
    		<div class="reg-cont" style="grid-column: 1/3; padding: 0; box-shadow: 0 0 0 0">
    			<form method="post" action="EditItemServlet" enctype="multipart/form-data">
    			<input type="text" hidden required name="id" id="idsub" value="">
    			<table>
    				<tr>
    					<td><input autocomplete="off" class="textbox" type="text" placeholder="Item Name" name="name" style="width: 18vw"></td>
    					<td><input autocomplete="off" class="textbox" min="0" step="0.01" type="number" placeholder="Price" name="price" style="width: 18vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><input autocomplete="off" class="textbox" type="text" placeholder="Description" name="description" style="width: 40vw"></td>
    				</tr>
    				<tr>
    					<td><select id="ecat" class="select" style="width: 20vw" name="category"><option value="" disabled selected>Category</option></select></td>
    					<td><input hidden type="text" id="tecol" value="" name="color"><select onchange="itemEditColor();" id="ecol" name="kk" class="select" style="width: 20vw"><option value="" disabled selected>Color</option></select></td>
    				</tr>
    				<tr>
    					<td><input type="text" hidden value="" id="tesiz" name="size"><select onchange="itemEditSize();" id="esiz" class="select" style="width: 20vw"><option value="" disabled selected>Size</option></select></td>
    					<td>
    						<div class="upload-btn-wrapper">
    							<input class="textbox" type="file" accept="image/*" name="image">
  								<button class="btn">Upload a file</button>
    						</div>
    					</td>
    				</tr>
    				<tr>
    					<td><input autocomplete="off" class="textbox" min="1" title="Minimum Quantity is 1" type="number" placeholder="Quantity" name="quantity" style="width: 18vw"></td>
    				</tr>
    				<tr>
    					<td colspan="2"><button class="button" type="submit" value="Submit" onclick="itemEditCheck();"><i class="fas fa-save"></i>&ensp;Save</button>&emsp;<button class="button" type="reset" value="Clear"><i class="fas fa-times"></i>&ensp;Clear</button></td>
    				</tr>
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