<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Settlements</title>
    </head>
    <body>
        <c:forEach var="settlement" items="${settlements}">
            <p>${settlement.debtorName} => ${settlement.amount} => ${settlement.creditorName}</p>
        </c:forEach>
    </body>
</html>