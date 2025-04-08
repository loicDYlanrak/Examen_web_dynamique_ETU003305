package servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import dep.Utilisateur;
import gestionDAO.UtilisateurDAO;

public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("logout") != null) {
            HttpSession session = request.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            response.sendRedirect("login");
            return;
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Utilisateur use;
        try {
            use = UtilisateurDAO.findByUsername(username);
            if (use.getPwd().compareTo(password) == 0) {
                HttpSession session = request.getSession();
                session.setAttribute("userConnected", username);
                response.sendRedirect("dashboard");
            } else {
                request.setAttribute("error",
                        "Nom d'utilisateur ou mot de passe incorrect:" + username + ":" + password);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new ServletException("Probleme lier au login");
        }

    }
}