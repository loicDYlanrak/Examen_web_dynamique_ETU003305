package service;

import gestionDAO.LigneCreditDAO;
import gestionDAO.LigneDepenseDAO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.UtiDB;
import dep.LigneCredit;
import dep.LigneDepense;

public class LigneCreditService {

    public static class CreditDepenseSummary {
        private int id;
        private String libelle;
        private double montantCredit;
        private double sommeDepenses;
        private double reste;

        public CreditDepenseSummary(int id, String libelle, double montantCredit, double sommeDepenses) {
            this.id = id;
            this.libelle = libelle;
            this.montantCredit = montantCredit;
            this.sommeDepenses = sommeDepenses;
            this.reste = montantCredit - sommeDepenses;
        }

        public int getId() {
            return id;
        }

        public String getLibelle() {
            return libelle;
        }

        public double getMontantCredit() {
            return montantCredit;
        }

        public double getSommeDepenses() {
            return sommeDepenses;
        }

        public double getReste() {
            return reste;
        }
    }

    public static List<CreditDepenseSummary> getCreditDepenseSummary() throws Exception {
        List<LigneCredit> lignesCredit = null;
        List<LigneDepense> lignesDepense = null;
        Connection conn = null;
        try {
            conn = UtiDB.getInstance();
            lignesCredit = LigneCreditDAO.findAll(conn);
            lignesDepense = LigneDepenseDAO.findAll(conn);
        } catch (Exception e) {
            throw new Exception("Erreur lors du findAll de ligne credit et de ligne depense");
        } finally {
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
        List<CreditDepenseSummary> summaryList = new ArrayList<>();
        for (LigneCredit credit : lignesCredit) {
            double sommeDepenses = 0;
            for (LigneDepense depense : lignesDepense) {
                if (depense.getIdCredit() == credit.getId()) {
                    sommeDepenses += depense.getMontant();
                }
            }
            summaryList.add(new CreditDepenseSummary(
                    credit.getId(),
                    credit.getLibelle(),
                    credit.getMontant(),
                    sommeDepenses));
        }
        return summaryList;
    }
}