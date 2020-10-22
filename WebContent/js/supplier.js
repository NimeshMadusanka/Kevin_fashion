window.onload = function loadSupplier() {

	/*----------LOAD SUPPLIERS----------*/
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {

			var suppliers = JSON.parse(this.responseText);
			var doc = document.getElementById("suplist");

			suppliers.forEach(function(supplier) {
				doc.innerHTML += "<option value=" + supplier.id + ">"
						+ supplier.id + " - " + supplier.name + "</option>";
			})
		}

	};

	xhttp.open("GET", "GetSuppliersServlet", true);
	xhttp.send();

	document.getElementById("edtsup").hidden = true;
	document.getElementById("delsup").disabled = true;

}

function getSupplierDetails(oFormElement) {

	var value = document.getElementById("suplist").value;
	document.getElementById("delsup").disabled = true;

	if (value != "") {

		document.getElementById("supid").value = value;
		document.getElementById("isup").value = value;

		var xhr = new XMLHttpRequest();
		xhr.onerror = function() {
			Alert.render(xhr.responseText);
		}

		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				document.getElementById("supdiv").hidden = false;

				var supplier = JSON.parse(this.responseText);
				var doc = document.getElementById("supdiv");

				doc.innerHTML = "<table><tr><td><b>Name</b></td><td>  :  </td><td>"
						+ supplier.name
						+ "</td></tr><tr><td><b>Telephone</b></td><td>  :  </td><td>"
						+ supplier.telephone
						+ "</td></tr><tr><td><b>Address</b></td><td>  :  </td><td>"
						+ supplier.address + "</td></tr></table>"

			}
		};

		xhr.open(oFormElement.method, oFormElement.action, true);
		xhr.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		xhr.send("supId=" + value);

	}

}

function editSupplier() {

	document.getElementById("edtsup").hidden = false;
	document.getElementById("addsup").hidden = true;
	
}