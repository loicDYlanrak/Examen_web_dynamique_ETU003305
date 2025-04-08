package gestionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.UtiDB;
import dep.LigneCredit;
import interfac.DAO;

public class LigneCreditDAO extends DAO {
    public static void save(Object obj) throws Exception {
        if (!(obj instanceof LigneCredit)) {
            throw new IllegalArgumentException("L'objet doit être de type LigneCredit");
        }
        LigneCredit ligneCredit = (LigneCredit) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "INSERT INTO lignes_credit (libelle, montant) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, ligneCredit.getLibelle());
            stmt.setDouble(2, ligneCredit.getMontant());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'enregistrement de la ligne de credit : " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static void update(Object obj) throws Exception {
        if (!(obj instanceof LigneCredit)) {
            throw new IllegalArgumentException("L'objet doit être de type LigneCredit");
        }
        LigneCredit ligneCredit = (LigneCredit) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "UPDATE lignes_credit SET libelle = ?, montant = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, ligneCredit.getLibelle());
            stmt.setDouble(2, ligneCredit.getMontant());
            stmt.setInt(3, ligneCredit.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise a jour de la ligne de credit : " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static void delete(Object obj) throws Exception {
        if (!(obj instanceof LigneCredit)) {
            throw new IllegalArgumentException("L'objet doit être de type LigneCredit");
        }
        LigneCredit ligneCredit = (LigneCredit) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "DELETE FROM lignes_credit WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ligneCredit.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la ligne de credit : " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static List<LigneCredit> findAll() throws Exception {
        List<LigneCredit> lignesCredit = new ArrayList<>();
        Connection conn = null;

        try {
            conn = UtiDB.getInstance();
            lignesCredit = findAll(conn);
            return lignesCredit;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des lignes de credit : " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static List<LigneCredit> findAll(Connection conn) throws Exception {
        List<LigneCredit> lignesCredit = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM lignes_credit";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                LigneCredit ligneCredit = new LigneCredit(rs.getString("libelle"), rs.getDouble("montant"));
                ligneCredit.setId(rs.getInt("id"));
                lignesCredit.add(ligneCredit);
            }
            return lignesCredit;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des lignes de credit : " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static List<LigneCredit> findAllWithPagination(int index, int limit) throws Exception {
        List<LigneCredit> lignesCredit = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM lignes_credit LIMIT ?, ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, index);
            stmt.setInt(2, limit);
            rs = stmt.executeQuery();

            while (rs.next()) {
                LigneCredit ligneCredit = new LigneCredit(rs.getString("libelle"), rs.getDouble("montant"));
                ligneCredit.setId(rs.getInt("id"));
                lignesCredit.add(ligneCredit);
            }
            return lignesCredit;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des lignes de credit : " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static LigneCredit findById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LigneCredit ligneCredit = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM lignes_credit WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                ligneCredit = new LigneCredit(rs.getString("libelle"), rs.getDouble("montant"));
                ligneCredit.setId(rs.getInt("id"));
            } else {
                throw new Exception("Aucune ligne de credit trouvee avec l'ID : " + id);
            }
            return ligneCredit;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recherche de la ligne de credit : " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }
}