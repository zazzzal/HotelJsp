<%@ page import="by.Belaya.model.Request" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="en">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Выбор комнаты</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
            margin: 0;
            flex-direction: column;
            background-color: #FFF5EE; /* Заменен цвет фона на светло-желтый */
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            margin-bottom: 20px; /* Добавленный отступ между таблицей и следующим элементом */
        }

        form {
            width: 300px;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-bottom: 20px;
            background-color: #FFF5EE; /* Заменен цвет фона на светло-желтый */
        }

        label {
            display: block;
            margin-bottom: 10px;
        }

        input, select, button {
            width: 100%;
            padding: 8px;
            margin-bottom: 15px;
            box-sizing: border-box;
        }

        button {
            background-color: #87CEFA;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #87CEFA;
            color: white;
        }
    </style>
</head>
<body>
<form action="client" method="post">
    <label for="people">Количество людей:</label>
    <input type="number" id="people" name="people" min="1" required>

    <label for="checkInDate">Дата заселения:</label>
    <input type="date" id="checkInDate" name="checkInDate" required>

    <label for="nights">Количество ночей:</label>
    <input type="number" id="nights" name="nights" min="1" required>

    <label for="apartmentType">Выбор апартаментов:</label>
    <select id="apartmentType" name="apartmentType" required>
        <option value="standart">Стандарт</option>
        <option value="family">Семейный</option>
        <option value="vip">VIP</option>
    </select>

    <button type="submit">Забронировать</button>
</form>

<table>
    <thead>
    <tr>
        <th>Номер комнаты</th>
        <th>Заезд</th>
        <th>Отъезд</th>
        <th>Итоговая сумма</th>
    </tr>
    </thead>
    <tbody>
    <%
        int clientId = (int)session.getAttribute("clientId");
        List<Request> allRequests = (List<Request>) request.getAttribute("allAcceptedRequests");
        if (allRequests != null && !allRequests.isEmpty()) {
            for (Request requestDetails : allRequests) {
                if (requestDetails.getClientId() == clientId) { %>
    <tr>
        <td><%= requestDetails.getRoomId() %></td>
        <td><%= requestDetails.getArrivalDate() %></td>
        <td><%= requestDetails.getEndDate() %></td>
        <td><%= requestDetails.getFinalPrice() %></td>
    </tr>
    <% }
    }
    } else { %>
    <tr>
        <td colspan="4">Нет доступных запросов</td>
    </tr>
    <% } %>

    </tbody>
</table>
<table>
    <thead>

    <tr>
        <th>Планируемая дата заезда</th>
        <th>статус</th>
    </tr>
    </thead>

    <tbody>
    <%
        List<Request> allBadRequests = (List<Request>) request.getAttribute("allBadRequests");
        if (allBadRequests != null && !allBadRequests.isEmpty()) {
            for (Request requestDetails : allBadRequests) {
                if (requestDetails.getClientId() == clientId && "Отказано".equals(requestDetails.getStatus())){%>
    <tr>
        <td><%= requestDetails.getArrivalDate() %></td>
        <td><%= requestDetails.getStatus() %></td>
    </tr>
    <%}
    }
    } else { %>
    <tr>
        <td colspan="2">Нет доступных запросов</td>
    </tr>
    <% } %>
    </tbody>
</table>

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
