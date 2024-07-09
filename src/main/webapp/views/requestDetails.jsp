<%@ page import="by.Belaya.model.Request" %>
<!DOCTYPE html>
<html lang="en">
<!-- requestDetails.jsp -->
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Детали запроса</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFF5EE;
            margin: 0;
            padding: 20px;
        }

        h1 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #FFF5EE;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #87CEFA;
            color: #FFF5EE;
        }

        p {
            color: red;
        }

        a {
            display: inline-block;
            padding: 10px;
            margin-top: 20px;
            background-color: #87CEFA;
            color: #FFF5EE;
            text-decoration: none;
            border-radius: 4px;
        }

        a:hover {
            background-color: #87CEFA;
        }
    </style>
</head>
<body>
<h1>Детали запроса</h1>

<% if (request.getAttribute("requestDetails") != null) {
    Request requestDetails = (Request) request.getAttribute("requestDetails"); %>

<table>
    <thead>
    <tr>
        <th>Идентификатор запроса</th>
        <th>Идентификатор клиента</th>
        <th>Дата заселения</th>
        <th>Тип комнаты</th>
        <th>Количество ночей</th>
        <th>Количество людей</th>
        <th>Статус</th>
        <!-- Добавьте другие поля запроса, которые вы хотите отобразить -->
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%= requestDetails.getRequestId() %></td>
        <td><%= requestDetails.getClientId() %></td>
        <td><%= requestDetails.getArrivalDate() %></td>
        <td><%= requestDetails.getType() %></td>
        <td><%= requestDetails.getNight() %></td>
        <td><%= requestDetails.getManCounter() %></td>
        <td><%= requestDetails.getStatus() %></td>
        <!-- Добавьте другие поля запроса, которые вы хотите отобразить -->
    </tr>
    </tbody>
</table>

<% } else { %>
<p>Ошибка: Не удалось загрузить детали запроса.</p>
<% } %>

<a href="/admin">Назад к списку запросов</a>
</body>
</html>
