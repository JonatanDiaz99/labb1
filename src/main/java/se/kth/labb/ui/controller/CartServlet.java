package se.kth.labb.ui.controller;

import se.kth.labb.bo.cart.CartFacade;
import se.kth.labb.ui.dto.CartItemInfo;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/cart"})
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int itemId;
        try {
            itemId = Integer.parseInt(request.getParameter("itemId"));
        } catch (NumberFormatException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Fel format på itemId");
            return;
        }

        HttpSession httpSession = request.getSession();

        CartFacade cartFacade = (CartFacade) httpSession.getAttribute("cartFacade");

        if (cartFacade == null) {
            cartFacade = new CartFacade();
            httpSession.setAttribute("cartFacade", cartFacade);
        }

        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    "Handling saknas."
            );
            return;
        }

        try {
            switch (action) {
                case "add":
                    cartFacade.addItem(itemId);
                    break;
                case "remove":
                    cartFacade.removeItem(itemId);
                    break;
                default:
                    response.sendError(
                            HttpServletResponse.SC_BAD_REQUEST,
                            "Okänd handling."
                    );
                    return;
            }
        } catch (IllegalArgumentException e) {
            response.sendError(
                    HttpServletResponse.SC_BAD_REQUEST,
                    e.getMessage()
            );
            return;
        }

        String returnTo = request.getParameter("returnTo");

        if("cart".equals(returnTo)){
            response.sendRedirect(request.getContextPath() + "/cart");
        } else {
            response.sendRedirect(request.getContextPath() + "/items");
        }

    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();

        CartFacade cartFacade = (CartFacade) httpSession.getAttribute("cartFacade");

        List<CartItemInfo> cartItems = new ArrayList<>();

        if (cartFacade != null) {
            cartItems = cartFacade.getItems();
        }

        request.setAttribute("cartItems", cartItems);

        request.getRequestDispatcher("/WEB-INF/views/cart.jsp")
                .forward(request, response);
    }
}
