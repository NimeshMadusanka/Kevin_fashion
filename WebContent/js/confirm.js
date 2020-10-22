function CustomConfirm() {
	
	this.render = function(dialog,index){
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
	    document.getElementById('alertboxbody').innerHTML = dialog;
		document.getElementById('alertboxfoot').innerHTML = '<button class="button" onclick="Confirm.yes(\'' + index + '\')"><i class="fas fa-check"></i></button>&emsp;<button class="button" onclick="Confirm.no()"><i class="fa fa-times"></i></button>';
		
	}
	
	this.no = function() {
		
		document.getElementById('alertbox').style.display = "none";
		document.getElementById('overlay').style.display = "none";
		
	}
	
	this.yes = function(index) {
		
		document.forms[index].submit();
		document.getElementById('alertbox').style.display = "none";
		document.getElementById('overlay').style.display = "none";
		
	}
	
}

var Confirm = new CustomConfirm();