<%--
  Created by IntelliJ IDEA.
  User: timothy
  Date: 2024-06-12
  Time: 오후 4:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" />
</head>
<body>
<div class="container">
    <h2>Panel Header</h2>
    <div>
        <div>Spring MVC 01</div>
        <div>
            <table>
                <tr>
                    <td>index</td>
                    <td>title</td>
                    <td>content</td>
                    <td>writer</td>
                    <td>date</td>
                </tr>
                <c:forEach var="element" items="${boardList}">
                    <tr>
                        <td>element.getIndex()</td>
                        <td>element.getTitle()</td>
                        <td>element.getContent()</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div>
            SP 1
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
