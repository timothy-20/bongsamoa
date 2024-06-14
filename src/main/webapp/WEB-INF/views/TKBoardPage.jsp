<%--
  Created by IntelliJ IDEA.
  User: timothy
  Date: 2024-06-14
  Time: 오후 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TKBoardPage</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/TKBoardPage.css">
</head>
<body>
<div class="container">
    <header>
        <h1>게시판</h1>
    </header>
    <main>
        <table>
            <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>0</td>
                <td>테스트용 게시글</td>
                <td>임정운</td>
                <td>24.06.14.</td>
            </tr>
            <tr>
                <td>1</td>
                <td>테스트용 게시글</td>
                <td>박승원</td>
                <td>24.06.14.</td>
            </tr>
            </tbody>
        </table>
    </main>
    <footer>

    </footer>
</div>
</body>
</html>
