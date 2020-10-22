function LoginPop() {
	
    this.render = function() {
        
        var winW = window.innerWidth;
        var winH = window.innerHeight;
        var dialogoverlay = document.getElementById('overlay');
        var dialogbox = document.getElementById('logbox');
        
        overlay.style.display = "block";
        overlay.style.height = winH + "px";
        logbox.style.left = (winW / 2) - (400 * .5) + "px";
        logbox.style.top = "30%";
        logbox.style.display = "block";
        
        /*document.getElementById('logboxhead').innerHTML = '<img src="img/logo2.png" width="100vw" height="50vw">';
        document.getElementById('logboxbody').innerHTML = '';
        document.getElementById('logboxfoot').innerHTML = '<button style="width: 5vw" class="button" onclick="Login.close()">OK</button>';*/
        
    }
    
    this.close = function() {
        
        document.getElementById('logbox').style.display = "none";
        document.getElementById('overlay').style.display = "none";
        
    }
    
}

var Login = new LoginPop();