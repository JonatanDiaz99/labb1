package se.kth.labb.ui.controller;

import se.kth.labb.bo.ItemFacade;
import se.kth.labb.bo.cart.CartFacade;
import se.kth.labb.ui.dto.ItemInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet({"/items", "/hello"})
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession httpSession = request.getSession();

        List<ItemInfo> items;

        CartFacade cartFacade = (CartFacade) httpSession.getAttribute("cartFacade");

        if(cartFacade == null) {
            cartFacade = new CartFacade();
            httpSession.setAttribute("cartFacade", cartFacade);
        }

        try {
            items = ItemFacade.getAll(cartFacade);
        } catch (RuntimeException e) {
            log("Kunde inte hämta produkter via ItemFacade", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Produkterna kunde inte hämtas just nu.");
            return;
        }

        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/items.jsp")
                .forward(request, response);
    }
}
