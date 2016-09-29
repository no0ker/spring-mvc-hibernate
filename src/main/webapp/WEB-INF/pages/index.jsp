<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script src="/res/main.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>HTML Document</title>
</head>
<body>
<p>
    <b>
        Этот текст будет полужирным,
        <i>а этот - ещё и курсивным</i>
        <%--${attr}--%>
        <button id="3" onClick="send_data()">B3</button>
    </b>
</p>

<div id="resultTable">

</div>

</body>
</html>