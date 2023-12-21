<%@ page import="org.example.ResultData" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%!
    private String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat("0.00000");
        String formatted = df.format(number).replace(',', '.');
        return formatted.replaceAll("\\.0*$", "").replaceAll("(\\.[0-9]*[1-9])0+$", "$1");
    }
%>
<!DOCTYPE html>
<html lang="ru">
<head>
    <link rel="stylesheet" type="text/css" href="static/css/style_1lab.css">
    <link rel="icon" href="static/img/3img.png" type="image/png">
    <style>
        table {
            font-weight: bold;
            color: #333;
        }
    </style>
    <title>Результаты</title>
</head>
<body>
<div class="top-bar">
    <h1><a>podshapkoy</a></h1>
</div>

<div class="container">
    <h1>Результаты проверки попадания точек в область</h1>
        <div class="button_submit">
            <button type="button">Назад</button>
        </div>
    <div class="result-table">
        <table>
            <thead>
            <tr id="result-row">
                <th id="result-X"> X</th>
                <th id="result-Y"> Y</th>
                <th id="result-R"> R</th>
                <th id="result-Outcome">Результат</th>
                <th id="result-Time">Текущее время</th>
                <th id="result-Time-script">Время выполнения скрипта, 10^-4 сек.</th>
            </tr>
            </thead>
            <tbody id="result-table-body">
            <% List<ResultData> resultTable = (List<ResultData>) request.getServletContext().getAttribute("resultTable"); %>
            <% if (resultTable != null) { %>
            <% for (ResultData result : resultTable) { %>
            <tr class="<%= result.isInside() ? "inside" : "not-inside" %>">
                <td><%= result.getX() %>
                </td>
                <td><%= result.getY() %>
                </td>
                <td><%= result.getR() %>
                </td>
                <td><%= result.isInside() ? "Попадание" : "Промах" %>
                </td>
                <td><%= result.getLocalDateTime() %>
                </td>
                <td><%= formatNumber(result.getScriptExecutionTime() / 100)%>
                </td>
            </tr>
            <% } %>
            <% } %>
            </tbody>
        </table>
    </div>
</div>
<script src="JS/script_results_lab2.js"></script>
</body>
</html>
