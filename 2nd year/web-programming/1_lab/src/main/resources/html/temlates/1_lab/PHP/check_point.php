<?php
session_start();

function isBetween($value, $min, $max) {
    return is_numeric($value) && $value >= $min && $value <= $max;
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $response = array();

    $R = floatval($_POST["R"]);
    $X = $_POST["X"];
    $Y = str_replace(',', '.', $_POST["Y"]);

    if (is_numeric($X) && isBetween(floatval($X), -3, 5)) {
        $X = floatval($X);
    } else {
        $response["error"] = "Некорректное значение X.";
    }

    if (isBetween($R, 1, 5) && isBetween($X, -3, 5) && isBetween($Y, -5, 3)) {
        $isInside = false;

        if (
            ($X >= 0) && ($Y >= 0) &&
            ($X <= $R) && ($Y <= ($R / 2))
        ) {
            $isInside = true;
        } elseif (
            ($X <= 0) && ($Y >= 0) &&
            ($X * $X + $Y * $Y) <= ($R * $R)
        ) {
            $isInside = true;
        } elseif (
            ($X <= 0) && ($Y <= 0) &&
            ($Y >= (-$X - $R / 2))
        ) {
            $isInside = true;
        }

        $response = array(
            "X" => $X,
            "Y" => $Y,
            "R" => $R,
            "Inside" => $isInside
        );
    } else {
        $response["error"] = "Некорректные значения. Пожалуйста, проверьте входные данные.";
    }

    header('Content-Type: application/json');
    echo json_encode($response);
} else {
    http_response_code(400);
    echo "Invalid request.";
}

