<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="/spring-mvc-hibernate/res/main.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>HTML Document</title>
</head>
<body>
    <p>Введите критерии поиска</p>
    <br>Имя: <input type="text" id="fn">
    <br>Фамилия: <input type="text" id="sn">
    <br>Город: <input type="text" id="city">
    <br>Цвет машины: <input type="text" id="color">
    <br><button onClick="send_data()">Поиск</button>
    <br><div id="resultTable"/>
</body>
</html>