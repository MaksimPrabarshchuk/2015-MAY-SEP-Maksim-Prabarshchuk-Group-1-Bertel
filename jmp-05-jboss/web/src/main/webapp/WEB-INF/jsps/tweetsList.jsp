<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
    <form action="addTweet" method="post">
        <input type="text" name="text"/>
        <input type="submit" value="tweet">
    </form>
    <table>
        <c:forEach items="${tweets}" var="item">
            <tr>
                <td>${item.text}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
