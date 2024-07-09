<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Заявка отправлена</title>
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

        h1 {
            color: #333;
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
        }

        button:hover {
            background-color: #87CEFA;
        }
    </style>
</head>
<body>
<form action="http://localhost:8888/client" method="get">
    <h1>Заявка отправлена</h1>
    <button type="button" onclick="goBack()">Отлично!</button>
    </form>

    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
