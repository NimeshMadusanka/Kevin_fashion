window.onload = function loadItemList() {
	
	document.getElementById("itmtab").style.background = "rgba(183, 135, 61, 1)";
	document.getElementById("itmtab").style.boxShadow = "0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3)";
	document.getElementById("itmtab").style.color = "white";
	
	document.getElementById("itab").hidden = false;
	document.getElementById("atab").hidden = true;
	
	document.getElementById("adsub").disabled = true;
	document.getElementById("adsub").hidden = true;
	
	document.getElementById("edaucdiv").hidden = true;
	
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

function getAuctionItemDetails(oFormElement) {

	var value = document.getElementById("auclist").value;
	document.getElementById("aucsub").disabled = false;
	document.getElementById("asub").hidden = false;

	if (value != "") {

		document.getElementById("aucsub").value = value;
		document.getElementById("ida").value = value;
		document.getElementById("eau").value = value;

		var xhr = new XMLHttpRequest();
		xhr.onerror = function() {
			Alert.render(xhr.responseText);
		}

		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				
				document.getElementById("itmdiv").hidden = false;

				var item = JSON.parse(this.responseText);
				var doc = document.getElementById("itmdiv");

				doc.innerHTML = "<div class='item' style='padding-bottom: 2.2vw'><div style='grid-row: 1/4'><img src='"
						+ item.imagePath
						+ "' style='width: 100%; height: 100%'></div><div style='grid-row: 4/8; padding: 1vw;'><table style='margin: 0 auto; border-spacing: 0.5vw; text-align: start'><tr><td><b>"
						+ "Name" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.name + "</td></tr><tr><td><b>"
						+ "Start Price" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.startPrice + "</td></tr><td><b>"
						+ "Category" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.category + "</td></tr><td><b>"
						+ "Color" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.color + "</td></tr><td><b>"
						+ "Size" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.size + "</td></tr><td><b>"
						+ "Quantity" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.quantity + "</td></tr><td><b>"
						+ "Bid Duration" + "</b></td><td><b> : </b></td>"
						+ "</b></td><td>" + item.durationDays + "D&ensp;"+ item.durationHours + "H&ensp;" + item.durationMinutes + "M</td></tr>"
						+ "</table></div></div>";

			}
		};

		xhr.open(oFormElement.method, oFormElement.action, true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send("aucId=" + value);

	}

}

function getItemDetails(oFormElement) {

	var value = document.getElementById("itmlist").value;
	document.getElementById("adsub").disabled = false;
	document.getElementById("adsub").hidden = false;

	if (value != "") {

		document.getElementById("idsub").value = value;
		document.getElementById("idauc").value = value;

		var xhr = new XMLHttpRequest();
		xhr.onerror = function() {
			Alert.render(xhr.responseText);
		}

		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				
				document.getElementById("itmdiv").hidden = false;

				var item = JSON.parse(this.responseText);
				var doc = document.getElementById("itmdiv");

				doc.innerHTML = "<div class='item' style='padding-bottom: 2.2vw'><div style='grid-row: 1/4'><img src='"
						+ item.imagePath
						+ "' style='width: 100%; height: 100%'></div><div style='grid-row: 4/8; padding: 1vw;'><table style='margin: 0 auto; border-spacing: 0.5vw; text-align: start'><tr><td><b>"
						+ "Name" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.name + "</td></tr><tr><td><b>"
						+ "Price" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.price + "</td></tr><td><b>"
						+ "Category" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.category + "</td></tr><td><b>"
						+ "Color" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.color + "</td></tr><td><b>"
						+ "Size" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.size + "</td></tr><td><b>"
						+ "Quantity" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.quantity + "</td></tr><td><b>"
						+ "Description" + "<td><b> : </b></td>"
						+ "</b></td><td>" + item.description + "</td></tr>"
						+ "</table></div></div>";

			}
		};

		xhr.open(oFormElement.method, oFormElement.action, true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send("itemId=" + value);

	}

}

function switchTab(value) {
	
	document.getElementById("itmdiv").hidden = true;
	
	var item = document.getElementById("itmtab");
	var auc = document.getElementById("auctab");
	var itab = document.getElementById("itab");
	var atab = document.getElementById("atab");
	
	if (value == "itm") {
		item.style.background = "rgba(183, 135, 61, 1)";
		item.style.boxShadow = "0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3)";
		item.style.color = "white";
		auc.style.background = "white";
		auc.style.boxShadow = "0 0 0 0";
		auc.style.color = "black";
		itab.hidden = false;
		atab.hidden = true;
		document.getElementById("aucsub").value = "";
		document.getElementById("idsub").value = "";
		document.getElementById("idauc").value = "";
		document.getElementById("edaucdiv").hidden = true;
		
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var items = JSON.parse(this.responseText);
				var doc = document.getElementById("itmlist");
				doc.innerHTML = "<option value='' disabled selected>Select Item</option>";

				items.forEach(function(item) {
					doc.innerHTML += "<option value=" + item.id + ">" + item.id
							+ " - " + item.name + "</option>";
				})
			}

		};

		xhttp.open("GET", "GetItemsServlet", true);
		xhttp.send();
	}
	
	if (value == "auc") {
		auc.style.background = "rgba(183, 135, 61, 1)";
		auc.style.boxShadow = "0 0.4629629629629629vh 0.5208333333333334vw 0 rgba(0,0,0,0.3)";
		auc.style.color = "white";
		item.style.background = "white";
		item.style.boxShadow = "0 0 0 0";
		item.style.color = "black";
		atab.hidden = false;
		itab.hidden = true;
		document.getElementById("adsub").disabled = true;
		document.getElementById("adsub").hidden = true;
		document.getElementById("aucsub").value = "";
		document.getElementById("idsub").value = "";
		
		/*----------LOAD AUCTION ITEMS----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var items = JSON.parse(this.responseText);
				var doc = document.getElementById("auclist");
				doc.innerHTML = "<option value='' disabled selected>Select Auction Item</option>";

				items.forEach(function(item) {
					doc.innerHTML += "<option value=" + item.id + ">" + item.id
							+ " - " + item.name + "</option>";
				})
			}

		};
		
		xhttp.open("GET", "GetAuctionItemsServlet", true);
		xhttp.send();
	}
	
}

function deleteAuctionItem(oFormElement) {

	var id = document.getElementById("aucsub").value;
	document.getElementById("aucsub").value = "";
	document.getElementById("auclist").value = "";

	if (id != "") {

		var xhr = new XMLHttpRequest();
		xhr.onerror = function() {
			Alert.render(xhr.responseText);
		}

		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var message = JSON.parse(this.responseText);
				Alert.render(message);
			}
		};

		xhr.open(oFormElement.method, oFormElement.action, true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send("aucId=" + id);

		/*----------LOAD AUCTION ITEMS----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var items = JSON.parse(this.responseText);
				var doc = document.getElementById("auclist");
				doc.innerHTML = "<option value='' disabled selected>Select Auction Item</option>";
				document.getElementById("itmdiv").innerHTML = "";

				items.forEach(function(item) {
					doc.innerHTML += "<option value=" + item.id + ">" + item.id
							+ " - " + item.name + "</option>";
				})
			}

		};
		
		xhttp.open("GET", "GetAuctionItemsServlet", true);
		xhttp.send();

	} else {

		Alert.render("Please Select an Item");

	}

}

function editAuction() {
	
	var id = document.getElementById("aucsub").value;
	
	if (id != "") {
		
		document.getElementById("edaucdiv").hidden = false;
		
	} else {
		
		Alert.render("Please Select an Item");
		
	}
	
}