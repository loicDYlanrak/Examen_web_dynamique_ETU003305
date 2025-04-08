package dep;

import interfac.BaseObject;
import gestionDAO.LigneDepenseDAO;

import java.sql.SQLException;
import java.util.Date;

public class LigneDepense extends BaseObject {
    private int id;
    private int idCredit;
    private double montant;
    private Date dateDepense;

    public LigneDepense(int idCredit, double montant, Date dateDepense) {
        super();
        this.idCredit = idCredit;
        this.montant = montant;
        this.dateDepense = dateDepense;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCredit() {
        return idCredit;
    }

    public void setIdCredit(int idCredit) {
        this.idCredit = idCredit;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Date getDateDepense() {
        return dateDepense;
    }

    public void setDateDepense(Date dateDepense) {
        this.dateDepense = dateDepense;
    }

    @Override
    public void save() throws Exception {
        try {
            LigneDepenseDAO.save(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'enregistrement de la ligne de depense : " + e.getMessage());
        }
    }

    @Override
    public void update() throws Exception {
        try {
            LigneDepenseDAO.update(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise a jour de la ligne de depense : " + e.getMessage());
        }
    }

    @Override
    public void delete() throws Exception {
        try {
            LigneDepenseDAO.delete(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la ligne de depense : " + e.getMessage());
        }
    }
}