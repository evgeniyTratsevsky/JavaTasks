function LoginHandler() {

    var userName = document.LoginForm.login.value;
    var userPassword = document.LoginForm.password.value;

    if (userName == "" && userPassword == "") {
        alert("Поля формы не заполнены!");
    }
    else if (userName == "") {
        alert("Вы не ввели имя пользователя!");
    }
    else if (userPassword == "") {
        alert("Вы не ввели пароль!");
    }
}