function goBack() {
    try {
        window.history.back();
    } catch (error) {
        console.error('Error navigating back:', error);
    }
}

document.addEventListener('DOMContentLoaded', function () {
    var backButton = document.querySelector('.button_submit button');
    backButton.addEventListener('click', goBack);
});