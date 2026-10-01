<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Produkter – Labb 1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/items.css">
</head>
<body>
<main>
    <div class="items-header">
        <h1>Produkter</h1>
        <button type="button" class="cart-button">Kundvagn</button>
    </div>
    <c:if test="${not empty requestScope.items}">
        <table class="items-table">
            <thead>
            <tr>
                <th scope="col">Namn</th>
                <th scope="col">Pris</th>
                <th scope="col">Antal</th>
                <th scope="col"></th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="item" items="${requestScope.items}">
                <tr>
                    <td><c:out value="${item.name}" /></td>
                    <td><c:out value="${item.price}" /> kr</td>
                    <td><c:out value="${item.quantity}" /></td>
                    <td>
                        <button type="button" class="add-button">
                            +
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</main>
</body>
</html>
