function alertFilename() {
	var thefile = document.getElementById('inputfile');
	
	if (thefile.value != "")
		document.getElementById("up").innerHTML = thefile.value.replace(/.*[\/\\]/, '');
	else
		document.getElementById("up").innerHTML = "Upload a File";
}