<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>KEVIN FASHIONS | Account</title>
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
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User" %>
<% 
	HttpSession logged = request.getSession(false);
	boolean loggedin;
	User user = new User();
	
	if (logged.getAttribute("admin") != null)
		response.sendRedirect(request.getHeader("referer").replace("http://localhost:8080/KEVIN/", ""));

	if (logged.getAttribute("user") != null) {
		
		loggedin = true;
		user = (User) logged.getAttribute("user");
		
	} else {
		
		loggedin = false;
		
	}
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
					out.print("<a class='nvbut' href='message.jsp'><i class='fas fa-envelope'></i>&ensp;Messages</a>");
					out.print("<a id='acb' class='nvbut' href='account.jsp'><i class='fas fa-user'></i>&ensp;Profile</a>");
					out.print("<a class='nvbut' href='LogoutServlet'><i class='fas fa-sign-out-alt'></i>&ensp;Sign Out</a>");
				} else {
					out.print("<a id='acb' class='nvbut' href='account.jsp'><i class='fas fa-user'></i>&ensp;Sign Up</a>");
					out.print("<a class='nvbut' style='cursor: pointer;' onclick='Login.render()'><i class='fas fa-sign-in-alt'></i>&ensp;Sign In</a>");
				}
			%>
            </div>
        </nav>
        <img src="img/logo.png" alt="logo" id="logo">
        <img src="img/h.jpg" alt="header">
        <div style="position: absolute; right: 6vw; top: 8vw"><form action="SearchServlet" method="get"><input type="text" class="textbox" name="query" placeholder="Search"><button type="submit" class="searchbut" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fa fa-search"></i></button><button type="button" class="searchbut" onclick="location.href='history.jsp';" style="margin: 0 0 0 1vw; border-radius: 5vw; height: 3vw;"><i class="fas fa-history"></i></button></form></div>
    </header>
    <div class="reg-container">
        <div class="reg-cont" style="text-align: start; font-size: 1vw; padding: 2vw">
        <span>
			<h1 style="color: red;"><i class="fa fa-user-shield" style="font-size: 2vw"></i>&ensp;Privacy Notice</h1>
			<p>
				This privacy notice discloses the privacy practices for KEVIN FASHIONS. This privacy notice applies solely to information collected
				by this website. It will notify you of the following:<br>
			<ul type="disc">
				<li>What personally identifiable information is collected from
					you through the website, how it is used and with whom it may be
					shared.<br>
				</li>
				<li>What choices are available to you regarding the use of your
					data.<br>
				</li>
				<li>The security procedures in place to protect the misuse of
					your information.<br>
				</li>
				<li>How you can correct any inaccuracies in the information.</li>
			</ul>
			<h3>Information Collection, Use, and Sharing</h3> We are the sole
			owners of the information collected on this site. We only have access
			to/collect information that you voluntarily give us via email or
			other direct contact from you. We will not sell or rent this
			information to anyone.<br> <br> We will use your
			information to respond to you, regarding the reason you contacted us.
			We will not share your information with any third party outside of
			our organization, other than as necessary to fulfill your request,
			e.g. to ship an order.<br> <br> Unless you ask us not to,
			we may contact you via email in the future to tell you about
			specials, new products or services, or changes to this privacy
			policy.
			<h3>Your Access to and Control Over Information</h3> You may opt out
			of any future contacts from us at any time. You can do the following
			at any time by contacting us via the email address or phone number
			given on our website:<br>
			<ul type="disc">
				<li>what data we have about you, if any.<br></li>
				<li>Change/correct any data we have about you.<br></li>
				<li>Have us delete any data we have about you. Express any
					concern you have about our use of your data.</li>
			</ul>
			<h3>Security</h3>We take precautions to protect your information.
			When you submit sensitive information via the website, your
			information is protected both online and offline.<br> <br>
			Wherever we collect sensitive information (such as credit card data),
			that information is encrypted and transmitted to us in a secure way.
			You can verify this by looking for a lock icon in the address bar and
			looking for "https" at the beginning of the address of the Web page.<br>
			<br> While we use encryption to protect sensitive information
			transmitted online, we also protect your information offline. Only
			employees who need the information to perform a specific job (for
			example, billing or customer service) are granted access to
			personally identifiable information. The computers/servers in which
			we store personally identifiable information are kept in a secure
			environment.<br>
			<h3>If you feel that we are not abiding by this privacy policy,
				you should contact us immediately via telephone at +94 91 3492471 or
				via email.</h3>
			</p>
		</span>
        </div>
        <div class="reg-cont" <% if (!loggedin) { out.print("hidden"); } %>>
            <h3 id="protitle">Profile</h3>
            <form method="post" action="EditProfileServlet">
                <table>
                    <tr>
                        <td><input type="text" disabled id="fname" value="<%=user.getFirstName()%>" pattern="[a-zA-Z]{3,}" title="First Name Should Only Contain Letters" name="firstName" class="textbox" placeholder="First Name" autocomplete="off" style="width: 18vw"></td>
                        <td><input type="text" disabled id="lname" value="<%=user.getLastName()%>" pattern="[a-zA-Z]{3,}" title="Last Name Should Only Contain Letters" name="lastName" class="textbox" placeholder="Last Name" autocomplete="off" style="width: 18vw"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input disabled id="email" value="<%=user.getEmail()%>" type="email" name="email" class="textbox" placeholder="E-Mail" autocomplete="off" style="width: 40vw"></td>
                    </tr>
                    <tr>
                        <td><input type="text" disabled id="pno" value="<%=user.getPostalNumber()%>" name="postalNumber" class="textbox" placeholder="Postal Number" autocomplete="off" style="width: 18vw"></td>
                        <td><input type="text" disabled id="st" value="<%=user.getStreet()%>" pattern="[a-zA-Z]{3,}" title="Street Should Only Contain Letters" name="street" class="textbox" placeholder="Street" autocomplete="off" style="width: 18vw"></td>
                    </tr>
                    <tr>
                        <td><input type="text" disabled id="ct" value="<%=user.getCity()%>" pattern="[a-zA-Z]{3,}" title="City Should Only Contain Letters" name="city" class="textbox" placeholder="City" autocomplete="off" style="width: 18vw"></td>
                        <td><input type="text" disabled id="di" value="<%=user.getDistrict()%>" pattern="[a-zA-Z]{3,}" title="District Should Only Contain Letters" name="district" class="textbox" placeholder="District" autocomplete="off" style="width: 18vw"></td>
                    </tr>
                    <tr>
                            <td colspan="2"><input disabled id="tel" value="<%=user.getTelephone()%>" type="text" pattern="[0-9]{10}" title="Telephone Number Should Contain 10 Digits" name="telephone" class="textbox" placeholder="Telephone" autocomplete="off" style="width: 40vw"></td>
                    </tr>
                    <tr id="pwrow" hidden>
                            <td><input type="password" title="New Password" name="password" class="textbox" placeholder="New Password" autocomplete="off" id="halfbox"></td>
                            <td><input type="password" title="Confirm Password" name="confirmPassword" class="textbox" placeholder="Confirm Password" autocomplete="off" id="halfbox"></td>
                    </tr>
                    <tr>                 
                        <td colspan="2" style="text-align: end"><button id="save" type="submit"  class="button" hidden><i class="fa fa-save"></i>&ensp;Save Changes</button></form><button hidden id="clear" type="reset" class="button"><i class="fa fa-eye"></i>&ensp;See Current Values</button><button id="cancel" type="button" onclick="cancelEdit('<%=user.getFirstName()%>','<%=user.getLastName()%>','<%=user.getEmail()%>','<%=user.getPostalNumber()%>','<%=user.getStreet()%>','<%=user.getCity()%>','<%=user.getDistrict()%>','<%=user.getTelephone()%>')" hidden class="button"><i class="fa fa-times"></i>&ensp;Cancel</button><form method="post" action="RemoveAccountServlet" id="del"><button id="delete" type="button" class="button" onclick="Confirm.render('Are you sure, You want to delete your account?','del')"><i class="fa fa-trash"></i>&ensp;Delete Profile</button><button id="edit" type="button" onclick="editProfile()" class="button"><i class="fa fa-edit"></i>&ensp;Edit Profile</button></form></td>
                    </tr>
                </table>
        </div>
        <div class="reg-cont" <% if (loggedin) { out.print("hidden"); } %>>
            <h3>Sign Up</h3>
            <form method="POST" action="RegisterServlet">
                <table>
                    <tr>
                        <td><input type="text" pattern="[a-zA-Z]{3,}" title="First Name Should Only Contain Letters" name="firstName" class="textbox" placeholder="First Name" autocomplete="off" id="halfbox"></td>
                        <td><input type="text" pattern="[a-zA-Z]{3,}" title="Last Name Should Only Contain Letters" name="lastName" class="textbox" placeholder="Last Name" autocomplete="off" id="halfbox"></td>
                    </tr>
                    <tr>
                        <td colspan="2"><input type="email" name="email" class="textbox" placeholder="E-Mail" autocomplete="off" id="fullbox"></td>
                    </tr>
                    <tr>
                        <td><input type="text" name="postalNumber" class="textbox" placeholder="Postal Number" autocomplete="off" id="halfbox"></td>
                        <td><input type="text" pattern="[a-zA-Z]{3,}" title="Street Should Only Contain Letters" name="street" class="textbox" placeholder="Street" autocomplete="off" id="halfbox"></td>
                    </tr>
                    <tr>
                        <td><input type="text" pattern="[a-zA-Z]{3,}" title="City Should Only Contain Letters" name="city" class="textbox" placeholder="City" autocomplete="off" id="halfbox"></td>
                        <td><input type="text" pattern="[a-zA-Z]{3,}" title="District Should Only Contain Letters" name="district" class="textbox" placeholder="District" autocomplete="off" id="halfbox"></td>
                    </tr>
                    <tr>
                            <td colspan="2"><input type="text" pattern="[0-9]{10}" title="Telephone Number Should Contain 10 Digits" name="telephone" class="textbox" placeholder="Telephone" autocomplete="off" id="fullbox"></td>
                    </tr>
                    <tr>
                            <td><input type="password" title="New Password" name="password" class="textbox" placeholder="New Password" autocomplete="off" id="halfbox"></td>
                            <td><input type="password" title="Confirm Password" name="confirmPassword" class="textbox" placeholder="Confirm Password" autocomplete="off" id="halfbox"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td style="text-align: end"><input type="submit" value="Submit" class="button">&emsp;<input type="reset" value="Clear" class="button"></td>
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