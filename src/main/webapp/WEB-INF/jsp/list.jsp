<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>HELLO WORLD!</title>
  </head>
  <body>
    <h1>HELLO WORLD!</h1>
    <c:forEach var="user" items="${users}">
        <p>${user.id}</p>
        <p>${user.name}</p>
        <p>${user.balance}</p>
    </c:forEach>
  </body>
</html>
