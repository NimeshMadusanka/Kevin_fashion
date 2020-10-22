function reply(email,fname,lname,val) {

		if (val == "reply")
			document.getElementById("tile").innerHTML = "Write reply to " + fname + " " + lname + "'s review";
		else
			document.getElementById("tile").innerHTML = "Edit reply to " + fname + " " + lname + "'s review";
		
    	document.getElementById("em").value = email;
    	document.getElementById("rep").hidden = false;
    	
}