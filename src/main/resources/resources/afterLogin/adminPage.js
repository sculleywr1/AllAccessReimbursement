function onload() {
    getAllReimbursements();
    document.getElementById("btn1").addEventListener("click", getRequested);
    document.getElementById("btn2").addEventListener("click", approve);
    document.getElementById("btn3").addEventListener("click", deny)
}

function approve(){
    let xmlhttp = new XMLHttpRequest();
    let idCode = document.getElementById("id").value;
    let myobj = {
        "reimbId": idCode,
        "statusId": 1
    }
    let statusOfRequested = document.getElementById("status").innerHTML;

    if (statusOfRequested == "Pending"){

        xmlhttp.open("PUT", "http://localhost:9003/api/reimbursement/");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(myobj));   

        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
            { 
                alert("Success")
            }
            else 
            {
                alert("Error")
            }
        }

    }
    else if (statusOfRequested == "")
    { 
        alert("You must populate the list below to approve or deny. Please hit the first button")
    }
    else
    {
        alert("You cannot change the approval or denial of a resolved ticket.")
    }
}

function deny(){
    let xmlhttp = new XMLHttpRequest();
    let idCode = document.getElementById("id").value;
    let myobj = {
        "reimbId": idCode,
        "statusId": 2
    }
    let statusOfRequested = document.getElementById("status").innerHTML;

    if (statusOfRequested == "Pending"){

        xmlhttp.open("PUT", "http://localhost:9003/api/reimbursement/");
        xmlhttp.setRequestHeader("Content-Type", "application/json");
        xmlhttp.send(JSON.stringify(myobj));   

        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200)
            { 
                alert("Success")
                window.location.reload(false);
            }
            else 
            {
                alert("Error")
            }
        }

    }
    else if (statusOfRequested == "")
    { 
        alert("You must populate the list below to approve or deny. Please hit the first button")
    }
    else
    {
        alert("You cannot change the approval or denial of a resolved ticket.")
    }

    
}

function getRequested() {
    let xmlhttp = new XMLHttpRequest();
    let idCode = document.getElementById("id").value;

    xmlhttp.open("GET", "http://localhost:9003/api/Reimbursement/" + idCode);
    xmlhttp.send();
    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
        { 
            let requestedReimbursement = JSON.parse(xmlhttp.responseText);
            console.log(requestedReimbursement);
            let dateSubmitted = new Date(requestedReimbursement.timeSubmitted);
            let dateResolved = new Date(requestedReimbursement.timeResolved);
            let statusId = requestedReimbursement.statusId;
            let typeId = requestedReimbursement.typeId;

            let status;
            switch (statusId){
                case 1: status = "Approved"; break;
                case 2: status = "Rejected"; break;
                case 3: status = "Pending";
            }

            let type;
            switch(typeId){
                case 1: type = "Lodging"; break;
                case 2: type = "Travel"; break;
                case 3: type = "Food"; break;
                case 4: type = "Other";
            }

            document.getElementById("reimbId").append(requestedReimbursement.reimbId);
            document.getElementById("amount").append(requestedReimbursement.amount);
            document.getElementById("timeSubmitted").append(dateSubmitted)
            document.getElementById("timeResolved").append(dateResolved);
            document.getElementById("description").append(requestedReimbursement.description);
            document.getElementById("authorId").append(requestedReimbursement.authorId);
            document.getElementById("resolverId").append(requestedReimbursement.resolverId);
            document.getElementById("status").append(status);
            document.getElementById("type").append(type);

        }
        else
        {
            window.location.redirect(`/afterLogin/reimbursements.html`);
        }
    }
}

function getAllReimbursements() {
    let myhtml = document.getElementById("allReimbursements");
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:9003/api/reimbursements", true);
    xmlhttp.send();

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            let myReimbursements = JSON.parse(xmlhttp.responseText);

            for (const reimbursement of myReimbursements) {
                let newTableRow = document.createElement("tr");
                newTableRow.className = "d-flex";
                let id = reimbursement.reimbId;
                let amount = reimbursement.amount;
                let timeSubmitted = reimbursement.timeSubmitted;
                let timeResolved = reimbursement.timeResolved;
                let description = reimbursement.description;
                let authorId = reimbursement.authorId;
                let resolverId = reimbursement.resolverId;
                let statusId = reimbursement.statusId;
                let typeId = reimbursement.typeId;

                

                let status;
                switch (statusId){
                    case 1: status = "Approved"; break;
                    case 2: status = "Rejected"; break;
                    case 3: status = "Pending";
                }

                let type;
                switch(typeId){
                    case 1: type = "Lodging"; break;
                    case 2: type = "Travel"; break;
                    case 3: type = "Food"; break;
                    case 4: type = "Other";
                }

                let dateSubmitted = new Date(timeSubmitted);
                timeSubmitted=dateSubmitted;
                let dateResolved = new Date(timeResolved);
                timeResolved=dateResolved;

                if (statusId === 3) {
                    timeResolved = "Not yet resolved";   
                }
                let idTD = document.createElement("td");
                idTD.append(id);
                idTD.className= "col-1";
                newTableRow.append(idTD);
                let amountTd = document.createElement("td")
                amountTd.append(amount);
                amountTd.className="col-1";
                newTableRow.append(amountTd);
                let timeSubmittedTd = document.createElement("td")
                timeSubmittedTd.append(timeSubmitted);
                timeSubmittedTd.className= "col-1";
                newTableRow.append(timeSubmittedTd);
                let timeResolvedTd = document.createElement("td");
                timeResolvedTd.append(timeResolved);
                timeResolvedTd.className = "col-1";
                newTableRow.append(timeResolvedTd);
                let descriptionTd = document.createElement("td");
                descriptionTd.append(description);
                descriptionTd.className="col-2";
                newTableRow.append(descriptionTd);
                let authorIdTd = document.createElement("td");
                authorIdTd.append(authorId);
                authorIdTd.className= "col-2"
                newTableRow.append(authorIdTd);
                let resolverIdTd = document.createElement("td");
                resolverIdTd.append(resolverId);
                resolverIdTd.className= "col-2";
                newTableRow.append(resolverIdTd);
                let statusTd = document.createElement("td");
                statusTd.append(status);
                statusTd.className = "col-1";
                newTableRow.append(statusTd);
                let typeTd = document.createElement("td");
                typeTd.append(type);
                typeTd.className = "col-1";
                newTableRow.append(typeTd);


                myhtml.append(newTableRow);
            }


        }
        else
        { 
            window.location(`/afterLogin/reimbursements.html`)
        }
    }
}