package se.kth.labb.ui.controller;

import se.kth.labb.bo.UserFacade;
import se.kth.labb.ui.dto.UserInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private static final List<String> ROLES = Arrays.asList("CUSTOMER", "ADMIN", "WAREHOUSE");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdmin(request, response)) {
            return;
        }
        showUsers(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdmin(request, response)) {
            return;
        }

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        try {
            if ("create".equals(action)) {
                UserFacade.createUser(
                        required(request, "name"),
                        required(request, "username"),
                        required(request, "password"),
                        role(request));
            } else if ("update".equals(action)) {
                UserInfo updated = UserFacade.updateUser(
                        id(request),
                        required(request, "name"),
                        required(request, "username"),
                        required(request, "password"),
                        role(request));
                if (updated == null) {
                    throw new IllegalArgumentException("Användaren finns inte.");
                }
            } else if ("delete".equals(action)) {
                UserInfo deleted = UserFacade.deleteUser(id(request));
                if (deleted == null) {
                    throw new IllegalArgumentException("Användaren finns inte.");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Okänd handling.");
                return;
            }
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", e.getMessage());
            showUsers(request, response);
            return;
        }

        response.sendRedirect(request.getContextPath() + "/users");
    }

    private void showUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("users", UserFacade.getAllUsers());
        request.setAttribute("roles", ROLES);
        request.getRequestDispatcher("/WEB-INF/views/users.jsp").forward(request, response);
    }

    private String required(HttpServletRequest request, String field) {
        String value = request.getParameter(field);
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("Alla fält måste fyllas i.");
        }
        return value.trim();
    }

    private String role(HttpServletRequest request) {
        String role = required(request, "role");
        if (!ROLES.contains(role)) {
            throw new IllegalArgumentException("Ogiltig roll.");
        }
        return role;
    }

    private int id(HttpServletRequest request) {
        try {
            return Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Ogiltigt användar-id.");
        }
    }

    private boolean isAdmin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);
        UserInfo user = session == null ? null : (UserInfo) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        if (!"ADMIN".equals(user.getRole())) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return false;
        }
        return true;
    }
}
