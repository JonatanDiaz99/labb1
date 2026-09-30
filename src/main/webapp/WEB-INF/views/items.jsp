<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Produkter – Labb 1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<main>
    <h1>Produkter</h1>
    <c:if test="${empty requestScope.items}">
        <p>Det finns inga produkter att visa.</p>
    </c:if>

    <ul>
        <c:forEach var="item" items="${requestScope.items}">
            <li>
                <c:out value="${item.name}" />
                – <c:out value="${item.price}" /> kr
                (Lagersaldo: <c:out value="${item.quantity}" />)
            </li>
        </c:forEach>
    </ul>
</main>
</body>
</html>
