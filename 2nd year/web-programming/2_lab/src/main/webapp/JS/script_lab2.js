var manualInput = false;
function setChoiceR(event) {
    if (event.target.tagName === 'BUTTON') {
        var buttons = document.querySelectorAll("#radio-buttons_R button");
        buttons.forEach(function (button) {
            button.classList.remove("selected");
        });

        event.target.classList.add("selected");
        document.getElementById("input_R").value = event.target.textContent;

        var canvas = document.getElementById('coordinateCanvas');
        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawPoints();
        drawGraph();

    }
}

function setChoiceX(event) {
    if (event.target.tagName === 'BUTTON') {
        var buttons = document.querySelectorAll("#radio-buttons_X button");
        buttons.forEach(function (button) {
            button.classList.remove("selected");
        });

        event.target.classList.add("selected");
        document.getElementById("input_X").value = event.target.textContent;

        var canvas = document.getElementById('coordinateCanvas');
        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawPoints();
        drawGraph();

    }
    manualInput = true;
}

function setChoiceY() {
    var textElement = document.getElementById("input_Y");
    var textValue = textElement.value.trim();

    textValue = textValue.replace(",", ".");
    if (textElement) {
        document.getElementById("input_Y").value = textValue;
        var canvas = document.getElementById('coordinateCanvas');
        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawPoints();
        drawGraph();
    }
    manualInput = true;
}

// Функция для проверки формы
function validateForm() {
    var xChecked = false;
    var yInput = document.getElementById("input_Y");
    var yValue = yInput.value.trim();
    yValue = yValue.replace(",", ".");

    var selectedX = parseFloat(document.getElementById("input_X").value);
    var selectedR = parseFloat(document.getElementById("input_R").value);

    clearMessages();

    if (isNaN(selectedR)) {
        showMessage("Пожалуйста, введите значение R");
        return false;
    }
    if (isNaN(selectedX)) {
        showMessage("Пожалуйста, введите значение X");
        return false;
    }
    if (isNaN(yValue) || yValue < -3 || yValue > 3) {
        showMessage("Пожалуйста, введите значение Y в диапазоне от -3 до 3");
        yInput.value = "";
        return false;
    }

    // Сохранение данных в локальном хранилище
    var formData = {
        xChecked: selectedX,
        yValue: yValue,
        selectedR: selectedR
    };
    localStorage.setItem("formData", JSON.stringify(formData));

    return true;
}

// Функция для получения данных из локального хранилища и восстановления
function restoreFormData() {
    var storedFormData = localStorage.getItem("formData");
    if (storedFormData !== null) {
        var formData = JSON.parse(storedFormData);

        var xRadios = document.querySelectorAll(".radio-buttons_X button");
        xRadios.forEach(function (button) {
            if (button.textContent === formData.xChecked.toString()) {
                button.classList.add("selected");
            }
        });

        document.getElementById("input_Y").value = formData.yValue;

        var rButtons = document.querySelectorAll("#radio-buttons_R button");
        rButtons.forEach(function (button) {
            if (button.textContent === formData.selectedR.toString()) {
                button.classList.add("selected");
            }
        });
        document.getElementById("input_R").value = formData.selectedR;
    }
}

// Функция для отображения сообщения
function showMessage(message) {
    var messageBox = document.getElementById("message-box");
    var messageElement = document.createElement("div");
    messageElement.className = "message";
    messageElement.textContent = message;
    messageBox.appendChild(messageElement);
    messageBox.style.display = "block";

    setTimeout(function () {
        messageElement.style.opacity = "0";

        setTimeout(function () {
            messageElement.remove();
            if (messageBox.children.length === 0) {
                messageBox.style.display = "none";
            }
        }, 500);
    }, 2000);
}

// Функция для очистки сообщений
function clearMessages() {
    var messageBox = document.getElementById("message-box");
    messageBox.style.display = "none";
    messageBox.textContent = "";
}

// Функция для отрисовки графика
function drawGraphOnLoad() {
    var selectedR = localStorage.getItem("selectedR");
    if (selectedR !== null) {
        var buttons = document.querySelectorAll(".radio-buttons button");
        buttons.forEach(function (button) {
            if (button.textContent === selectedR) {
                button.classList.add("selected");
                document.getElementById("input_R").value = selectedR;
            }
        });
    }
}


// Функция для отправки формы
function submitForm(event) {
    event.preventDefault();

    if (!validateForm()) {
        return;
    }

    var form = event.target;
    var formData = new FormData(form);

    var scriptExecutionTime = performance.now();

    formData.append("scriptExecutionTime", scriptExecutionTime.toString());

    var updatedParams = new URLSearchParams(formData);

    var newUrl = new URL(form.action);
    newUrl.search = updatedParams;

    fetch(newUrl, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                window.location.href = 'results_lab_2.jsp';
            } else {
                console.error('Server responded with an error:', response.status);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        })
        .then(data => {
            var scriptEndTime = performance.now();
            var executionTime = (scriptEndTime - scriptExecutionTime) / 1000;
            executionTime = executionTime.toFixed(4);

            document.body.innerHTML = data;

            console.log('Время выполнения скрипта: ' + executionTime + ' сек');
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

document.addEventListener("DOMContentLoaded", function () {
    drawGraph();
    drawGraphOnLoad();
    restoreFormData();
    drawPoints();

    var form = document.getElementById("data-form");
    form.addEventListener("submit", function (event) {
        submitForm(event);
    });
});