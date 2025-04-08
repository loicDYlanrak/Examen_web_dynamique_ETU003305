package gestionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.UtiDB;
import dep.LigneDepense;
import interfac.DAO;

public class LigneDepenseDAO extends DAO {
    public static void save(Object obj) throws Exception {
        if (!(obj instanceof LigneDepense)) {
            throw new IllegalArgumentException("L'objet doit être de type LigneDepense");
        }
        LigneDepense ligneDepense = (LigneDepense) obj;
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = UtiDB.getInstance();
            String sql = "INSERT INTO lignes_depense (id_credit, montant, date_depense) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ligneDepense.getIdCredit());
            stmt.setDouble(2, ligneDepense.getMontant());
            stmt.setDate(3, new java.sql.Date(ligneDepense.getDateDepense().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'enregistrement de la ligne de depense : " + e.getMessage());
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
        if (!(obj instanceof LigneDepense)) {
            throw new IllegalArgumentException("L'objet doit être de type LigneDepense");
        }
        LigneDepense ligneDepense = (LigneDepense) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "UPDATE lignes_depense SET id_credit = ?, montant = ?, date_depense = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ligneDepense.getIdCredit());
            stmt.setDouble(2, ligneDepense.getMontant());
            stmt.setDate(3, new java.sql.Date(ligneDepense.getDateDepense().getTime()));
            stmt.setInt(4, ligneDepense.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise a jour de la ligne de depense : " + e.getMessage());
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
        if (!(obj instanceof LigneDepense)) {
            throw new IllegalArgumentException("L'objet doit être de type LigneDepense");
        }
        LigneDepense ligneDepense = (LigneDepense) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "DELETE FROM lignes_depense WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, ligneDepense.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de la ligne de depense : " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static List<LigneDepense> findAll() throws Exception {
        List<LigneDepense> lignesDepense = new ArrayList<>();
        Connection conn = null;
       
        try {
            conn = UtiDB.getInstance();
            lignesDepense = findAll(conn);
            return lignesDepense;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des lignes de depense : " + e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static List<LigneDepense> findAll(Connection conn) throws Exception {
        List<LigneDepense> lignesDepense = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM lignes_depense";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                LigneDepense ligneDepense = new LigneDepense(
                    rs.getInt("id_credit"),
                    rs.getDouble("montant"),
                    rs.getDate("date_depense")
                );
                ligneDepense.setId(rs.getInt("id"));
                lignesDepense.add(ligneDepense);
            }
            return lignesDepense;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des lignes de depense : " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static List<LigneDepense> findAllWithPagination(int index, int limit) throws Exception {
        List<LigneDepense> lignesDepense = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM lignes_depense LIMIT ?, ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, index);
            stmt.setInt(2, limit);
            rs = stmt.executeQuery();

            while (rs.next()) {
                LigneDepense ligneDepense = new LigneDepense(
                    rs.getInt("id_credit"),
                    rs.getDouble("montant"),
                    rs.getDate("date_depense")
                );
                ligneDepense.setId(rs.getInt("id"));
                lignesDepense.add(ligneDepense);
            }
            return lignesDepense;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des lignes de depense : " + e.getMessage());
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

    public static LigneDepense findById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        LigneDepense ligneDepense = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM lignes_depense WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                ligneDepense = new LigneDepense(
                    rs.getInt("id_credit"),
                    rs.getDouble("montant"),
                    rs.getDate("date_depense")
                );
                ligneDepense.setId(rs.getInt("id"));
            } else {
                throw new Exception("Aucune ligne de depense trouvee avec l'ID : " + id);
            }
            return ligneDepense;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recherche de la ligne de depense : " + e.getMessage());
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