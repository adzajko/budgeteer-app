const updateForm = document.getElementById('update-form');
updateForm.style.display = 'none';

const submitLoginForm = () => {
    const userName = document.getElementById('username').value;
    const passCode = document.getElementById('password').value;
    const formBody = {
        userName: userName,
        passCode: passCode
    }

    fetch("http://localhost:8080/login", {
        method: "POST",
        body: JSON.stringify(formBody),
        headers: {'Content-Type': 'application/json'}
    }).then(response => response.json()).then(response => {
        console.log(response);
        updateForm.style.display = 'block';
    });
}

const resetLoginForm = () => {
    const form = document.getElementById('login-form');
}

const submitBudgetForm = () => {
    const targetGoal = document.getElementById('targetGoal').value;
    const allocatedBudget = document.getElementById('allocatedBudget').value;
    const updateValue = {
        targetGoal: targetGoal,
        allocatedBudget: allocatedBudget
    }
    fetch("http://localhost:8080/budget/goal", {
        method: 'PUT',
        body: JSON.stringify(updateValue),
        headers: {'Content-Type': 'application/json'}
    }).then(res => res.json()).then(res => alert(res.responseMessage));
}