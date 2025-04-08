package dep;

import interfac.BaseObject;
import gestionDAO.UtilisateurDAO;

import java.sql.SQLException;

public class Utilisateur extends BaseObject {
    private int id;
    private String nom;
    private String pwd;

    public Utilisateur(String nom, String pwd) {
        super();
        this.nom = nom;
        this.pwd = pwd;
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public void save() throws Exception {
        try {
            UtilisateurDAO.save(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'enregistrement de l'utilisateur : " + e.getMessage());
        }
    }

    @Override
    public void update() throws Exception {
        try {
            UtilisateurDAO.update(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise a jour de l'utilisateur : " + e.getMessage());
        }
    }

    @Override
    public void delete() throws Exception {
        try {
            UtilisateurDAO.delete(this);
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        }
    }
}