window.onload = function() {
    document.getElementById(`loginButton`).addEventListener('click', loginFunction);
    logout();
}

function loginFunction() {

    console.log('in the loginfunction')
    let username = document.getElementById("myUsername").value;
    let password = document.getElementById("myPassword").value;

    let xmlhttp = new XMLHttpRequest();   // new HttpRequest instance 
    xmlhttp.open("POST", "http://localhost:9003/api/login");
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify({userName: username, password: password}));

    xmlhttp.onreadystatechange = function() {

        console.log(xmlhttp.status)

        
        if (xmlhttp.readyState == 4 && xmlhttp.status == 403)
        { 
            window.location.replace(`afterLogin/badlogin.html`)
        }
        else if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        { 
            window.location.replace(`/afterLogin/reimbursements.html`)
        }
        else
        { 
            window.location.replace(`afterLogin/badlogin.html`)
        }
    }

}
function logout() {
    let xmlhttp2 = new XMLHttpRequest();
    xmlhttp2.open("GET", "http://localhost:9003/api/logout", true);
    xmlhttp2.send();
    
    xmlhttp2.onreadystatechange = function() {
        if (xmlhttp2.readyState == 4 && xmlhttp2.status == 200)
        {
            alert("ready to login!")
        }
    }
}