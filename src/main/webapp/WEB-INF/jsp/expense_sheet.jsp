<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Expense Sheet - ${expenseSheet.id}</title>
    </head>
    <body>
        <h1> Expense sheet </h1>
        <p>USERS</p>
        <c:forEach var="user" items="${expenseSheet.users}">
            <p> Id => ${user.id}</p>
            <p> Name => ${user.name}</p>
        </c:forEach>
        <p>EXPENSES</p>
        <c:forEach var="expense" items="${expenseSheet.expenses}">
            <p> Id => ${expense.id} </p>
            <p> Payer name => ${expense.payer.name} </p>
        </c:forEach>
    </body>
</html>