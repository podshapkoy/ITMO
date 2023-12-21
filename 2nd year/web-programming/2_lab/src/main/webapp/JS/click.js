var canvas = document.getElementById('coordinateCanvas');
var points = [];

document.addEventListener("DOMContentLoaded", function () {
    drawPoints();
    canvas.addEventListener("mousedown", function (event) {
        handleClick(event);
    });
});

function getSelectedR() {
    var selectedRButton = document.querySelector("#radio-buttons_R .selected");
    return selectedRButton ? selectedRButton.textContent : "";
}

function handleClick(event) {
    if (!getSelectedR()) {
        showMessage("Выберите значение R");
        return;
    }

    if (manualInput) {
        manualInput = false;
        return;
    }

    var selectedR = getSelectedR();
    var canvasRect = canvas.getBoundingClientRect();

    var clickX = event.clientX - canvasRect.left;
    var clickY = event.clientY - canvasRect.top;
    drawPoint(clickX, clickY);

    var clickXServer = ((event.clientX - canvasRect.left) / canvasRect.width) * (2 * selectedR) - selectedR;
    var clickYServer = (1 - (event.clientY - canvasRect.top) / canvasRect.height) * (2 * selectedR) - selectedR;

    // console.log(`Координаты: R=${selectedR}, X=${clickXServer.toFixed(2)}, Y=${clickYServer.toFixed(2)}`);

    sendPointAndFormDataToServer(clickXServer, clickYServer, selectedR);
    event.preventDefault();
}

function drawPoints() {
    var storedPoints = localStorage.getItem("points");
    points = storedPoints ? JSON.parse(storedPoints) : [];
    for (var i = 0; i < points.length; i++) {
        var point = points[i];
        ctx.beginPath();
        ctx.arc(point.x, point.y, 3, 0, 2 * Math.PI);
        ctx.fillStyle = 'rgb(255,213,0)';
        ctx.fill();
    }
}

function drawPoint(x, y) {
    points.push({ x: x, y: y });
    localStorage.setItem("points", JSON.stringify(points));
    drawPoints();
}

function sendPointAndFormDataToServer(x, y, selectedR) {
    var roundedX = x.toFixed(2);
    var roundedY = y.toFixed(2);

    if (!manualInput) {
        var storedPoints = localStorage.getItem("storedPoints");
        var points = storedPoints ? JSON.parse(storedPoints) : [];
        points.push({ x: roundedX, y: roundedY, R: selectedR });
        localStorage.setItem("storedPoints", JSON.stringify(points));
    }

    var formData = new FormData();
    formData.append("X", roundedX);
    formData.append("Y", roundedY);
    formData.append("R", selectedR);

    var scriptExecutionTime = performance.now();

    formData.append("scriptExecutionTime", scriptExecutionTime.toString());

    var updatedParams = new URLSearchParams(formData);

    fetch(`${window.location.pathname}controller?${updatedParams.toString()}`, {
        method: 'GET'
    })
        .then(response => {
            if (response.ok) {
                localStorage.removeItem("storedPoints");
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
            console.log('Время выполнения скрипта: ' + executionTime + ' сек');
        })
        .catch(error => {
            console.error('Error:', error);
        });
}

