window.onload = function() {

	/*----------LOAD DISPUTES----------*/
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {

			var items = JSON.parse(this.responseText);
			var doc = document.getElementById("olist");
			doc.innerHTML = "<option value='' disabled selected>Select Complaint</option>";

			items.forEach(function(item) {
				doc.innerHTML += "<option value=" + item.id + ">" + item.id
						+ " - " + item.description + "</option>";
			})
		}

	};

	xhttp.open("GET", "GetDisputesServlet", true);
	xhttp.send();

}

function getDisputeDetails(oFormElement) {

	var value = document.getElementById("olist").value;

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
				var status;
				
				if (item.response == null)
					status = "No Response";
				else
					status = item.response; 

				doc.innerHTML = "<div class='or' style='padding-bottom: 2.2vw; height: 25vw; grid-template-rows: 50% 50%'><div style='grid-row: 1/2'><img src='"
						+ item.imagePath
						+ "' style='width: 100%; height: 100%'></div><div style='grid-row: 2/3; padding: 1vw;'><table style='margin: 0; border-spacing: 0.5vw; text-align: start'><tr><td><b>"
						+ "Description"
						+ "<td><b> : </b></td>"
						+ "</b></td><td>"
						+ item.description
						+ "</td></tr><td><b>"
						+ "Response"
						+ "<td><b> : </b></td>"
						+ "</b></td><td>"
						+ status
						+ "</td></tr>"
						+ "</table></div></div>";

			}
		};

		xhr.open(oFormElement.method, oFormElement.action, true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send("disputeId=" + value);

	}

}

function EditDispute() {

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
					+ '"><input autocomplete="off" required type="text" class="textbox" name="description" style="width: 80%; margin-top: 1vh;" placeholder="'
					+ dialog
					+ '"><input hidden name="id" type="number" value="'
					+ document.getElementById("olist").value + '"></form>';
			document.getElementById('alertboxfoot').innerHTML = '<button class="button" onclick="Dispute.ok()">OK</button>&emsp;&emsp;<button class="button" onclick="Dispute.cancel()">Cancel</button>';

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

var Dispute = new EditDispute();