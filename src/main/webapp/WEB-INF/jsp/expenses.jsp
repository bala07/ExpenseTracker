<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <h1>List of expenses</h1>
        <c:forEach var="expense" items="${expenses}">
            <p>Id => ${expense.id}</p>

            <p>Sheet id => ${expense.expenseSheetId}</p>

            <p>Payer name => ${expense.payer.name}</p>

            <p>Beneficiaries => </p>
            <br/>
            <c:forEach var="user" items="${expense.beneficiaries}">
                <p>Beneficiary name => ${user.name}</p>

                <p>Beneficiary balance => ${user.balance}</p>
            </c:forEach>
        </c:forEach>
    </body>
</html>
