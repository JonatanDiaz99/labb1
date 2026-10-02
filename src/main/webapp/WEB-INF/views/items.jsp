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
        <a href="${pageContext.request.contextPath}/cart"
           class="cart-button">
            Kundvagn
        </a>
    </div>
    <p><a href="${pageContext.request.contextPath}/logout">Logga ut</a></p>
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
                        <form action="${pageContext.request.contextPath}/cart"
                              method="post">
                            <input type="hidden" name="itemId" value="${item.id}">
                            <input type="hidden" name="returnTo" value="item">
                            <button type="submit" class="add-button" name="action" value="add">
                                +
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</main>
</body>
</html>
