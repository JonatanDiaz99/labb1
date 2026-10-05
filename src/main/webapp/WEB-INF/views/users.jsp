<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Användare – Labb 1</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/users.css">
</head>
<body>
<main>
    <h1>Användare</h1>
    <c:if test="${not empty error}">
        <p class="error"><c:out value="${error}" /></p>
    </c:if>
    <table class="users-table">
        <thead>
            <tr>
                <th>Namn</th>
                <th>Användarnamn</th>
                <th>Roll</th>
                <th>Nytt lösenord</th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <form id="user-${user.id}" method="post" action="${pageContext.request.contextPath}/users">
                            <input type="hidden" name="id" value="${user.id}">
                        </form>
                        <input form="user-${user.id}" type="text" name="name" value="<c:out value='${user.name}'/>" required>
                    </td>
                    <td>
                        <input form="user-${user.id}" type="text" name="username" value="<c:out value='${user.username}'/>" required>
                    </td>
                    <td>
                        <select form="user-${user.id}" name="role" required>
                            <c:forEach var="role" items="${roles}">
                                <option value="${role}" <c:if test="${role == user.role}">selected</c:if>>
                                    <c:out value="${role}" />
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input form="user-${user.id}" type="password" name="password" required>
                    </td>
                    <td class="user-actions">
                        <button form="user-${user.id}" type="submit" name="action" value="update">Uppdatera</button>
                        <form method="post" action="${pageContext.request.contextPath}/users">
                            <input type="hidden" name="id" value="${user.id}">
                            <button type="submit" class="delete-button" name="action" value="delete">Ta bort</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <h2>Ny användare</h2>
    <form class="user-form" method="post" action="${pageContext.request.contextPath}/users">
        <label for="name">Namn:</label>
        <input type="text" id="name" name="name" required>
        <label for="username">Användarnamn:</label>
        <input type="text" id="username" name="username" required>
        <label for="role">Roll:</label>
        <select id="role" name="role" required>
            <c:forEach var="role" items="${roles}">
                <option value="${role}"><c:out value="${role}" /></option>
            </c:forEach>
        </select>
        <label for="password">Lösenord:</label>
        <input type="password" id="password" name="password" required>
        <button type="submit" name="action" value="create">Lägg till användare</button>
    </form>
    <a href="${pageContext.request.contextPath}/items">Tillbaka</a>
</main>
</body>
</html>