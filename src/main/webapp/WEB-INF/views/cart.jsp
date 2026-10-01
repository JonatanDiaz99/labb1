<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="sv">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Kundvagn</title>

  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/style.css">
  <link rel="stylesheet"
        href="${pageContext.request.contextPath}/css/items.css">
</head>
<body>
<main>
  <h1>Kundvagn</h1>

  <c:if test="${empty requestScope.cartItems}">
    <p>Din kundvagn är tom.</p>
  </c:if>

  <c:if test="${not empty requestScope.cartItems}">
    <table class="items-table">
      <thead>
      <tr>
        <th scope="col">Namn</th>
        <th scope="col">Styckpris</th>
        <th scope="col"></th>
        <th scope="col">Antal</th>
        <th scope="col"></th>
      </tr>
      </thead>

      <tbody>
      <c:forEach var="cartItem" items="${requestScope.cartItems}">
        <tr>
          <td><c:out value="${cartItem.name}" /></td>
          <td><c:out value="${cartItem.price}" /> kr</td>

          <td>
            <form action="${pageContext.request.contextPath}/cart" method="post">
              <input type="hidden" name="itemId" value="${cartItem.id}">
              <input type="hidden" name="returnTo" value="cart">
              <button class="add-button" type="submit" name="action" value="remove">
                -
              </button>
            </form>
          </td>

          <td><c:out value="${cartItem.quantity}" /></td>

          <td>
            <form action="${pageContext.request.contextPath}/cart" method="post">
              <input type="hidden" name="itemId" value="${cartItem.id}">
              <input type="hidden" name="returnTo" value="cart">
              <button class="add-button" type="submit" name="action" value="add">
                +
              </button>
            </form>
          </td>

        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:if>

  <p>
    <a href="${pageContext.request.contextPath}/items">
      Fortsätt handla
    </a>
  </p>

</main>
</body>
</html>