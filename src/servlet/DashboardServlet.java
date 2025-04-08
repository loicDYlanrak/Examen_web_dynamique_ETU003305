package servlet;

import service.LigneCreditService;
import service.LigneCreditService.CreditDepenseSummary;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DashboardServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            List<CreditDepenseSummary> summaryList = LigneCreditService.getCreditDepenseSummary();
            req.setAttribute("summaryList", summaryList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la recuperation des donnees du tableau : " + e.getMessage());
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            List<CreditDepenseSummary> summaryList = LigneCreditService.getCreditDepenseSummary();
            req.setAttribute("summaryList", summaryList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard.jsp");
            dispatcher.forward(req, res);
        } catch (Exception e) {
            throw new ServletException("Erreur lors de la recuperation des donnees du tableau : " + e.getMessage());
        }
    }
}