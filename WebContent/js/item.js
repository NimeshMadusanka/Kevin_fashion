window.onload = function loadItemDetails() {

	document.getElementById("eim").style.display = "none";

	/*----------LOAD CATEGORIES----------*/
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {

			var categories = JSON.parse(this.responseText);
			var doc = document.getElementById("cat");
			var doc1 = document.getElementById("delcat");
			var doc2 = document.getElementById("ecat");

			categories.forEach(function(category) {
				doc.innerHTML += "<option value=" + category + ">" + category
						+ "</option>";
				doc1.innerHTML += "<option value=" + category + ">" + category
						+ "</option>";
				doc2.innerHTML += "<option value=" + category + ">" + category
						+ "</option>";
			})
		}

	};

	xhttp.open("GET", "GetCategoriesServlet", true);
	xhttp.send();

	/*----------LOAD SIZES----------*/
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {

			var sizes = JSON.parse(this.responseText);
			var doc = document.getElementById("siz");
			var doc1 = document.getElementById("delsiz");
			var doc2 = document.getElementById("esiz");

			sizes.forEach(function(size) {
				doc.innerHTML += "<option value=" + size + ">" + size
						+ "</option>";
				doc1.innerHTML += "<option value=" + size + ">" + size
						+ "</option>";
				doc2.innerHTML += "<option value=" + size + ">" + size
						+ "</option>";
			})
		}

	};

	xhttp.open("GET", "GetSizesServlet", true);
	xhttp.send();

	/*----------LOAD COLORS----------*/
	var xhttp = new XMLHttpRequest();

	xhttp.onreadystatechange = function() {

		if (this.readyState == 4 && this.status == 200) {

			var colors = JSON.parse(this.responseText);
			var doc = document.getElementById("col");
			var doc1 = document.getElementById("delcol");
			var doc2 = document.getElementById("edtcol");
			var doc3 = document.getElementById("ecol");

			colors.forEach(function(color) {
				doc.innerHTML += "<option value=" + color + ">" + color
						+ "</option>";
				doc1.innerHTML += "<option value=" + color + ">" + color
						+ "</option>";
				doc2.innerHTML += "<option value=" + color + ">" + color
						+ "</option>";
				doc3.innerHTML += "<option value=" + color + ">" + color
						+ "</option>";
			})
		}

	};

	xhttp.open("GET", "GetColorsServlet", true);
	xhttp.send();

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

function addCategory(oFormElement) {

	var category = document.getElementById("adct").value;
	document.getElementById("adct").value = "";

	if (category != "") {

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
		xhr.send("addcategory=" + category);

		/*----------LOAD CATEGORIES----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var categories = JSON.parse(this.responseText);
				var doc = document.getElementById("cat");
				var doc1 = document.getElementById("delcat");
				var doc2 = document.getElementById("ecat");
				doc.innerHTML = "<option value='' disabled selected>Category</option>";
				doc1.innerHTML = "<option value='' disabled selected>Select Category</option>";
				doc2.innerHTML = "<option value='' disabled selected>Select Category</option>";

				categories.forEach(function(category) {
					doc.innerHTML += "<option value=" + category + ">" + category
							+ "</option>";
					doc1.innerHTML += "<option value=" + category + ">" + category
							+ "</option>";
					doc2.innerHTML += "<option value=" + category + ">" + category
							+ "</option>";
				})
			}

		};

		xhttp.open("GET", "GetCategoriesServlet", false);
		xhttp.send();

	} else {

		Alert.render("Please Enter a Category");

	}

}

function deleteCategory(oFormElement) {

	var category = document.getElementById("delcat").value;
	document.getElementById("delcat").value = "";

	if (category != "") {

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
		xhr.send("delcategory=" + category);

		/*----------LOAD CATEGORIES----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var categories = JSON.parse(this.responseText);
				var doc = document.getElementById("cat");
				var doc1 = document.getElementById("delcat");
				var doc2 = document.getElementById("ecat");
				doc.innerHTML = "<option value='' disabled selected>Category</option>";
				doc1.innerHTML = "<option value='' disabled selected>Select Category</option>";
				doc2.innerHTML = "<option value='' disabled selected>Select Category</option>";

				categories.forEach(function(category) {
					doc.innerHTML += "<option value=" + category + ">" + category
							+ "</option>";
					doc1.innerHTML += "<option value=" + category + ">" + category
							+ "</option>";
					doc2.innerHTML += "<option value=" + category + ">" + category
							+ "</option>";
				})
			}

		};

		xhttp.open("GET", "GetCategoriesServlet", false);
		xhttp.send();

	} else {

		Alert.render("Please Select a Category");

	}

}

function addSize(oFormElement) {

	var size = document.getElementById("adsiz").value;
	document.getElementById("adsiz").value = "";

	if (size != "") {

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
		xhr.send("addsize=" + size);

		/*----------LOAD SIZES----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var sizes = JSON.parse(this.responseText);
				var doc = document.getElementById("siz");
				var doc1 = document.getElementById("delsiz");
				var doc2 = document.getElementById("esiz");
				doc.innerHTML = "<option value='' disabled selected>Size</option>";
				doc1.innerHTML = "<option value='' disabled selected>Select Size</option>";
				doc2.innerHTML = "<option value='' disabled selected>Select Size</option>";

				sizes.forEach(function(size) {
					doc.innerHTML += "<option value=" + size + ">" + size
							+ "</option>";
					doc1.innerHTML += "<option value=" + size + ">" + size
							+ "</option>";
					doc2.innerHTML += "<option value=" + size + ">" + size
							+ "</option>";
				})
			}

		};

		xhttp.open("GET", "GetSizesServlet", false);
		xhttp.send();

	} else {

		Alert.render("Please Enter a Size");

	}

}

function deleteSize(oFormElement) {

	var n = document.getElementById("delsiz");
	var size = n.options[n.selectedIndex].text;
	document.getElementById("delsiz").value = "";

	if (size != "") {

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
		xhr.send("delsize=" + size);

		/*----------LOAD SIZES----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var sizes = JSON.parse(this.responseText);
				var doc = document.getElementById("siz");
				var doc1 = document.getElementById("delsiz");
				var doc2 = document.getElementById("esiz");
				doc.innerHTML = "<option value='' disabled selected>Size</option>";
				doc1.innerHTML = "<option value='' disabled selected>Select Size</option>";
				doc2.innerHTML = "<option value='' disabled selected>Select Size</option>";

				sizes.forEach(function(size) {
					doc.innerHTML += "<option value=" + size + ">" + size
							+ "</option>";
					doc1.innerHTML += "<option value=" + size + ">" + size
							+ "</option>";
					doc2.innerHTML += "<option value=" + size + ">" + size
							+ "</option>";
				})
			}

		};

		xhttp.open("GET", "GetSizesServlet", false);
		xhttp.send();

	} else {

		Alert.render("Please Select a Size");

	}

}

function addColor(oFormElement) {

	var value = document.getElementById("adcolv").value;
	var name = document.getElementById("adcoln").value;
	document.getElementById("adcoln").value = "";

	if (name != "") {

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
		xhr.send("addvalue=" + value + "&addname=" + name);

		/*----------LOAD COLORS----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var colors = JSON.parse(this.responseText);
				var doc = document.getElementById("col");
				var doc1 = document.getElementById("delcol");
				var doc2 = document.getElementById("edtcol");
				var doc3 = document.getElementById("ecol");

				doc.innerHTML = "<option value='' disabled selected>Color</option>";
				doc1.innerHTML = "<option value='' disabled selected>Select Color</option>";
				doc2.innerHTML = "<option value='' disabled selected>Select Color</option>";
				doc3.innerHTML = "<option value='' disabled selected>Select Color</option>";
				

				colors.forEach(function(color) {
					doc.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
					doc1.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
					doc2.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
					doc3.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
				})
			}

		};

		xhttp.open("GET", "GetColorsServlet", false);
		xhttp.send();

	} else {

		Alert.render("Please Enter a Color Name");

	}

}

function deleteColor(oFormElement) {

	var n = document.getElementById("delcol");
	var name = n.options[n.selectedIndex].text;
	document.getElementById("delcol").value = "";

	if (name != "") {

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
		xhr.send("delcolor=" + name);

		/*----------LOAD COLORS----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var colors = JSON.parse(this.responseText);
				var doc = document.getElementById("col");
				var doc1 = document.getElementById("delcol");
				var doc2 = document.getElementById("edtcol");
				var doc3 = document.getElementById("ecol");

				doc.innerHTML = "<option value='' disabled selected>Color</option>";
				doc1.innerHTML = "<option value='' disabled selected>Select Color</option>";
				doc2.innerHTML = "<option value='' disabled selected>Select Color</option>";
				doc3.innerHTML = "<option value='' disabled selected>Select Color</option>";
				

				colors.forEach(function(color) {
					doc.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
					doc1.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
					doc2.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
					doc3.innerHTML += "<option value=" + color + ">" + color
							+ "</option>";
				})
			}

		};

		xhttp.open("GET", "GetColorsServlet", false);
		xhttp.send();

	} else {

		Alert.render("Please Select a Color");

	}

}

function editColor(oFormElement) {

	var v = document.getElementById("edtcol");
	var value = v.options[v.selectedIndex].text;
	var name = document.getElementById("edtncoln").value;
	document.getElementById("edtncoln").value = "";

	if (value != "") {

		if (name != "") {

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
			xhr.send("edtcolor=" + value + "&newname=" + name);

			/*----------LOAD COLORS----------*/
			var xhttp = new XMLHttpRequest();

			xhttp.onreadystatechange = function() {

				if (this.readyState == 4 && this.status == 200) {

					var colors = JSON.parse(this.responseText);
					var doc = document.getElementById("col");
					var doc1 = document.getElementById("delcol");
					var doc2 = document.getElementById("edtcol");
					var doc3 = document.getElementById("ecol");

					doc.innerHTML = "<option value='' disabled selected>Color</option>";
					doc1.innerHTML = "<option value='' disabled selected>Select Color</option>";
					doc2.innerHTML = "<option value='' disabled selected>Select Color</option>";
					doc3.innerHTML = "<option value='' disabled selected>Select Color</option>";
					

					colors.forEach(function(color) {
						doc.innerHTML += "<option value=" + color + ">" + color
								+ "</option>";
						doc1.innerHTML += "<option value=" + color + ">" + color
								+ "</option>";
						doc2.innerHTML += "<option value=" + color + ">" + color
								+ "</option>";
						doc3.innerHTML += "<option value=" + color + ">" + color
								+ "</option>";
					})
				}

			};

			xhttp.open("GET", "GetColorsServlet", true);
			xhttp.send();

		} else {

			Alert.render("Please Enter a Color Name");

		}

	} else {

		Alert.render("Please Select a Color Name");

	}

}

function displayCategories() {

	document.getElementById("catdiv").hidden = false;
	document.getElementById("sizdiv").hidden = true;
	document.getElementById("coldiv").hidden = true;
	document.getElementById("dddiv").hidden = true;

}

function displaySizes() {

	document.getElementById("catdiv").hidden = true;
	document.getElementById("sizdiv").hidden = false;
	document.getElementById("coldiv").hidden = true;
	document.getElementById("dddiv").hidden = true;

}

function displayColors() {

	document.getElementById("catdiv").hidden = true;
	document.getElementById("sizdiv").hidden = true;
	document.getElementById("coldiv").hidden = false;
	document.getElementById("dddiv").hidden = true;

}

function displayNone() {

	document.getElementById("catdiv").hidden = true;
	document.getElementById("sizdiv").hidden = true;
	document.getElementById("coldiv").hidden = true;
	document.getElementById("dddiv").hidden = false;

}

function getItemDetails(oFormElement) {

	var value = document.getElementById("itmlist").value;

	if (value != "") {

		document.getElementById("idsub").value = value;

		var xhr = new XMLHttpRequest();
		xhr.onerror = function() {
			Alert.render(xhr.responseText);
		}

		xhr.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {

				var item = JSON.parse(this.responseText);
				var doc = document.getElementById("itmdiv");

				doc.innerHTML = "<div class='item' style='padding-bottom: 2vw'><div style='grid-row: 1/4'><img src='"
						+ item.imagePath
						+ "' style='width: 100%; height: 100%'></div><div style='grid-row: 4/7; padding: 1vw;'><table style='margin: 0 auto; border-spacing: 0.5vw; text-align: start'><tr><td><b>"
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

function deleteItem(oFormElement) {

	var id = document.getElementById("itmlist").value;
	document.getElementById("itmlist").value = "";

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
		xhr.send("itemId=" + id);

		/*----------LOAD ITEMS----------*/
		var xhttp = new XMLHttpRequest();

		xhttp.onreadystatechange = function() {

			if (this.readyState == 4 && this.status == 200) {

				var items = JSON.parse(this.responseText);
				var doc = document.getElementById("itmlist");
				document.getElementById("itmdiv").innerHTML = "";

				items.forEach(function(item) {
					doc.innerHTML += "<option value=" + item.id + ">" + item.id
							+ " - " + item.name + "</option>";
				})
			}

		};

		xhttp.open("GET", "GetItemsServlet", true);
		xhttp.send();

	} else {

		Alert.render("Please Select an Item");

	}

}

function addItem() {

	document.getElementById("aim").hidden = false;
	document.getElementById("eim").style.display = "none";

}

function editItem() {

	document.getElementById("aim").hidden = true;
	document.getElementById("eim").style.display = "grid";

}

function itemEditCheck() {
	
	if (document.getElementById("idsub").value == "")
		Alert.render("Please Select an Item");
	
}

function itemEditColor() {
	
	var n = document.getElementById("ecol");
	
	document.getElementById("tecol").value = n.options[n.selectedIndex].text;
}

function itemAddColor() {
	
	var n = document.getElementById("col");
	
	document.getElementById("tcol").value = n.options[n.selectedIndex].text;
}

function itemEditSize() {
	
	var n = document.getElementById("esiz");
	
	document.getElementById("tesiz").value = n.options[n.selectedIndex].text;
}

function itemAddSize() {
	
	var n = document.getElementById("siz");
	
	document.getElementById("tsiz").value = n.options[n.selectedIndex].text;
}