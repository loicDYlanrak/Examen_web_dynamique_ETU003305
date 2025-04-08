package servlet;

import dep.LigneCredit;
import gestionDAO.LigneCreditDAO;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LigneCreditServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userConnected") == null) {
            req.setAttribute("error", "Vous devez vous connecter pour acceder a cette page.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, res);
            return;
        }

        String libelle = req.getParameter("libelle");
        double montant = Double.parseDouble(req.getParameter("montant"));
        LigneCredit ligneCredit = new LigneCredit(libelle, montant);

        String message;
        try {
            ligneCredit.save();
            message = "La ligne de credit '" + libelle + "' a ete enregistree avec succes !";
            System.out.println(message);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'enregistrement de la ligne de credit : " + e.getMessage());
        }
        req.setAttribute("message", message);
        res.sendRedirect("dashboard");
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userConnected") == null) {
            req.setAttribute("error", "Vous devez vous connecter pour pouvoir ajouter une ligne de credit");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, res);
            return;
        }
        
        try {
            List<LigneCredit> lignesCredit = LigneCreditDAO.findAll();
            req.setAttribute("lignesCredit", lignesCredit);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/formLigneCredit.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}