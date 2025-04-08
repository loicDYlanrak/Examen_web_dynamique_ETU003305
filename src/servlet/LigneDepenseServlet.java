package servlet;

import dep.LigneDepense;
import dep.LigneCredit;
import gestionDAO.LigneCreditDAO;
import gestionDAO.LigneDepenseDAO;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LigneDepenseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userConnected") == null) {
            req.setAttribute("error", "Vous devez vous connecter pour acceder a cette page.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, res);
            return;
        }

        int idCredit = Integer.parseInt(req.getParameter("idCredit"));
        double montant = Double.parseDouble(req.getParameter("montant"));
        Date dateDepense = new Date(); 
        LigneDepense ligneDepense = new LigneDepense(idCredit, montant, dateDepense);

        String message;
        try {
            LigneCredit lo = LigneCreditDAO.findById(idCredit);
            List<LigneDepense> list = LigneDepenseDAO.findAll();
            double total =montant;
            for (LigneDepense ligneDepense2 : list) {
                if (ligneDepense2.getIdCredit()==idCredit) {
                    total =total+ ligneDepense2.getMontant();
                }
            }

            double reste = lo.getMontant() - total;
            if (reste <= 0) {
                message = "Erreur lors de l'enregistrement de la ligne de depense : credit insuffisant: credit actuelle:"+lo.getMontant()+" , total depense:"+total;
                // throw new ServletException("Erreur lors de l'enregistrement de la ligne de depense : credit insuffisant: credit actuelle:"+lo.getMontant()+" , total depense:"+total);
            } else {
                ligneDepense.save();
                message =" La ligne de depense a ete enregistree avec succes !";
            }
            System.out.println(message);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de l'enregistrement de la ligne de depense : " + e.getMessage());
        }
        req.setAttribute("message", message);
        // res.sendRedirect("dashboard?message="+message);
        RequestDispatcher dispatcher = req.getRequestDispatcher("dashboard");
        dispatcher.forward(req, res);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("userConnected") == null) {
            req.setAttribute("error", "Vous devez vous connecter pour pouvoir ajouter une ligne de depense");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/login.jsp");
            dispatcher.forward(req, res);
            return;
        }
        
        try {
            List<LigneCredit> lignesCredit = LigneCreditDAO.findAll();
            req.setAttribute("lignesCredit", lignesCredit);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/formLigneDepense.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            throw new ServletException(e.getMessage());
        }
    }
}