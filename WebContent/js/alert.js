function CustomAlert() {
	
		this.render = function(dialog) {
			
			var winW = window.innerWidth;
			var winH = window.innerHeight;
			var dialogoverlay = document.getElementById('overlay');
			var dialogbox = document.getElementById('alertbox');
			
			overlay.style.display = "block";
			overlay.style.height = winH + "px";
			alertbox.style.left = (winW / 2) - (550 * .5) + "px";
			alertbox.style.top = "30%";
			alertbox.style.display = "block";
			
			document.getElementById('alertboxhead').innerHTML = '<img src="img/logo.png" width="164vw" height="33vw">';
			document.getElementById('alertboxbody').innerHTML = dialog;
			document.getElementById('alertboxfoot').innerHTML = '<button style="width: 5vw" class="button" onclick="Alert.ok()" style="margin-right: 1vw; margin-top: 2vw;">OK</button>';
			
		}
		
		this.ok = function() {
			
			document.getElementById('alertbox').style.display = "none";
			document.getElementById('overlay').style.display = "none";
			
		}
		
	}

var Alert = new CustomAlert();