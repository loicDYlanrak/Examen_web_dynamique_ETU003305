package dep;

import interfac.BaseObject;
import gestionDAO.LigneCreditDAO;

import java.sql.SQLException;

public class LigneCredit extends BaseObject {
    private int id;
    private String libelle;
    private double montant;

    public LigneCredit(String libelle, double montant) {
        super();
        this.libelle = libelle;
        this.montant = montant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    @Override
    public void save() throws Exception {
        try {
            LigneCreditDAO.save(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'enregistrement de la ligne de credit : " + e.getMessage());
        }
    }

    @Override
    public void update() throws Exception {
        try {
            LigneCreditDAO.update(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise a jour de la ligne de credit : " + e.getMessage());
        }
    }

    @Override
    public void delete() throws Exception {
        try {
            LigneCreditDAO.delete(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la ligne de credit : " + e.getMessage());
        }
    }
}