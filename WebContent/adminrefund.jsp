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
    <script src="js/form.js"></script>
</head>
<%@ page import="javax.servlet.http.HttpSession, kevin.model.User, java.util.ArrayList, kevin.model.Refund, kevin.service.RefundService, kevin.model.Payment, kevin.service.PaymentService" %>
<% 
	HttpSession logged = request.getSession(false);

	if (logged.getAttribute("admin") == null)
	response.sendRedirect("account.jsp");
	
	
	ArrayList<Refund> refunds = new ArrayList<>();
	refunds = RefundService.getRefunds();
	
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
    <div class="reg-cont" style="grid-column: 1/3">
    		<h2>All Requests</h2>
    </div>
    <div class="reg-cont" style="grid-column: 1/3">
    		<%
    			if (!refunds.isEmpty()) {
    			
	    			out.print("<div style='width: 100%; padding: 1vw; padding-left: 0vw;'><table style='width: 100%; margin: auto;'>");
	    		
	    			out.print("<tr><td style='padding: 1vh 1vw'><b>Payment ID</b><td style='padding: 1vh 1vw'><b>User ID</b></td></td><td style='padding: 1vh 1vw'><b>Description</b></td><td style='padding: 1vh 1vw'><b>Amount</b></td><td style='padding: 1vh 1vw'><b>Status</b></td><td style='padding: 1vh 1vw'><b>Allow</b></td><td style='padding: 1vh 1vw'><b>Reject</b></td><td style='padding: 1vh 1vw'><b>Delete</b></td></tr>");
	    			
	    			for (Refund r : refunds) {
	    				
	    				Payment payment = PaymentService.getPayment(r.getPaymentId());
	    				
	    				String status;
	    				
	    				if (r.getStatus() == Refund.PENDING)
	    					status = "Pending";
	    				else if (r.getStatus() == Refund.REJECTED)
	    					status = "Rejected";
	    				else
	    					status = "Accepted";
	    					
	    				
	    				out.print("<tr>");
	    				out.print("<td style='padding: 1vh 1vw'>" + r.getPaymentId() + "</td><td style='padding: 1vh 1vw'>" + r.getUserId() + "</td><td style='padding: 1vh 1vw'>" + r.getDescription() + "</td><td style='padding: 1vh 1vw'>" + payment.getPrice() + "</td><td style='padding: 1vh 1vw'>" + status + "</td><td><form method='post' action='AllowRefundServlet' id='RA" + r.getId() + "'><button class='button' type='button' onclick=\"Confirm.render('Are you sure want to allow this refund?', 'RA" + r.getId() + "')\"><i class='fa fa-check'></i></button><input hidden type='text' name='id' value='" + r.getId() + "'></form></td><td><form method='post' action='RejectRefundServlet' id='RR" + r.getId() + "'><button class='button' type='button' onclick=\"Confirm.render('Are you sure want to reject this refund?', 'RR" + r.getId() + "')\"><i class='fa fa-times'></i></button><input hidden type='text' name='id' value='" + r.getId() + "'></form></td><td><form method='post' action='DeleteRefundServlet' id='RE" + r.getId() + "'><button class='button' type='button' onclick=\"Confirm.render('Are you sure want to delete this request?', 'RE" + r.getId() + "')\"><i class='fa fa-trash'></i></button><input hidden type='text' name='id' value='" + r.getId() + "'></form></td>");
	    				out.print("</tr>");
	    				
	    			}
	    		
	    			out.print("</table></div>");
	    			
    			} else {
    				
    				out.print("<div style='margin: 4vw; color: grey;'>No Refund Requests</div>");
    				
    			}
    		%>
    	</div>
    </div>
    <%
		if (request.getAttribute("result") != null) {
 			out.print("<script type='text/javascript'>Alert.render('"+request.getAttribute("result")+"')</script>");
		}
	%>
</body>
</html>