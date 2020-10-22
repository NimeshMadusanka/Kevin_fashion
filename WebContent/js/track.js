window.onload = function loadItemList() {
	
	/*----------LOAD AUCTIONS----------*/
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

function getAuctionItemDetails(oFormElement) {

	var value = document.getElementById("auclist").value;

	if (value != "") {

		document.getElementById("aucid").value = value;

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