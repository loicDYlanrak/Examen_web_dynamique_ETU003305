package connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtiDB {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_financiere";
    private static final String UTILISATEUR = "root";
    private static final String MOT_DE_PASSE = "";

    private static Connection connexion = null;

    private UtiDB() {}

    public static Connection getInstance() throws Exception {
        if (connexion == null || connexion.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connexion = DriverManager.getConnection(URL, UTILISATEUR, MOT_DE_PASSE);
                connexion.setAutoCommit(true);
            } catch (Exception e) {
                throw new Exception("Erreur de connexion : " + e.getMessage());
            }
        }
        return connexion;
    }

    public static void setAutoCommit(boolean autoCommit) throws Exception {
        try {
            getInstance().setAutoCommit(autoCommit);
        }  catch (Exception e) {
            throw e;
        }
    }

    public static void commit() throws SQLException {
        if (connexion != null && !connexion.getAutoCommit()) {
            connexion.commit();
        }
    }

    public static void rollback() throws SQLException {
        if (connexion != null && !connexion.getAutoCommit()) {
            connexion.rollback();
        }
    }

    public static void closeConnection() throws SQLException {
        if (connexion != null && !connexion.isClosed()) {
            connexion.close();
        }
    }
}