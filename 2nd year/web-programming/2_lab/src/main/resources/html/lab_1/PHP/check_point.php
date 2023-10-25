<?php
session_start();

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $response = array();

    $R = floatval($_POST["R"]);
    $X = $_POST["X"];
    $Y = str_replace(',', '.', $_POST["Y"]);

    if (is_numeric($X) && ($X >= -3 && $X <= 5)) {
        $X = floatval($X);
    } else {
        $response["error"] = "Некорректное значение X.";
    }

    if (($R >= 1 && $R <= 5) && ($X >= -3 && $X <= 5) && ($Y >= -5 && $Y <= 3)) {
        $isInside = false;

        if (
            ($X >= 0) && ($Y >= 0) &&
            ($X <= $R) && ($Y <= ($R / 2))
        ) {
            $isInside = true;
        } elseif (
            ($X >= 0) && ($Y <= 0) && ($X <= $R / 2) && ($Y <= $R)
        ) {
            $isInside = true;
        } elseif (
            ($X <= 0) && ($Y <= 0) && (pow($X, 2) + pow($Y, 2) <= pow($R, 2))
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

