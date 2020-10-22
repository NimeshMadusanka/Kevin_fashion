function editReview() {
	
	document.getElementById("rr").hidden = true;
	document.getElementById("rh").hidden = true;
	document.getElementById("rd").hidden = true;
	document.getElementById("dr").hidden = true;
	
	document.getElementById("err").hidden = false;
	document.getElementById("erd").hidden = false;
	document.getElementById("erh").hidden = false;
	document.getElementById("ers").hidden = false;
	document.getElementById("eru").hidden = false;
	document.getElementById("erc").hidden = false;
	document.getElementById("ere").hidden = true;
	
	document.getElementById("erd").value = "";
	
	document.getElementById("1-star").checked = false;
	document.getElementById("2-star").checked = false;
	document.getElementById("3-star").checked = false;
	document.getElementById("4-star").checked = false;
	document.getElementById("5-star").checked = false;
	
}

function cancelReviewEdit(des) {
	
	document.getElementById("rr").hidden = false;
	document.getElementById("rh").hidden = false;
	document.getElementById("rd").hidden = false;
	document.getElementById("dr").hidden = false;
	
	document.getElementById("err").hidden = true;
	document.getElementById("erd").hidden = true;
	document.getElementById("erh").hidden = true;
	document.getElementById("ers").hidden = true;
	document.getElementById("eru").hidden = true;
	document.getElementById("erc").hidden = true;
	document.getElementById("ere").hidden = true;
	
}

function seeReview() {
	
	var des = document.getElementById("desc").innerHTML;
	var val = document.getElementById("rat").innerHTML;
	
	document.getElementById("eru").hidden = true;
	document.getElementById("ere").hidden = false;
	document.getElementById("erd").value = des;
	
	if (val == 1)
		document.getElementById("1-star").checked = true;
	else if (val == 2)
		document.getElementById("2-star").checked = true;
	else if (val == 3)
		document.getElementById("3-star").checked = true;
	else if (val == 4)
		document.getElementById("4-star").checked = true;
	else if (val == 5)
		document.getElementById("5-star").checked = true;
	
}

function hideReview() {
	
	document.getElementById("eru").hidden = false;
	document.getElementById("ere").hidden = true;
	document.getElementById("erd").value = "";
	document.getElementsByName("fname").value = null;
	
	document.getElementById("1-star").checked = false;
	document.getElementById("2-star").checked = false;
	document.getElementById("3-star").checked = false;
	document.getElementById("4-star").checked = false;
	document.getElementById("5-star").checked = false;
	
}