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
    
    let response = await fetch(`http://localhost:9001/user?userId=${user.id}`);

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

async function createnewReimbursement (event){
    event.preventDefault();

    let amountInput = document.getElementById("Amount");
    let descriptionInput = document.getElementById("description");
    let typeInput = document.getElementById("type");

    let list = {amount: amountInput.value,author_id: user.id, type_id: typeInput.value,description: descriptionInput.value}

    let response = await fetch("http://localhost:9001/user/reimbursement",{
        method: "POST",
        body: JSON.stringify(list)
    })

    let responseBody = await response.json();

    console.log(responseBody)

    createReimbursement(responseBody.data);

    amountInput.value=""
    descriptionInput.value=""
    typeInput.value=0
}