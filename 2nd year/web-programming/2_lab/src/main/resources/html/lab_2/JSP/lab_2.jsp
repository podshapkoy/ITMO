<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="../../static/css/style_1lab.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="icon" href="../../static/img/3img.png" type="image/png">
    <title>2 lab</title>
</head>
<body onload="setChoiceR();setChoiceX()">
<div class="top-bar">
    <h1><a href="../../../../main.html">podshapkoy</a></h1>
</div>

<div class="container">

    <h1>Хвостова Ирина Леонидовна P-3224</h1>
    <h1>ВАРИАНТ 3412</h1>
    <p>
        Введите необходимые данные для проверки попадания точки в область

    </p>
    <h1>Выберите значение R, чтобы появился график с заданным радиусом</h1><br>
    <div id="message-box" class="message-box"></div>
    <div class="container-form">
        <div class="coordinate_img">
            <canvas id="coordinateCanvas" width="400" height="400"></canvas>
        </div>
        <div class="form">
            <form id="data-form" method="POST" action="results.jsp" target="_self">
                <div class="block_R">
                    <h2>Изменение R: </h2>
                    <div class="radio-buttons" onclick="setChoiceR()">
                        <button type="button" id="button1" name="R">1</button>
                        <button type="button" id="button2" name="R">2</button>
                        <button type="button" id="button3" name="R">3</button>
                        <button type="button" id="button4" name="R">4</button>
                        <button type="button" id="button5" name="R">5</button>
                    </div>
                </div>
                <div class="block_X" onclick="setChoiceX()">
                    <h2>Изменение X: </h2>
                    <div class="radio-buttons" id="input_X">
                        <label>
                            <input type="radio" id="radio1" name="X" value="-3"> -3
                        </label>
                        <label>
                            <input type="radio" id="radio2" name="X" value="-2"> -2
                        </label>
                        <label>
                            <input type="radio" id="radio3" name="X" value="-1"> -1
                        </label>
                        <label>
                            <input type="radio" id="radio4" name="X" value="0"> 0
                        </label>
                        <label>
                            <input type="radio" id="radio5" name="X" value="1"> 1
                        </label>
                        <label>
                            <input type="radio" id="radio6" name="X" value="2"> 2
                        </label>
                        <label>
                            <input type="radio" id="radio7" name="X" value="3"> 3
                        </label>
                        <label>
                            <input type="radio" id="radio8" name="X" value="4"> 4
                        </label>
                        <label>
                            <input type="radio" id="radio9" name="X" value="5"> 5
                        </label>
                    </div>
                </div>
                <div class="block_Y" onclick="setChoiceX()">
                    <h2>Изменение Y: </h2>
                    <label for="input_Y"></label><input type="text" id="input_Y" name="Y"
                                                        placeholder="Значение от -5 до 3" required>
                </div>

                <!--Скрытое поле-->
                <input type="hidden" id="input_R" name="R" value="">
                <input type="hidden" id="input_LocalTime" name="LocalTime" value="">

                <div class="button_submit">
                    <button type="submit" name="submit">Проверим попадание?</button>
                </div>

            </form>
        </div>
    </div>

</div>
<script src="../JS/script.js"></script>
<script src="../JS/chart.js"></script>
</body>
</html>