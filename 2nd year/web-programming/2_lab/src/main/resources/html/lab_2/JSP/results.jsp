<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="../../static/css/style_1lab.css">
    <link rel="icon" href="../../static/css/img/3img.png" type="image/png">
    <title>Результаты</title>
</head>
<body>
<div class="top-bar">
    <h1><a href="lab_2.jsp">podshapkoy</a></h1>
</div>

<div class="container">
    <h1>Результаты проверки попадания точек в область</h1>
    <div class="button_submit">
        <button id="clear-table-button" type="button">Очистить таблицу</button>
    </div>
    <div class="result-table">
        <table>
            <thead>
            <tr id="result-row">
                <td id="result-X"> X </td>
                <td id="result-Y"> Y </td>
                <td id="result-R"> R </td>
                <td id="result-Outcome">Результат</td>
                <td id="result-Time">Текущее время</td>
                <td id="result-Time-script">Время выполнения скрипта, сек.</td>
            </tr>
            </thead>
            <tbody id="result-table-body">
            </tbody>
        </table>
    </div>
</div>
<script src="../JS/script_results.js"></script>
</body>
</html>