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
<c:if test="${not empty requestScope.orderSuccess}">
  <dialog id="order-success"
          aria-labelledby="order-success-title">

    <h2 id="order-success-title">
      <c:out value="${requestScope.orderSuccess}" />
    </h2>

    <form action="${pageContext.request.contextPath}/items"
          method="get">
      <button type="submit" autofocus>OK</button>
    </form>
  </dialog>

  <script>
    document.getElementById("order-success").showModal();
  </script>
</c:if>
<body>
<main>
  <h1>Kundvagn</h1>
  <c:if test="${empty requestScope.cartItems}">
    <p>Din kundvagn är tom.</p>
  </c:if>

  <p>
    <a href="${pageContext.request.contextPath}/items">
      Fortsätt handla
    </a>
  </p>

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
            <c:if test="${cartItem.canAdd}">
              <form action="${pageContext.request.contextPath}/cart" method="post">
                <input type="hidden" name="itemId" value="${cartItem.id}">
                <input type="hidden" name="returnTo" value="cart">

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

  <div class="checkout-actions">
    <form action="${pageContext.request.contextPath}/orders"
          method="post">
      <button type="submit" class="cart-button">
        Beställ
      </button>
    </form>
  </div>

  <c:if test="${not empty requestScope.orderError}">
    <p>
      <c:out value="${requestScope.orderError}" />
    </p>
  </c:if>

</main>
</body>
</html>