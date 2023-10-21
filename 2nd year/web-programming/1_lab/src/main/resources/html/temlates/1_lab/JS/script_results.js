// Функция для извлечения данных из локального хранилища
function getStoredData() {
    var storedData = localStorage.getItem("tableData");
    return storedData ? JSON.parse(storedData) : [];
}

// Функция для добавления данных в таблицу
function addToTable(data) {
    var tableBody = document.getElementById("result-table-body");
    var newRow = document.createElement("tr");
    newRow.className = data.Inside ? "inside" : "not-inside";
    newRow.innerHTML = `
        <td>${data.X}</td>
        <td>${data.Y}</td>
        <td>${data.R}</td>
        <td>${data.Inside ? 'Попал(-а)' : 'Не попал(-а)'}</td>
        <td>${data.LocalTime}</td>
        <td>${data.ScriptExecutionTime}</td>
    `;
    tableBody.appendChild(newRow);
}
// Функция для очистки таблицы и локального хранилища
function clearTable() {
    var tableBody = document.getElementById("result-table-body");
    tableBody.innerHTML = "";
    localStorage.removeItem("tableData");
}

document.addEventListener("DOMContentLoaded", function () {
    var clearTableButton = document.getElementById("clear-table-button");
    clearTableButton.addEventListener("click", function () {
        clearTable();
    });

    // Отображаем все ранее сохраненные данные
    var storedData = getStoredData();
    for (var i = 0; i < storedData.length; i++) {
        addToTable(storedData[i]);
    }
});


