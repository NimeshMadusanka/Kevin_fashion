window.onload = function() {

	document.getElementById('odel').disabled = true;
	
	/*----------LOAD ORDERS----------*/
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {

			var items = JSON.parse(this.responseText);
			var doc = document.getElementById("olist");
			doc.innerHTML = "<option value='' disabled selected>Select Order</option>";

			items.forEach(function(item) {
				doc.innerHTML += "<option value=" + item.id + ">" + item.id
						+ " - " + item.description + "</option>";
			})
		}

	};

	xhttp.open("GET", "GetOrdersServlet", true);
	xhttp.send();
	
	/*----------LOAD CREDIT CARDS----------*/
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {

			var items = JSON.parse(this.responseText);
			var doc = document.getElementById("clist");
			doc.innerHTML = "<option value='' disabled selected>Select Credit Card</option>";

			items.forEach(function(item) {
				doc.innerHTML += "<option value=" + item.cardNo + ">" + item.network
						+ " - " + item.cardNo + "</option>";
			})
		}

	};

	xhttp.open("GET", "GetCreditCardsServlet", true);
	xhttp.send();

}

function getOrderDetails(oFormElement) {

	var value = document.getElementById("olist").value;
	document.getElementById('odel').disabled = false;

	if (value != "") {

		document.getElementById("orid").value = value;

		var xhr = new XMLHttpRequest();
		xhr.onerror = function() {
			Alert.render(xhr.responseText);
		}

		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				document.getElementById("orderdiv").hidden = false;

				var item = JSON.parse(this.responseText);
				var doc = document.getElementById("orderdiv");
				var status, cost;

				if (item.cost == -1)
					cost = "Not Accepted";
				else
					cost = item.cost;

				if (item.accepted == false)
					status = "Not Accepted";
				else
					status = "Accepted";
				
				if (item.payed == false)
					payed = "Not Payed";
				else
					payed = "Payed"; 

				doc.innerHTML = "<div class='or' style='padding-bottom: 2.2vw'><div style='grid-row: 1/2'><img src='"
						+ item.imagePath
						+ "' style='width: 100%; height: 100%'></div><div style='grid-row: 2/3; padding: 1vw;'><table style='margin: 0; border-spacing: 0.5vw; text-align: start'><tr><td><b>"
						+ "Quantity"
						+ "<td><b> : </b></td>"
						+ "</b></td><td>"
						+ item.quantity
						+ "</td></tr><tr><td><b>"
						+ "Status"
						+ "<td><b> : </b></td>"
						+ "</b></td><td>"
						+ status
						+ "</td></tr><td><b>"
						+ "Cost"
						+ "<td><b> : </b></td>"
						+ "</b></td><td>"
						+ cost
						+ "</td></tr><td><b>"
    					+ "Payment"
    					+ "<td><b> : </b></td>"
    					+ "</b></td><td>"
    					+ payed
    					+ "</td></tr><td><b>"
						+ "Description"
						+ "<td><b> : </b></td>"
						+ "</b></td><td>"
						+ item.description
						+ "</td></tr>"
						+ "<tr><td colspan='3' style='text-align: center; padding: 2vw 0'><form method='post' action='PayOrderServlet'>" + document.getElementById('cc').innerHTML + "<button class='button' type='submit' style='width: 8vw; border: 0'><i class='fa fa-dollar-sign'></i>&ensp;Pay</button><input hidden type='text' name='amount' value='" + item.cost + "'><input hidden type='text' name='id' value='" + item.id + "'></form></td></tr>"
						+ "</table></div></div>";

			}
		};

		xhr.open(oFormElement.method, oFormElement.action, true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send("orderId=" + value);

	}

}

function EditOrder() {

	this.render = function(dialog, servlet) {
		
		if (document.getElementById("olist").value != "") {

			var winW = window.innerWidth;
			var winH = window.innerHeight;
			var dialogoverlay = document.getElementById('overlay');
			var dialogbox = document.getElementById('alertbox');
			dialogoverlay.style.display = "block";
			dialogoverlay.style.height = winH + "px";
			dialogbox.style.left = (winW / 2) - (550 * .5) + "px";
			dialogbox.style.top = "30%";
			dialogbox.style.display = "block";
			document.getElementById('alertboxhead').innerHTML = '<img src="img/logo.png" width="164vw" height="33vw">';
			document.getElementById('alertboxbody').innerHTML = '<form id="val" method="post" action="'
					+ servlet
					+ '"><input autocomplete="off" required type="number" min="20" class="textbox" name="quantity" style="width: 80%; margin-top: 1vh;" placeholder="'
					+ dialog
					+ '"><input hidden name="id" type="number" value="'
					+ document.getElementById("olist").value + '"></form>';
			document.getElementById('alertboxfoot').innerHTML = '<button class="button" onclick="Order.ok()">OK</button>&emsp;&emsp;<button class="button" onclick="Order.cancel()">Cancel</button>';

		} else {
			
			Alert.render('Please Select an Order');
			
		}

	}

	this.cancel = function() {

		document.getElementById('alertbox').style.display = "none";
		document.getElementById('overlay').style.display = "none";

	}

	this.ok = function() {

		document.forms['val'].submit();
		document.getElementById('alertbox').style.display = "none";
		document.getElementById('overlay').style.display = "none";

	}

}

var Order = new EditOrder();