<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Вход</title>
    <style>
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            background-color: #FFF5EE;
        }

        .login-container {
            text-align: center;
            background-color: #FFF5EE;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .login-container label {
            display: block;
            margin-bottom: 10px;
        }

        .login-container input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
            margin-bottom: 15px;
        }

        .login-container h1,
        .login-container h2 {
            margin-bottom: 20px;
        }

        .login-container .button-container {
            margin-top: 20px;
        }

        .login-container button {
            padding: 10px;
            margin: 0 10px;
            background-color: #87CEFA;
            color: #FFF5EE;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        .login-container button:hover {
            background-color: #87CEFA;
        }

        /* Стиль для сообщения об ошибке */
        .error-message {
            color: red;
            margin-top: 10px; /* Переместите сообщение вниз */
        }
    </style>
</head>
<body>
<div class="login-container">
    <form action="loginServlet" method="post">
        <label>
            Логин(адрес электронной почты):
            <input type="text" name="username">
        </label>
        <label>
            Пароль:
            <input type="password" name="password">
        </label>
        <div class="button-container">
            <button type="submit" name="action" value="Entrance">Вход</button>
            <button type="submit" name="action" value="Registration">Регистрация</button>
        </div>
        <% if (request.getAttribute("errorMessage") != null) { %>
        <p class="error-message"><%= request.getAttribute("errorMessage") %></p>
        <% } %>
    </form>
</div>
</body>
</html>
