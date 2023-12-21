// Функция для установки выбранного значения R
function setChoiceR(value) {
    if (event.target.tagName === 'BUTTON') {
        var buttons = document.querySelectorAll(".radio-buttons button");
        buttons.forEach(function (button) {
            button.classList.remove("selected");
        });

        event.target.classList.add("selected");
        document.getElementById("input_R").value = event.target.textContent;

        var canvas = document.getElementById('coordinateCanvas');
        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawCoordinateAxes();
    }
}

// Функция для установки выбранного значения X и Y
function setChoiceX() {
    var radioElements = document.querySelectorAll("input[name='X']");
    var selectedRadio = Array.from(radioElements).find(element => element.checked);
    var textElement = document.getElementById("input_Y");
    var textValue = textElement.value.trim();

    textValue = textValue.replace(",", ".");
    if (selectedRadio && textElement) {
        var expected_X_values = ["-3", "-2", "-1", "0", "1", "2", "3", "4", "5"];
        if (expected_X_values.includes(selectedRadio.value)) {
            document.getElementById("input_X").value = selectedRadio.value;
        } else {
            document.getElementById("input_X").value = "0";
        }
        document.getElementById("input_Y").value = textValue;
        var canvas = document.getElementById('coordinateCanvas');
        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawCoordinateAxes();
    }
}

// Функция для проверки формы
function validateForm() {
    var xChecked = false;
    var yInput = document.getElementById("input_Y");
    var yValue = yInput.value.trim();
    yValue = yValue.replace(",", ".");

    var selectedR = parseFloat(document.getElementById("input_R").value);

    var xRadios = document.querySelectorAll("input[name='X']");
    for (var i = 0; i < xRadios.length; i++) {
        if (xRadios[i].checked) {
            xChecked = true;
            break;
        }
    }
    clearMessages();

    if (!xChecked) {
        showMessage("Please select a value for X");
        return false;
    }
    if (isNaN(yValue) || yValue < -5 || yValue > 3) {
        showMessage("Please enter a value for Y in the range from -5 to 3");
        yInput.value = "";
        return false;
    }

    if (isNaN(selectedR)) {
        showMessage("Please select a value for R");
        return false;
    }

    // Сохранение данных в локальном хранилище
    var formData = {
        xChecked: xChecked,
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
        var xRadios = document.querySelectorAll("input[name='X']");
        xRadios.forEach(function (radio) {
            radio.checked = formData.xChecked;
        });
        document.getElementById("input_Y").value = formData.yValue;
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

        var canvas = document.getElementById('coordinateCanvas');
        var ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        drawCoordinateAxes();
    }
}

// // Функция для сохранения данных таблицы в локальное хранилище
// function saveTableToLocalStorage() {
//     var tableBody = document.getElementById("result-table-body");
//     var tableData = tableBody.innerHTML;
//     localStorage.setItem("tableData", tableData);
// }

// Функция для отправки формы
// Функция для отправки формы
function submitForm(event) {
    event.preventDefault();

    if (!validateForm()) {
        return;
    }
    var scriptStartTime = performance.now();
    var form = event.target;
    var formData = new FormData(form);

    var currentTimeMoscow = new Date().toLocaleString('en-US', {
        timeZone: 'Europe/Moscow'
    });

    formData.append("LocalTime", currentTimeMoscow);

    $.ajax({
        type: "POST",
        url: '../PHP/check_point.php',
        data: formData,
        processData: false,
        contentType: false,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            if (data.error) {
                showMessage(data.error);
                return;
            }
            var scriptEndTime = performance.now();
            var scriptExecutionTimeInSeconds = ((scriptEndTime - scriptStartTime) / 1000).toFixed(4);

            var queryString = `?X=${data.X}&Y=${data.Y}&R=${data.R}&Inside=${data.Inside}&LocalTime=${currentTimeMoscow}&ScriptExecutionTime=${scriptExecutionTimeInSeconds}`;
            window.location.href = "results.html" + queryString;

            var storedData = localStorage.getItem("tableData");
            var newData = {
                X: data.X,
                Y: data.Y,
                R: data.R,
                Inside: data.Inside,
                LocalTime: currentTimeMoscow,
                ScriptExecutionTime: scriptExecutionTimeInSeconds
            };
            if (storedData) {
                var existingData = JSON.parse(storedData);
                existingData.push(newData);
                localStorage.setItem("tableData", JSON.stringify(existingData));
            } else {
                localStorage.setItem("tableData", JSON.stringify([newData]));
            }
        },
        error: function (xhr, error) {
            console.error("Error when sending data: " + error);
            console.log(xhr.responseText);
            showMessage("An error occurred while sending the data.");
        }
    });
}


document.addEventListener("DOMContentLoaded", function () {
    drawGraphOnLoad();
    restoreFormData();
    var form = document.getElementById("data-form");
    form.addEventListener("submit", function (event) {
        submitForm(event);
    });
});
