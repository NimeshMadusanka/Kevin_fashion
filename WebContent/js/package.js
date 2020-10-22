function editPackage(){
	
	this.render = function(index) {
		
		var winW = window.innerWidth;
	    var winH = window.innerHeight;
		var dialogoverlay = document.getElementById('overlay');
	    var dialogbox = document.getElementById('alertbox');
		dialogoverlay.style.display = "block";
	    dialogoverlay.style.height = winH+"px";
		dialogbox.style.left = (winW/2) - (550 * .5)+"px";
	    dialogbox.style.top = "30%";
	    dialogbox.style.display = "block";
		document.getElementById('alertboxhead').innerHTML = '<img src="img/logo.png" width="164vw" height="33vw">';
		document.getElementById('alertboxbody').innerHTML = '<h3 style="color: black; font-family: android;">Edit Package</h3><form id="val" method="post" action="EditPackageServlet"><input hidden name="id" type="text" value="' + index + '"><table style="border-spacing: 1vw;"><tr><td><input autocomplete="off" class="textbox" type="text" placeholder="Package Name" name="name" style="width: 12vw"></td><td><input autocomplete="off" class="textbox" type="number" step="0.01" min="0" max="100" placeholder="Discount Percentage" name="percentage" style="width: 12vw"></td></tr><tr><td colspan="2"><input autocomplete="off" class="textbox" type="number" step="0.01" min="0" placeholder="Price" name="price" style="width: 28vw"></td></tr></table></form>';
		document.getElementById('alertboxfoot').innerHTML = '<button class="button" onclick="Prompt.ok()">OK</button>&emsp;&emsp;<button class="button" onclick="Prompt.cancel()">Cancel</button>';
	
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

var EditPack = new editPackage();