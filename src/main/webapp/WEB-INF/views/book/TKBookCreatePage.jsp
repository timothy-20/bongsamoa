<%--
  Created by IntelliJ IDEA.
  User: timothy
  Date: 2024-06-12
  Time: 오후 6:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>TKBookCreatePage</title>
</head>
<body>
<header>
    <h1>책 생성하기</h1>
    <span></span>
</header>
<main>
    <form id="book-create-form" method="POST">
        <div>
            <label>제목: <input id="book-title" type="text"></label>
        </div>
        <div>
            <label>카테고리: <input id="book-category" type="text"></label>
        </div>
        <div>
            <label>가격: <input id="book-price" type="text"></label>
        </div>
    </form>
    <button type="submit" form="book-create-form">저장</button>
</main>
<footer>

</footer>
</body>
</html>
