<%@ page import="by.Belaya.model.Request" %>
<%@ page import="java.util.List" %>
<!-- views/adminPage.jsp -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>All Requests</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #FFF5EE;
            margin: 0;
            padding: 20px;
        }

        h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #87CEFA;
            color: #FFF5EE;
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

        form {
            display: inline;
        }

        button {
            background-color: #87CEFA;
            color: #FFF5EE;
            padding: 8px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"] {
            background-color: #87CEFA;
            color: #FFF5EE;
            padding: 8px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="text"] {
            padding: 8px;
            margin-right: 5px;
        }
    </style>
</head>
<body>
<h2>Все заявки</h2>

<table>
    <thead>
    <tr>
        <th>Номер заявки</th>
        <th>Номер пользователя</th>
        <th>Статус</th>
    </tr>
    </thead>
    <tbody>
    <% for (Request req : (List<Request>) request.getAttribute("allRequests")) { %>
    <tr>
        <% if ("Ожидает".equals(req.getStatus())) { %>
        <td><%= req.getRequestId() %></td>
        <td><%= req.getClientId() %></td>
        <td><%= req.getStatus() %></td>
        <td>
            <a href="/requestDetails?requestId=<%= req.getRequestId() %>">Подробнее</a>


            <form action="/approveRequest" method="post">
                <input type="hidden" name="requestId" value="<%= req.getRequestId() %>">
                <button type="submit">Одобрить</button>
            </form>

            <form action="/denyRequest" method="post">
                <input type="hidden" name="requestId" value="<%= req.getRequestId() %>">
                <button type="submit">Отказать</button>
            </form>

            <form action="/checkAvailability" method="post">
                <input type="hidden" name="requestId" value="<%= req.getRequestId() %>">
                <button type="submit">Проверить возможность заселения</button>
            </form>

        </td>
        <% } %>
    </tr>
    <% } %>

    </tbody>
</table>
<div id="resultMessage">
    <% String resultMessage = (String) request.getAttribute("resultMessage");
        if (resultMessage != null) {
            out.println(resultMessage);
        }
    %>
</div>
<form action="http://localhost:8888/home" method="get">
    <button type="button" onclick="goBack()">Назад</button>
    </form>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
