function onload() {
    getAllUsers();
   
}

function getAllUsers() {
    let myhtml = document.getElementById("allusers");
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:9003/api/users", true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let users = JSON.parse(xmlhttp.responseText);

            for (const user of users) {
                let newTableRow = document.createElement("tr");
                newTableRow.className = "d-flex";
                let id = user.userId;
                let userName = user.userName;
                let roleId = user.roleId;

                

                let role;
                switch (roleId){
                    case 1: role = "Manager"; break;
                    case 2: role = "employee"; break;
                }
              
                let userNameTd = document.createElement("td")
                userNameTd.append(userName);
                userNameTd.className="col-1";
                newTableRow.append(userNameTd);
                let roleTd = document.createElement("td")
                roleTd.append(role);
                roleTd.className= "col-1";
                newTableRow.append(roleTd);
                let idTD = document.createElement("td");
                idTD.append(id);
                idTD.className= "col-10";
                newTableRow.append(idTD);


                myhtml.append(newTableRow);
            }


        }
        else
        {
            window.location.redirect(`/afterLogin/reimbursements.html`);
        }


    }
    

}


