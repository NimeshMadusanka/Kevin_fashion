function editProfile() {
	
	document.getElementById("fname").disabled = false;
	document.getElementById("lname").disabled = false;
	document.getElementById("email").disabled = false;
	document.getElementById("pno").disabled = false;
	document.getElementById("st").disabled = false;
	document.getElementById("ct").disabled = false;
	document.getElementById("di").disabled = false;
	document.getElementById("tel").disabled = false;
	
	document.getElementById("fname").value = "";
	document.getElementById("lname").value = "";
	document.getElementById("email").value = "";
	document.getElementById("pno").value = "";
	document.getElementById("st").value = "";
	document.getElementById("ct").value = "";
	document.getElementById("di").value = "";
	document.getElementById("tel").value = "";
	
	document.getElementById("lname").disabled = false;
	document.getElementById("email").disabled = false;
	document.getElementById("pno").disabled = false;
	document.getElementById("st").disabled = false;
	document.getElementById("ct").disabled = false;
	document.getElementById("di").disabled = false;
	document.getElementById("tel").disabled = false;
	
	document.getElementById("edit").hidden = true;
	document.getElementById("delete").hidden = true;
	document.getElementById("cancel").hidden = false;
	document.getElementById("clear").hidden = false;
	document.getElementById("save").hidden = false;
	document.getElementById("pwrow").hidden = false;
	
	document.getElementById("protitle").innerHTML = "Edit Profile";
	
}

function cancelEdit(fname,lname,email,pno,st,ct,di,tel) {

	document.getElementById("fname").value = fname;
	document.getElementById("lname").value = lname;
	document.getElementById("email").value = email;
	document.getElementById("pno").value = pno;
	document.getElementById("st").value = st;
	document.getElementById("ct").value = ct;
	document.getElementById("di").value = di;
	document.getElementById("tel").value = tel;
	
	document.getElementById("fname").disabled = true;
	document.getElementById("lname").disabled = true;
	document.getElementById("email").disabled = true;
	document.getElementById("pno").disabled = true;
	document.getElementById("st").disabled = true;
	document.getElementById("ct").disabled = true;
	document.getElementById("di").disabled = true;
	document.getElementById("tel").disabled = true;
	
	document.getElementById("edit").hidden = false;
	document.getElementById("delete").hidden = false;
	document.getElementById("cancel").hidden = true;
	document.getElementById("clear").hidden = true;
	document.getElementById("save").hidden = true;
	document.getElementById("pwrow").hidden = true;
	
	document.getElementById("protitle").innerHTML = "Profile";
	
}