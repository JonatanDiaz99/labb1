package se.kth.labb.ui.controller;

import se.kth.labb.bo.ItemFacade;
import se.kth.labb.ui.dto.ItemInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/** Controller som hämtar produktdata via fasaden och lämnar den till JSP-vyn. */
@WebServlet({"/items", "/hello"})
public class ItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Listan tillhör det här anropet och delas inte mellan besökare.
        List<ItemInfo> items;
        try {
            items = ItemFacade.getAll();
        } catch (RuntimeException e) {
            log("Kunde inte hämta produkter via ItemFacade", e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                    "Produkterna kunde inte hämtas just nu.");
            return;
        }

        // JSP-sidan kan läsa listan från samma request.
        request.setAttribute("items", items);
        request.getRequestDispatcher("/WEB-INF/views/items.jsp")
                .forward(request, response);
    }
}
