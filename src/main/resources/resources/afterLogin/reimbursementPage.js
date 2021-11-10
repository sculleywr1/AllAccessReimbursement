function onload() {
    getMyReimbursements();

}

function CreateReimbursement() {
    let amount = document.getElementById('amount').value;
    let description = document.getElementById('description').value;
    let statusId = 3;
    let type = document.getElementById(`type`).value;
    let typeId;
    switch (type) {
        case `Lodging`: typeId = 1; break;
        case `Travel`: typeId=2; break;
        case `Food`: typeId=3; break;
        case `Other`: typeId=4; break;
    }
    let jsonObject = {
        "amount": amount,
        "description": description,
        "statusId" : statusId,
        "typeId" : typeId
    }


    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open("POST", "http://localhost:9003/api/reimbursements")
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.send(JSON.stringify(jsonObject));

    xmlhttp.onreadystatechange = function() {
        if(xmlhttp.readyState == 4 && xmlhttp.status == 201) {
            alert("Your reimbursement has been created. A financial Manager will review your request as soon as possible. Thank you for using All Access Reimbursement!")
        }
        else if (xmlhttp.readyState == 4 && xmlhttp.status == 403)
        { 
            alert("You are currently not logged in. Please log in and try again.")
        }
        else
        {
            alert("If you are getting this alert that means that something is wrong with the system. Please contact your administrator. This could happen if the database or server is not running. reimbursementpage.356")
        }
    }
}



function getMyReimbursements() {
    let myhtml = document.getElementById("myReimbursementsStart");
    let xmlhttp = new XMLHttpRequest();
    xmlhttp.open("GET", "http://localhost:9003/api/reimbursement/mine", true);
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
                console.log(reimbursement)
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
                descriptionTd.className="col-1";
                newTableRow.append(descriptionTd);
                let authorIdTd = document.createElement("td");
                authorIdTd.append(authorId);
                authorIdTd.className= "col-1"
                newTableRow.append(authorIdTd);
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
            window.location.redirect(`/index.html`);
        }
    }
}