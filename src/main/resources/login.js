window.onload = async function(){
    let response = await fetch(`http://localhost:9001/session`);
    let responseBody = await response.json();

    if(responseBody.success){
        window.location = "./EmployeeDashboard";
        if(responseBody.data.role == 1){
            window.location = "./EmployeeDashboard?userId=" + responseBody.data.id;
        }
        else if(responseBody.data.role == 2){
            window.location = "./FinanceManagerDashboard?userId=" + responseBody.data.id
        }
    }
    
}


document.getElementById("login-form").addEventListener("submit", async function (event){

    event.preventDefault();

    let usernameInput = document.getElementById("username");
    let passwordInput = document.getElementById("password");

    let user = {
        username: usernameInput.value,
        password: passwordInput.value
    }

    let DBoutput = await fetch("http://localhost:9001/session",{
        method: "POST",
        body: JSON.stringify(user)
    }) 

    let respose = await DBoutput.json();

    if(respose.output == false){
        let messageOutput = document.getElementById("message");
        messageOutput.innerText = respose.message
    }
    else{
        console.log("Login Successful",respose.data)
        //move user to either dashboard base on the data/user role
        if(respose.data.role == 1){
            window.location = "./EmployeeDashboard?userId=" + respose.data.id;
        }
        else if(respose.data.role == 2){
            window.location = "./FinanceManagerDashboard?userId=" + respose.data.id;
        }
    }
})