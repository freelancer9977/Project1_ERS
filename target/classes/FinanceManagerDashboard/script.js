let user;

window.onload = async function(){

    let respose = await fetch("http://localhost:9001/session")

    let responseBody = await respose.json();

    // if(!responseBody.success){
    //     window.location = "../";
    // }

    user = responseBody.data;

    getAllReinb();
}

async function getAllReinb(){
    let response = await fetch(`http://localhost:9001/user/FM`);

    let responseBody = await response.json();

    console.log(responseBody)

    let list = responseBody.data;

    list.forEach(element => {
        createReimbursement(element)
    });
}

function createReimbursement(list){
    let listContainer = document.getElementById("tbody");

    let listCard = document.createElement("tr");
    listCard.className = "list-card"

    listCard.innerHTML = `
        <tr>
            <td>${list.id}</td>
            <td>${list.amount}</td>
            <td>${list.submitted}</td>
            <td>${list.resolved}</td>
            <td>${list.description}</td>
            <td>${list.author_id}</td>
            <td>${list.resolver_id}</td>
            <td>${list.status_id}</td>
            <td>${list.type_id}</td>
        </tr>`;
        
        
    listContainer.appendChild(listCard);
}

async function changeStatus(event){

    let idInput = document.getElementById("Id");
    let statusInput = document.getElementById("status");

    if(statusInput.value == 2){
        let response = await fetch(`http://localhost:9001/user/${user.id}/reimbursement/${idInput.value}/approved`,{
            method: "PATCH",
        })
    }else if(statusInput.value == 3){
        let response = await fetch(`http://localhost:9001/user/${user.id}/reimbursement/${idInput.value}/denied`,{
            method: "PATCH",
        })
    }

    getAllReinb();

    idInput = 0;
    statusInput = 0;
}

async function filterByStatus(event){
    event.preventDefault();

    let statusInput = document.getElementById("statusF").value;

    console.log(statusInput);

    if(statusInput == 0){
        getAllReinb();
    }else if(statusInput == 1){
        filterPending();
    }else if(statusInput == 2){
        filterApproved();
    }else if(statusInput == 3){
        filterDenied()
    }

    statusInput = 0;
}

async function filterPending(){
    let response = await fetch(`http://localhost:9001/user/reimbursement/pending`);

    let responseBody = await response.json();

    console.log(responseBody)

    let list = responseBody.data;

    list.forEach(element => {
        createReimbursement(element)
    });
}

async function filterApproved(){
    let response = await fetch(`http://localhost:9001/user/reimbursement/approved`);

    let responseBody = await response.json();

    console.log(responseBody)

    let list = responseBody.data;

    list.forEach(element => {
        createReimbursement(element)
    });
}

async function filterDenied(){
    let response = await fetch(`http://localhost:9001/user/reimbursement/denied`);

    let responseBody = await response.json();

    console.log(responseBody)

    let list = responseBody.data;

    list.forEach(element => {
        createReimbursement(element)
    });
}