//КАНВА ХАЙ-ПО-ВА-Я
var canvas = document.getElementById('coordinateCanvas');
var ctx = canvas.getContext('2d');
function drawGraphOnLoad() {
    drawGraph();
}
function drawGraph(){
    var centerX = canvas.width / 2;
    var centerY = canvas.height / 2;
    var arrowSize = 8;
    var axisMargin = 20;
    var canvasWidth = canvas.width;
    var canvasHeight = canvas.height;

    ctx.fillStyle = 'rgba(255,255,255,0.2)';
    ctx.fillRect(0, 0, canvasWidth, canvasHeight);

    // Ось X
    ctx.beginPath();
    ctx.moveTo(axisMargin, centerY);
    ctx.lineTo(canvas.width - axisMargin, centerY);
    ctx.strokeStyle = 'black';
    ctx.stroke();

    // Стрелочка для оси X
    ctx.beginPath();
    ctx.moveTo(canvas.width - axisMargin - arrowSize, centerY - arrowSize);
    ctx.lineTo(canvas.width - axisMargin, centerY);
    ctx.lineTo(canvas.width - axisMargin - arrowSize, centerY + arrowSize);
    ctx.fillStyle = 'black';
    ctx.fill();

    // Подпись к оси X
    ctx.fillStyle = 'black';
    ctx.font = '14px Arial';
    ctx.fillText('X', canvas.width - axisMargin + 4, centerY + 20);

    // Ось Y
    ctx.beginPath();
    ctx.moveTo(centerX, axisMargin);
    ctx.lineTo(centerX, canvas.height - axisMargin);
    ctx.strokeStyle = 'black';
    ctx.stroke();

    // Стрелочка для оси Y
    ctx.beginPath();
    ctx.moveTo(centerX - arrowSize, axisMargin + arrowSize);
    ctx.lineTo(centerX, axisMargin);
    ctx.lineTo(centerX + arrowSize, axisMargin + arrowSize);
    ctx.fillStyle = 'black';
    ctx.fill();

    // Подпись к оси Y
    ctx.fillStyle = 'black';
    ctx.font = '14px Arial';
    ctx.fillText('Y', centerX - 20, axisMargin - 4);

    // Отрисовка фигур
    var rectWidth = canvas.width;
    var rectHeight = canvas.width / 2;

    var scale = canvas.width / (2.5 * canvas.width);

    // Координаты прямоугольника
    var rectX1 = centerX - rectWidth * scale / 2;
    var rectY1 = centerY;
    var rectX2 = centerX - rectWidth * scale / 2;
    var rectY2 = centerY + rectHeight * scale;
    var rectX3 = centerX;
    var rectY3 = centerY + rectHeight * scale;
    var rectX4 = centerX;
    var rectY4 = centerY;

    ctx.beginPath();
    ctx.moveTo(rectX1, rectY1);
    ctx.lineTo(rectX2, rectY2);
    ctx.lineTo(rectX3, rectY3);
    ctx.lineTo(rectX4, rectY4);
    ctx.closePath();
    ctx.fillStyle = 'rgba(230,0,255,0.2)';
    ctx.fill();

    // Координаты треугольника
    var trianX1 = centerX;
    var trianY1 = centerY;
    var trianX2 = centerX - (rectWidth / 2) * scale;
    var trianY2 = centerY + rectWidth * scale;
    var trianX3 = centerX;
    var trianY3 = centerY - (rectWidth / 2) * scale;

    ctx.beginPath();
    ctx.moveTo(trianX1, trianY1);
    ctx.lineTo(trianX2, trianY2);
    ctx.lineTo(trianX3, trianY3);
    ctx.closePath();
    ctx.fillStyle = 'rgba(230,0,255,0.2)';
    ctx.fill();

    // Координаты вершин четверти окружности
    ctx.beginPath();
    ctx.arc(centerX, centerY, (rectWidth / 2) * scale * scale, Math.PI / 2, 0, true);
    ctx.lineTo(centerX, centerY);
    ctx.closePath();
    ctx.fillStyle = 'rgba(230,0,255,0.2)';
    ctx.fill();
    drawCoordinateAxes();
}

// Отрисовка координатных осей
function drawCoordinateAxes() {
    var centerX = canvas.width / 2;
    var centerY = canvas.height / 2;
    var arrowSize = 8;
    var axisMargin = 20;


    // Засечки и надписи на оси X
    var selectedR = parseFloat(document.getElementById("input_R").value);
    var stepX = (canvas.width - 2 * axisMargin - 5 * arrowSize) / (2 * selectedR);
    ctx.fillStyle = 'rgb(18,66,103)';

    var labelsX = [];
    for (var i = -selectedR; i <= selectedR; i += selectedR / 2) {
        labelsX.push(i);
    }

    for (i = 0; i < labelsX.length; i++) {
        var xPos = centerX + labelsX[i] * stepX;
        ctx.beginPath();
        ctx.moveTo(xPos, centerY - 5);
        ctx.lineTo(xPos, centerY + 5);
        ctx.strokeStyle = 'black';
        ctx.stroke();
        ctx.fillText(labelsX[i], xPos - 10, centerY + 20);
    }

    // Засечки и надписи на оси Y
    var stepY = (canvas.height - 2 * axisMargin - 5 * arrowSize) / (2 * selectedR);
    var labelsY = [];
    for (i = -selectedR; i <= selectedR; i += selectedR / 2) {
        labelsY.push(i);
    }
    ctx.fillStyle = 'rgb(18,66,103)';

    for (i = 0; i < labelsY.length; i++) {
        var yPos = centerY - labelsY[i] * stepY;
        ctx.beginPath();
        ctx.moveTo(centerX - 5, yPos);
        ctx.lineTo(centerX + 5, yPos);
        ctx.strokeStyle = 'black';
        ctx.stroke();
        ctx.fillText(labelsY[i], centerX + 10, yPos + 5);
    }
    // ОЧЕНЬ КРУТАЯ ТОЧКА
    var selectedX, selectedY;
    var inputXElement = document.getElementById("input_X");
    var inputYElement = document.getElementById("input_Y");

    if (inputXElement && inputYElement) {
        selectedX = parseFloat(inputXElement.value);
        selectedY = parseFloat(inputYElement.value);
    }
    var scale = canvas.width / (2.5 * selectedR);

    var xCoordinate = centerX + selectedX * scale;
    var yCoordinate = centerY - selectedY * scale;
    ctx.beginPath();
    ctx.arc(xCoordinate, yCoordinate, 3, 0, 2 * Math.PI);
    ctx.fillStyle = 'rgb(230,0,255)';
    ctx.fill();

}
