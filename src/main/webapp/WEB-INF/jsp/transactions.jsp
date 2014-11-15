<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title></title>
    </head>
    <body>
        <h1>List of transactions</h1>
        <c:forEach var="transaction" items="${transactions}">
            <p>Id => ${transaction.id}</p>

            <p>Sheet id => ${transaction.expenseSheetId}</p>

            <p>Payer name => ${transaction.payer.name}</p>

            <p>Beneficiaries => </p>
            <br/>
            <c:forEach var="user" items="${transaction.beneficiaries}">
                <p>Beneficiary name => ${user.name}</p>

                <p>Beneficiary balance => ${user.balance}</p>
            </c:forEach>
        </c:forEach>
    </body>
</html>
