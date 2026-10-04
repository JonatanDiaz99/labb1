package se.kth.labb.ui.controller;

import se.kth.labb.bo.order.OrderFacade;
import se.kth.labb.bo.cart.CartFacade;
import se.kth.labb.ui.dto.CartItemInfo;
import se.kth.labb.ui.dto.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet({"/orders",})
public class OrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession httpSession = request.getSession();
        UserInfo user = (UserInfo) httpSession.getAttribute("user");
        CartFacade cartFacade = (CartFacade) httpSession.getAttribute("cartFacade");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        int userId = user.getId();

        if (cartFacade == null || cartFacade.isEmpty()) {
            httpSession.setAttribute("orderError", "Lägg till varor för att genomföra beställning");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }
        List<CartItemInfo> itemsInCart = cartFacade.getItems();

        try {
           OrderFacade.placeOrder(userId, itemsInCart);
        } catch (RuntimeException e){
            httpSession.setAttribute("orderError", "Beställning misslyckades");
            response.sendRedirect(request.getContextPath() + "/cart");
            return;
        }

        cartFacade.clear();

        httpSession.setAttribute(
                "orderSuccess",
                "Order lyckades"
        );

        response.sendRedirect(request.getContextPath() + "/cart");
    }
}

