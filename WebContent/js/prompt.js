function CustomPrompt(){
	
	this.render = function(dialog, index, servlet) {
		
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
		document.getElementById('alertboxbody').innerHTML = '<form id="val" method="post" action="' + servlet + '"><input autocomplete="off" required type="text" class="textbox" name="val" style="width: 80%; margin-top: 1vh;" placeholder="' + dialog + '"><input hidden name="ind" value="' + index + '"></form>';
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

var Prompt = new CustomPrompt();