function editBtnHandler() {
    var check = document.addEditForm.radio.value;
    if (check == "") {
        alert("Не выбран контакт для редактирования!");
    }
}