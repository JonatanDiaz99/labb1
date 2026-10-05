<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Webshop – Labb 1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/items.css">
</head>
<body>
<main>
    <div class="items-header">
        <h1>Webshop</h1>
        <div class="header-actions">
            <c:if test="${sessionScope.user.role == 'ADMIN'}">
                <a href="${pageContext.request.contextPath}/users"
                   class="cart-button">
                    Användare
                </a>
            </c:if>
            <a href="${pageContext.request.contextPath}/cart"
               class="cart-button">
                Kundvagn
            </a>
        </div>
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
                    <td>
                        <c:choose>
                            <c:when test="${not item.canAdd}">
                                -
                            </c:when>
                            <c:otherwise>
                                <c:out value="${item.quantity}" />
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:if test="${item.canAdd}">
                            <form action="${pageContext.request.contextPath}/cart" method="post">
                                <input type="hidden" name="itemId" value="${item.id}">
                                <input type="hidden" name="returnTo" value="item">

                                <button type="submit"
                                        class="add-button"
                                        name="action"
                                        value="add">
                                    +
                                </button>
                            </form>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</main>
</body>
</html>
