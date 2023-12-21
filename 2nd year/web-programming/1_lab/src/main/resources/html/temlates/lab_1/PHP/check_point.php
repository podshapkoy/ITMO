<?php
session_start();
require __DIR__ . '../../../vendor/autoload.php';

use Google\Client;
use Google\Service\Sheets;

$client = new Client();
$client->setApplicationName('Google Sheets and PHP');
$client->setScopes([Sheets::SPREADSHEETS]);
$client->setAuthConfig('../../../test1.json');
$client->setAccessType('offline');

// Создание нового сервиса для работы с таблицами
$service = new Sheets($client);

$spreadsheetId = '1t_CG912q4sU99v-VW8Tkhqj4M0uUfDwvk-LEt_0Bzok';

$range = 'Sheet1!N17';
$response = $service->spreadsheets_values->get($spreadsheetId, $range);
$values = $response->getValues();

if (empty($values)) {
    die(json_encode(["error" => "There is no data in the specified Google Table cell."]));
}

$points = $values[0][0];

if ($points === null || !in_array($points, [1, 2])) {
    die(json_encode(["error" => "Invalid score value in the specified Google Table cell."]));
}

if ($_SERVER["REQUEST_METHOD"] === "POST") {
    $response = array();

    $R = floatval($_POST["R"]);
    $X = $_POST["X"];
    $Y = str_replace(',', '.', $_POST["Y"]);

    if (is_numeric($X) && ($X >= -3 && $X <= 5)) {
        $X = floatval($X);
    } else {
        $response["error"] = "Invalid value of X.";
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
        $response["error"] = "Incorrect values. Please check the input data.";
    }

    header('Content-Type: application/json');
    echo json_encode($response, JSON_UNESCAPED_UNICODE | JSON_UNESCAPED_SLASHES);

} else {
    http_response_code(400);
    echo json_encode(["error" => "Invalid request."]);
}

