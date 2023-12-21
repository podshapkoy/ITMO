<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="static/css/style_1lab.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="icon" href="static/img/3img.png" type="image/png">
    <title>2 lab</title>
</head>
<body onload="setChoiceR(event); setChoiceX(event)">
<div class="top-bar">
    <h1><a href="">podshapkoy</a></h1>
</div>

<div class="container">

    <h1>Khvostova Irina Leonidovna P-3224</h1>
    <h1>VARIANT 223318</h1>
    <p>
        Заполните форму, чтобы проверить попадание точек в область
    </p>
    <h1>Выберите значение R, чтобы отобразить график</h1><br>
    <div id="message-box" class="message-box"></div>
    <form id="data-form" method="GET" action="${pageContext.request.contextPath}/controller" target="_self">
        <div class="coordinate_img">
            <canvas id="coordinateCanvas" width="400" height="400"></canvas>
        </div>
        <div class="form">
            <div class="block_R">
                <h2>Change R: </h2>
                <div class="radio-buttons" id="radio-buttons_R" onclick="setChoiceR(event)">
                    <% for (double rValue : Arrays.asList(1.0, 1.5, 2.0, 2.5, 3.0)) { %>
                    <button type="button" id="R_button_<%= rValue %>" name="R"><%= rValue %>
                    </button>
                    <% } %>
                </div>
            </div>

            <div class="block_X">
                <h2>Change X: </h2>
                <div class="radio-buttons" id="radio-buttons_X" onclick="setChoiceX(event)">
                    <% for (int xValue = -3; xValue <= 5; xValue++) { %>
                    <button type="button" name="X" id="X_button_<%= xValue %>"><%= xValue %>
                    </button>
                    <% } %>
                </div>
            </div>

            <div class="block_Y" onclick="setChoiceY()">
                <h2>Change Y: </h2>
                <label for="input_Y"></label>
                <input type="text" id="input_Y" name="Y" placeholder="Value from -3 to 3" required>
            </div>

            <!--Скрытое поле-->
            <input type="hidden" id="input_R" name="R" value="">
            <input type="hidden" id="input_X" name="X" value="">
            <input type="hidden" id="input_LocalTime" name="LocalTime" value="">

            <div class="button_submit">
                <button type="submit" id="submit-button" name="submit">Проверим попадание?</button>
            </div>
        </div>

    </form>

</div>
<script src="JS/script_lab2.js"></script>
<script src="JS/chart_lab2.js"></script>
<script src="JS/click.js"></script>
</body>
</html>