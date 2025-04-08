package gestionDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connexion.UtiDB;
import dep.Utilisateur;
import interfac.DAO;

public class UtilisateurDAO extends DAO {
    public static void save(Object obj) throws Exception {
        if (!(obj instanceof Utilisateur)) {
            throw new IllegalArgumentException("L'objet doit être de type Utilisateur");
        }
        Utilisateur utilisateur = (Utilisateur) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "INSERT INTO utilisateurs (nom, pwd) VALUES (?, ?)";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPwd());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de l'enregistrement de l'utilisateur : " + e.getMessage());
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
        if (!(obj instanceof Utilisateur)) {
            throw new IllegalArgumentException("L'objet doit être de type Utilisateur");
        }
        Utilisateur utilisateur = (Utilisateur) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "UPDATE utilisateurs SET nom = ?, pwd = ? WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPwd());
            stmt.setInt(3, utilisateur.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la mise a jour de l'utilisateur : " + e.getMessage());
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
        if (!(obj instanceof Utilisateur)) {
            throw new IllegalArgumentException("L'objet doit être de type Utilisateur");
        }
        Utilisateur utilisateur = (Utilisateur) obj;
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "DELETE FROM utilisateurs WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, utilisateur.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la suppression de l'utilisateur : " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                throw new SQLException("Erreur lors de la fermeture des ressources : " + e.getMessage());
            }
        }
    }

    public static List<Utilisateur> findAll() throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM utilisateurs";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(rs.getString("nom"), rs.getString("pwd"));
                utilisateur.setId(rs.getInt("id"));
                utilisateurs.add(utilisateur);
            }
            return utilisateurs;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des utilisateurs : " + e.getMessage());
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

    public static List<Utilisateur> findAllWithPagination(int index, int limit) throws Exception {
        List<Utilisateur> utilisateurs = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM utilisateurs LIMIT ?, ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, index);
            stmt.setInt(2, limit);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur(rs.getString("nom"), rs.getString("pwd"));
                utilisateur.setId(rs.getInt("id"));
                utilisateurs.add(utilisateur);
            }
            return utilisateurs;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recuperation des utilisateurs : " + e.getMessage());
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

    public static Utilisateur findById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Utilisateur utilisateur = null;

        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM utilisateurs WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                utilisateur = new Utilisateur(rs.getString("nom"), rs.getString("pwd"));
                utilisateur.setId(rs.getInt("id"));
            } else {
                throw new Exception("Aucun utilisateur trouve avec l'ID : " + id);
            }
            return utilisateur;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
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

    public static Utilisateur findByUsername(String username) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Utilisateur user = null;
    
        try {
            conn = UtiDB.getInstance();
            String sql = "SELECT * FROM utilisateurs WHERE nom = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                user = new Utilisateur(rs.getString("nom"), rs.getString("pwd"));
                user.setId(rs.getInt("id"));
            }
            return user;
        } catch (SQLException e) {
            throw new SQLException("Erreur lors de la recherche de l'utilisateur : " + e.getMessage());
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