<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Регистрация</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #FFF5EE;
        }

        form {
            width: 300px;
            padding: 20px;
            border: 1px solid #FFF5EE;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #FFF5EE;
            display: flex;
            flex-direction: column;
            align-items: center; /* Центрируем содержимое по горизонтали */
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        button {
            background-color: #87CEFA;
            color: #FFF5EE;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            margin: 10px;
        }

        button:hover {
            background-color: #87CEFA;
        }
    </style>
</head>

<body>
<form action="registration" method="post">
    <label for="firstName">Имя (латиница):</label>
    <input type="text" id="firstName" name="firstName" pattern="[A-Za-z]+" title="Введите только латинские символы" required>

    <label for="lastName">Фамилия (латиница):</label>
    <input type="text" id="lastName" name="lastName" pattern="[A-Za-z]+" title="Введите только латинские символы" required>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required>

    <label for="password">Пароль (латиница и цифры)</label>
    <input type="password" id="password" name="password" pattern="[A-Za-z0-9]+" required>

    <button type="submit">Зарегистрироваться</button>
    <button type="button" onclick="goBack()">Назад</button>
</form>

<script>
    function goBack() {
        window.history.back();
    }
</script>

</body>
</html>
